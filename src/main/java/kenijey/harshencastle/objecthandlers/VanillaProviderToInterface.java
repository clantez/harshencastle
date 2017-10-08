package kenijey.harshencastle.objecthandlers;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IVanillaProvider;

public class VanillaProviderToInterface implements IVanillaProvider
{

	private final EnumInventorySlots slot;
	private final int toolTipLines;
	
	public VanillaProviderToInterface(EnumInventorySlots slot, int toolTipLines) {
		this.slot = slot;
		this.toolTipLines = toolTipLines;
	}
	
	public VanillaProviderToInterface(EnumInventorySlots slot) {
		this(slot, 2);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return slot;
	}
	
	@Override
	public int toolTipLines() {
		return toolTipLines;
	}

}
