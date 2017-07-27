package kenijey.harshencastle.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class BaseTileEntityHarshenSingleItemInventory extends BaseHarshenTileEntity implements net.minecraft.util.ITickable, ICapabilityProvider
{
	protected final ItemStackHandler handler;
	protected boolean hasItem = false;
	protected int timer;
	private int dirtyTimer;
	
	public BaseTileEntityHarshenSingleItemInventory(){
		this.handler = new ItemStackHandler(1);
	}
	
	
	@Override
	public void update()
	{
		timer ++;
		tick();
		if(dirtyTimer++ % 10 == 0)
			dirty();
		
	}
	
	public int getTimer()
	{
		return timer;
	}
	
	protected void tick()
	{
		
	}
	
	
	
	
	public boolean canAddItem()
	{	 
		return this.handler.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.AIR);
	}
	
	
	public boolean setItem(ItemStack item)
	{
		item.setCount(1);
		this.handler.setStackInSlot(0, item);
		dirty();
		return true;
	}
		
	public void delItem()
	{
		this.handler.setStackInSlot(0, new ItemStack(Blocks.AIR));
		dirty();
	}
	
	public boolean hasItem()
	{
		return getItem().getItem() != Item.getItemFromBlock(Blocks.AIR);
	}
	
	public ItemStack getItem()
	{
		return handler.getStackInSlot(0);
	}
	
	protected void dirty()
	{
		markDirty();
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 3);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability  == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability  == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.handler.deserializeNBT(compound.getCompoundTag("ItemStackHandler"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
		return super.writeToNBT(nbt);
	}

}

