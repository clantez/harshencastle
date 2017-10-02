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
		return -1;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		EntityThrown thrown = new EntityThrown(worldIn, entityLiving, new EntityThrown.HitResult() {
			@Override
			public void onHit(RayTraceResult result, boolean isServer) {
				if(isServer)
					worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, result.hitVec.x, result.hitVec.y, result.hitVec.z, false));
			}
		}, new EntityThrowLocation(0));
		thrown.setHeadingFromThrower(entityLiving, entityLiving.rotationPitch, entityLiving.rotationYaw, 0.0F, 1.5F, 0f);
		worldIn.spawnEntity(thrown);
		stack.damageItem(1, entityLiving);
		return stack;
	}
}
