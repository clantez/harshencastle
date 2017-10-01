package kenijey.harshencastle.containers;

import java.awt.Point;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseHarshenContainer;
import kenijey.harshencastle.base.HandlerInventory;
import kenijey.harshencastle.slots.OutputSlot;
import kenijey.harshencastle.tileentity.TileEntityHarshenMagicTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMagicTable extends BaseHarshenContainer
{

	private final TileEntityHarshenMagicTable te;
	
	public ContainerMagicTable(TileEntityHarshenMagicTable te, EntityPlayer player) 
	{
		super(te.getHandler(), player);
		this.te = te;
	}
	
	@Override
	protected Slot getSlot(ItemStackHandler handler, int index, int xPosition, int yPosition) {
		return index == 4 ? new OutputSlot(handler, index, xPosition, yPosition) : super.getSlot(handler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	protected Point getPoint(int index) 
	{
		return HarshenUtils.listOf(new Point(80, 17), new Point(45, 52), new Point(115, 52), new Point(80, 87), new Point(80, 52))[index];
	}

	@Override
	protected Point getInventoryStart() {
		return new Point(8, 128);
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		te.markDirty();
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
	

}
