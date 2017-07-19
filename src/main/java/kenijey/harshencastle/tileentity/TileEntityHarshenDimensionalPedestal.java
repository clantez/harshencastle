package kenijey.harshencastle.tileentity;

import java.util.ArrayList;
import java.util.Arrays;

import org.omg.PortableInterceptor.ACTIVE;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
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

public class TileEntityHarshenDimensionalPedestal extends BaseTileEntityHarshenSingleItemInventory
{
	int activeTimer = 0;
	public static ArrayList<BlockPos> positionsOfGo = new ArrayList<BlockPos>(); 
	private boolean isActive = false;
	
	public float getMove()
	{
		return activeTimer / 100f;
	}
	
	public BlockPos getMoveDirection()
	{
		for(BlockPos pos : positionsOfGo)
			if(pos.distanceSq(this.pos) < 2)
				for(EnumFacing face : EnumFacing.HORIZONTALS)
					if(this.pos.offset(face).equals(pos))
						return new BlockPos(0, 0, 0).offset(face);
				
		return null;
	}
	
	@Override
	public void tick() {
		boolean flag = handler.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.AIR);
		if(flag)
			timer = 0;
		if(flag != hasItem)
		{
			hasItem = flag;
			dirty();
		}
		
		if(isActive)
			if(activeTimer++ == 100)
			{
				BlockPos rem = null;
				isActive = false;
				handler.setStackInSlot(0, new ItemStack(Blocks.AIR));
				for(BlockPos pos : positionsOfGo)
				{
					if(pos.distanceSq(this.pos) < 2)
						for(EnumFacing face : EnumFacing.HORIZONTALS)
							if(this.pos.offset(face).equals(pos))
							{
								world.addWeatherEffect(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
								if(!world.isRemote)
									InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(HarshenItems.pontus_world_gate_spawner));
								rem = pos;
								break;
							}
					if(rem == null)
						break;
				}
				if(!world.isRemote)
					positionsOfGo.remove(rem);
			}
	}
	
	@Override
	public boolean setItem(ItemStack item) {
		super.setItem(item);
		checkForCompleation();
		return true;
	}

	private void checkForCompleation()
	{
		ArrayList<Item> localItems = new ArrayList<Item>(Arrays.asList(
				HarshenItems.pontus_world_gate_part_1,
				HarshenItems.pontus_world_gate_part_2,
				HarshenItems.pontus_world_gate_part_3,
				HarshenItems.harshen_soul_fragment));
		for(EnumFacing facing : EnumFacing.HORIZONTALS)
		{
			BlockPos position = pos.offset(facing);
			ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
			ArrayList<Boolean> isBlock = new ArrayList<Boolean>();
			ArrayList<Boolean> isBlockHolding = new ArrayList<Boolean>();
			for(EnumFacing face : EnumFacing.HORIZONTALS)
			{
				boolean flag = world.getTileEntity(position.offset(face)) instanceof TileEntityHarshenDimensionalPedestal;
				isBlock.add(flag);
				if(flag)
					blocks.add(position.offset(face));
			}
			if(!isBlock.contains(false))
			{
				for(EnumFacing face : EnumFacing.HORIZONTALS)
					if(localItems.contains(((TileEntityHarshenDimensionalPedestal) world.getTileEntity(position.offset(face))).getItem().getItem()))
						localItems.remove(((TileEntityHarshenDimensionalPedestal) world.getTileEntity(position.offset(face))).getItem().getItem());
				if(localItems.isEmpty())
					activate(position, blocks);
			}
		}
	}
	
	private void activate(BlockPos pos, ArrayList<BlockPos> positions)
	{
		positionsOfGo.clear();
		positionsOfGo.add(pos);
		for(BlockPos position : positions)
			((TileEntityHarshenDimensionalPedestal) world.getTileEntity(position)).setActive();
	}
	
	public void setActive()
	{
		isActive = true;
		activeTimer = 0;
	}
	
	public void delItem()
	{
		dirty();
		this.handler.setStackInSlot(0, new ItemStack(Blocks.AIR));
	}
	
	public boolean hasItem()
	{
		return getItem().getItem() != Item.getItemFromBlock(Blocks.AIR);
	}
	
	public ItemStack getItem()
	{
		return handler.getStackInSlot(0);
	}
	
	public boolean isActive()
	{
		return isActive;
	}
}
