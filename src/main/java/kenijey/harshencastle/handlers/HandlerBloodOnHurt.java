package kenijey.harshencastle.handlers;

import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerBloodOnHurt
{
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		System.out.println(event.getEntityLiving().getHeldItemMainhand().getItem().getClass());
		if(event.getSource() instanceof EntityDamageSource && new Random().nextInt(10) == 0 )
			event.getEntity().getEntityWorld().setBlockState(event.getEntity().getPosition(), HarshenBlocks.blood_block.getDefaultState(), 3);
	}
}
