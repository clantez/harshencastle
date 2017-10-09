package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.HarshenEvent;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class FieryRing extends Item implements IHarshenProvider
{

	public FieryRing() {
		setUnlocalizedName("fiery_ring");
		setRegistryName("fiery_ring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new HandlerFieryRing();
	}
	
	public class HandlerFieryRing
	{
		@HarshenEvent
		public void onBlockBroken(HarvestDropsEvent event)
		{
			HarshenUtils.cookAndReplaceStackList(event.getDrops());
		}
		
		@HarshenEvent
		public void onLivingDrops(LivingDropsEvent event)
		{
			HarshenUtils.cookAndReplaceList(event.getDrops());
		}
	}

}
