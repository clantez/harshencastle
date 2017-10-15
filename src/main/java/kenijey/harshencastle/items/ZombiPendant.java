package kenijey.harshencastle.items;

import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.HarshenEvent;
import kenijey.harshencastle.api.IHarshenProvider;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ZombiPendant extends Item implements IHarshenProvider
{
	public ZombiPendant()
	{
		setUnlocalizedName("zombi_pendant");
		setRegistryName("zombi_pendant");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new HandlerZombiPendant();
	}
	
	public class HandlerZombiPendant
	{
		@HarshenEvent
		public void onLivingHurt(LivingHurtEvent event)
		{
			if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityZombie &&
					!(((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPigZombie))
				event.setAmount(1);
		}
	}

}
