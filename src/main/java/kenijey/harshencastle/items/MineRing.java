package kenijey.harshencastle.items;

import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.IHarshenProvider;
import net.minecraft.item.Item;

public class MineRing extends Item implements IHarshenProvider
{
	public MineRing()
	{
		setUnlocalizedName("minering");
		setRegistryName("minering");
		setMaxDamage(400);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
}
