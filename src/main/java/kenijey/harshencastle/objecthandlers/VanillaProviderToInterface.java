package kenijey.harshencastle.objecthandlers;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IVanillaProvider;
import net.minecraft.item.ItemStack;

public class VanillaProviderToInterface implements IVanillaProvider
{

	private final EnumInventorySlots slot;
	private final int toolTipLines;
	private final Object provider;
	
	public VanillaProviderToInterface(EnumInventorySlots slot, int toolTipLines, Object provider) {
		this.slot = slot;
		this.toolTipLines = toolTipLines;
		this.provider = provider;
	}
	
	public VanillaProviderToInterface(EnumInventorySlots slot, Object provider) {
		this(slot, 2, provider);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return slot;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return provider;
	}
	
	@Override
	public int toolTipLines() {
		return toolTipLines;
	}

}
