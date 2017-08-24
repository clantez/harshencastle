package kenijey.harshencastle.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPlayerInventory extends net.minecraft.inventory.Container
{
	
	ItemStackHandler handler = new ItemStackHandler(3);
	public ContainerPlayerInventory(EntityPlayer player)
	{
		IInventory playerInv = player.inventory;
		handler.deserializeNBT(player.getEntityData().getCompoundTag("harshenInventory"));
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 44, 20));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 62, 20));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 107, 20));		
		int xPos = 8;
		int yPos = 51;
				
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}
				
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return !playerIn.isSpectator();
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    ItemStack previous = ItemStack.EMPTY;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < this.handler.getSlots()) {
	            if (!this.mergeItemStack(current, handler.getSlots(), 39, true))
	                return ItemStack.EMPTY;
	        } else {
	            if (!this.mergeItemStack(current, 0, handler.getSlots(), false))
	                return ItemStack.EMPTY;
	        }

	        if (current.getCount() == 0)
	            slot.putStack(ItemStack.EMPTY);
	        else
	            slot.onSlotChanged();

	        if (current.getCount() == previous.getCount())
	            return null;
	        slot.onTake(playerIn, current);
	    }
	    return previous;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		playerIn.getEntityData().setTag("harshenInventory", this.handler.serializeNBT());
	}
}