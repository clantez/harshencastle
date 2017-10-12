package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseHarshenStaff;
import kenijey.harshencastle.entity.EntityThrown;
import kenijey.harshencastle.objecthandlers.EntityThrowSpawnData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class LightningStaff extends BaseHarshenStaff
{
	public LightningStaff()
	{
		setUnlocalizedName("lightning_staff");
		setRegistryName("lightning_staff");
		setMaxDamage(500);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.GOLD_INGOT;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 23;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		spawnThrownEntity(worldIn, entityLiving, 1.5f, new LightningHit(), new EntityThrowSpawnData(0));
		stack.damageItem(1, entityLiving);
		return stack;
	}
	
	public static class LightningHit implements EntityThrown.HitResult
	{
		@Override
		public void onHit(EntityThrown entity, RayTraceResult result, boolean isServer) {
			if(isServer)
				entity.world.addWeatherEffect(new EntityLightningBolt(entity.world, result.hitVec.x, result.hitVec.y, result.hitVec.z, false));
		}
	}
}
