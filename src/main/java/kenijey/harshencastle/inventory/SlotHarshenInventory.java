package kenijey.harshencastle.inventory;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotHarshenInventory extends SlotItemHandler
{
	
	private final EnumInventorySlots slotType;
	
	public SlotHarshenInventory(IItemHandler itemHandler, EnumInventorySlots slotType, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		this.slotType = slotType;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		Object item = stack.getItem() instanceof ItemBlock ? ((ItemBlock)stack.getItem()).getBlock() : stack.getItem();
		return item instanceof IHarshenProvider && ((IHarshenProvider)item).getSlot() == this.slotType;
	}
	
}
