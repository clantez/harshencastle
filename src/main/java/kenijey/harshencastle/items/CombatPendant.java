package kenijey.harshencastle.items;

import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.IHarshenProvider;
import net.minecraft.item.Item;

public class CombatPendant extends Item implements IHarshenProvider
{
	
	public CombatPendant() {
		setRegistryName("combat_pendant");
		setUnlocalizedName("combat_pendant");
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
}
