package kenijey.harshencastle.handlers;

import java.util.Arrays;
import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerBloodOnHurt
{
	Class[] AllowedEntities = {EntityPlayerMP.class, EntityWitch.class, EntityVillager.class};
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(event.getSource() instanceof EntityDamageSource && new Random().nextInt(3) == 0 && Arrays.asList(AllowedEntities).contains(event.getEntity().getClass())
				&& event.getEntity().world.isAirBlock(event.getEntity().getPosition()))
			event.getEntity().getEntityWorld().setBlockState(event.getEntity().getPosition(), HarshenBlocks.blood_block.getDefaultState(), 3);
	}
}
