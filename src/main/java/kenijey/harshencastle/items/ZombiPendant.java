package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.HarshenEvent;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ZombiPendant extends BaseItemInventory
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
