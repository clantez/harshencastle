package kenijey.harshencastle.objecthandlers;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IVanillaProvider;
import net.minecraft.item.ItemStack;

public class VanillaProviderToInterface implements IVanillaProvider
{

	private final EnumInventorySlots slot;
	private final Object provider;
	
	private boolean multipleEvent = true;
	private int toolTipLines;

	
	public VanillaProviderToInterface(EnumInventorySlots slot, Object provider) {
		this.slot = slot;
		this.provider = provider;
	}
	
	public VanillaProviderToInterface setToolTipLines(int toolTipLines) {
		this.toolTipLines = toolTipLines;
		return this;
	}
	
	public VanillaProviderToInterface disableEventMultiplication() {
		this.multipleEvent = false;
		return this;
	}
	
	@Override
	public boolean multiplyEvent(ItemStack stack) {
		return multipleEvent;
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
