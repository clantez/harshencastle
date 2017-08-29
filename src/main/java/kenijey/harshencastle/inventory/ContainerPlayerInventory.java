package kenijey.harshencastle.inventory;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.itemstackhandlers.HarshenItemStackHandler;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessageSendPlayerInv;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPlayerInventory extends net.minecraft.inventory.Container
{
	
	private final HarshenItemStackHandler handler;
	public ContainerPlayerInventory(EntityPlayer player)
	{
		IInventory playerInv = player.inventory;
		this.handler = HarshenUtils.getHandler(player);
		for(EnumInventorySlots slot : EnumInventorySlots.values())
			this.addSlotToContainer(new SlotHarshenInventory(handler, slot, slot.getId(), slot.getDimension().width, slot.getDimension().height));
		int xPos = 8;
		int yPos = 84;			
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
		return true;
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
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		ItemStack stack = super.slotClick(slotId, dragType, clickTypeIn, player);
		updated();
		return stack;
	}
	
	public void updated()
	{
		Minecraft.getMinecraft().player.getEntityData().setTag("harshenInventory", this.handler.serializeNBT());
		HarshenNetwork.sendToServer(new MessageSendPlayerInv(Minecraft.getMinecraft().player));
	}
}