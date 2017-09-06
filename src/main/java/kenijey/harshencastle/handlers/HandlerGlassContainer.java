package kenijey.harshencastle.handlers;

import kenijey.harshencastle.items.GlassContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerGlassContainer 
{
	@SubscribeEvent
	public void onEntityHit(LivingHurtEvent event)
	{
		if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource) event.getSource()).getTrueSource() instanceof EntityLivingBase && 
				((EntityLivingBase)((EntityDamageSource) event.getSource()).getTrueSource()).getHeldItemMainhand().getItem() instanceof GlassContainer && 
				((EntityLivingBase)((EntityDamageSource) event.getSource()).getTrueSource()).getHeldItemMainhand().getItemDamage() == 0)
		{
			ItemStack stack = ((EntityLivingBase)((EntityDamageSource) event.getSource()).getTrueSource()).getHeldItemMainhand();
			stack.setItemDamage(2);
			((EntityLivingBase)((EntityDamageSource) event.getSource()).getTrueSource()).setHeldItem(EnumHand.MAIN_HAND, stack);
		}
			
			
	}
}
