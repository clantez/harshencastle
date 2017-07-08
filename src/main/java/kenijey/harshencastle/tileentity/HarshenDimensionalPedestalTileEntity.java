package kenijey.harshencastle.tileentity;

import java.util.ArrayList;
import java.util.Arrays;

import org.omg.PortableInterceptor.ACTIVE;

import kenijey.harshencastle.HarshenItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class HarshenDimensionalPedestalTileEntity extends TileEntity implements net.minecraft.util.ITickable, ICapabilityProvider
{
	private final ItemStackHandler handler;
	private boolean hasItem = false;
	private int rotation = 0, activeTimer = 0;
	public static ArrayList<BlockPos> positionsOfGo = new ArrayList<BlockPos>(); 
	private boolean isActive = false;

	
	public HarshenDimensionalPedestalTileEntity(){
		this.handler = new ItemStackHandler(1);
	}
	
	public boolean canAddItem()
	{	 
		return this.handler.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.AIR);
	}
	
	public void addItem(ItemStack item)
	{
		item.setCount(1);
		dirty();
		this.handler.setStackInSlot(0, item);
		checkForCompleation();
	}
	
	private void checkForCompleation()
	{
		ArrayList<Item> localItems = new ArrayList<Item>(Arrays.asList(
				HarshenItems.pontus_world_gate_part_1,
				HarshenItems.pontus_world_gate_part_2,
				HarshenItems.pontus_world_gate_part_3,
				HarshenItems.pontus_world_gate_spawner));
		for(EnumFacing facing : EnumFacing.HORIZONTALS)
		{
			BlockPos position = pos.offset(facing);
			ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
			ArrayList<Boolean> isBlock = new ArrayList<Boolean>();
			ArrayList<Boolean> isBlockHolding = new ArrayList<Boolean>();
			for(EnumFacing face : EnumFacing.HORIZONTALS)
			{
				boolean flag = world.getTileEntity(position.offset(face)) instanceof HarshenDimensionalPedestalTileEntity;
				isBlock.add(flag);
				if(flag)
					blocks.add(position.offset(face));
			}
			if(!isBlock.contains(false))
			{
				for(EnumFacing face : EnumFacing.HORIZONTALS)
					if(localItems.contains(((HarshenDimensionalPedestalTileEntity) world.getTileEntity(position.offset(face))).getItem().getItem()))
						localItems.remove(((HarshenDimensionalPedestalTileEntity) world.getTileEntity(position.offset(face))).getItem().getItem());
				if(localItems.isEmpty())
					activate(position, blocks);
			}
		}
	}
	
	private void activate(BlockPos pos, ArrayList<BlockPos> positions)
	{
		positionsOfGo.add(pos);
		for(BlockPos position : positions)
			((HarshenDimensionalPedestalTileEntity) world.getTileEntity(position)).setActive(true);
	}
	
	public void setActive(Boolean set)
	{
		isActive = set;
	}
	
	public void delItem()
	{
		dirty();
		this.handler.setStackInSlot(0, new ItemStack(Blocks.AIR));
	}
	
	public ItemStack getItem()
	{
		return handler.getStackInSlot(0);
	}
	
	public int getRotation()
	{
		return rotation;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	private void dirty()
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
	public void readFromNBT(NBTTagCompound nbt)
	{
		this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
		return super.writeToNBT(nbt);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound getTileData() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}

	@Override
	public void update() {
		boolean flag = handler.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.AIR);
		if(!flag)
			rotation = rotation == 360? 0 : rotation + 6;
		else
			rotation = 0;
		if(flag != hasItem)
		{
			hasItem = flag;
			dirty();
		}
		
		if(isActive)
			System.out.println(activeTimer++);
		
		

	}

	

}
