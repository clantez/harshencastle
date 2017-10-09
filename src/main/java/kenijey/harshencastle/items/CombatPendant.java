package kenijey.harshencastle.items;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IHarshenProvider;
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
