package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseHarshenStaff;
import kenijey.harshencastle.entity.EntityThrown;
import kenijey.harshencastle.objecthandlers.EntityThrowLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
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
	public int getMaxItemUseDuration(ItemStack stack) {
		return 10;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		spawnThrownEntity(worldIn, entityLiving, 1.5f, 0, new EntityThrown.HitResult() {
			
			@Override
			public void onHit(EntityThrown entity, RayTraceResult result, boolean isServer) {
				if(isServer)
					worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, result.hitVec.x, result.hitVec.y, result.hitVec.z, false));
			}
			
		});
		stack.damageItem(1, entityLiving);
		return stack;
	}
}
