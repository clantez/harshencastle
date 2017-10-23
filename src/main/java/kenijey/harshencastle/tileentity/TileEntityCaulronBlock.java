package kenijey.harshencastle.tileentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.blocks.CauldronBlock;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketUpdateCauldron;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityCaulronBlock extends TileEntity implements Serializable 
{
	public final static int MIN_LEVELS = 2;
	public final static int MAX_LEVELS = 5;
	private final static int CHECK_SIZE = 1000;

	public final static int LEVEL_RANGE = (MAX_LEVELS - MIN_LEVELS) + 1;
	
	
	private CauldronMultiBlock controller;
	
	private int legacySize;
	
	public static void testForCauldron(World world, BlockPos position)
	{
		if(world.isRemote)
			return;
		ArrayList<TileEntityCaulronBlock> blocksNearby = new ArrayList<>();
		for(TileEntity te : new ArrayList<TileEntity>(world.loadedTileEntityList))
			if(te instanceof TileEntityCaulronBlock)
			{
				ArrayList<TileEntityCaulronBlock> adjacentBlocks = new ArrayList<>();
				((TileEntityCaulronBlock)te).addAdjacent(adjacentBlocks);
				boolean update = false;
				for(int i = 0; i < adjacentBlocks.size() && i < CHECK_SIZE; i++)
					if(position == null || Math.sqrt(adjacentBlocks.get(i).getPos().distanceSq(position)) < MAX_LEVELS+2)
					{
						update = true;
						break;
					}
				if(update)
					for(TileEntityCaulronBlock t : adjacentBlocks)
						blocksNearby.add(t);
			}
		for(TileEntityCaulronBlock te : blocksNearby)
			te.testForActivation();
		for(TileEntityCaulronBlock te : blocksNearby)
			te.deactivate();
		HashMap<Integer, ArrayList<CauldronMultiBlock>> sizeMap = new HashMap<>();
		for(int i = MIN_LEVELS; i <= MAX_LEVELS; i++)
			sizeMap.put(i, new ArrayList<CauldronMultiBlock>());
		for(CauldronMultiBlock cmb : LEADER_MAP.values())
			sizeMap.get(cmb.size).add(cmb);
		for(int i = MAX_LEVELS; i >= MIN_LEVELS; i--)
			for(CauldronMultiBlock cmb : sizeMap.get(i))
			{
				boolean isFalse = false;
				for(int x = 0; x < cmb.size; x++)
					for(int y = 0; y < cmb.size; y++)
						for(int z = 0; z < cmb.size; z++)
						if(!(world.getTileEntity(cmb.positions.get(0).pos.add(x, y, z)) instanceof TileEntityCaulronBlock
								&& !((TileEntityCaulronBlock)world.getTileEntity(cmb.positions.get(0).pos.add(x, y, z))).isCauldron()))
							isFalse = true;
				if(!isFalse)
				{
					ArrayList<TileEntityCaulronBlock> adjacentBlocks = new ArrayList<>();
					cmb.positions.get(0).addAdjacent(adjacentBlocks);
					isFalse = adjacentBlocks.size() != cmb.size*cmb.size*cmb.size;
				}
				if(!isFalse)
				{
					for(int x = 0; x < cmb.size; x++)
						for(int y = 0; y < cmb.size; y++)
							for(int z = 0; z < cmb.size; z++)
								((TileEntityCaulronBlock)world.getTileEntity(cmb.positions.get(0).pos.add(x, y, z))).activate(cmb);
				}
			}				
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return isLeader() ? new AxisAlignedBB(pos, pos.add(getSize(), getSize(), getSize())) : super.getRenderBoundingBox();
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}
		
	private void activate(CauldronMultiBlock controller)
	{
		if(isCauldron() && controller.size < this.controller.size)
			return;
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketUpdateCauldron(pos, true, controller.size));
		this.controller = controller;
	}
	
	public void deactivate()
	{
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketUpdateCauldron(pos, false, -1));
		controller = null;
	}
	
	public void addAdjacent(ArrayList<TileEntityCaulronBlock> list)
	{
		list.add(this);
		for(EnumFacing face : EnumFacing.values())
			if(world.getTileEntity(pos.offset(face)) instanceof TileEntityCaulronBlock && !list.contains(world.getTileEntity(pos.offset(face))))
				((TileEntityCaulronBlock)world.getTileEntity(pos.offset(face))).addAdjacent(list);
	}
	
	public boolean isLeader()
	{
		boolean flagTE1;
		boolean flagTE2;
		boolean flagTE3;
		TileEntity te1 = world.getTileEntity(pos.add(-1, 0, 0));
		TileEntity te2 = world.getTileEntity(pos.add(0, 0, -1));
		TileEntity te3 = world.getTileEntity(pos.add(0, -1, 0));
		if(te1 instanceof TileEntityCaulronBlock)
			flagTE1 = !CauldronBlock.CAULDRON_POSITIONS.contains(te1.getPos());
		else flagTE1 = true;
		if(te2 instanceof TileEntityCaulronBlock)
			flagTE2 = !CauldronBlock.CAULDRON_POSITIONS.contains(te2.getPos());
		else flagTE2 = true;
		if(te3 instanceof TileEntityCaulronBlock)
			flagTE3 = !CauldronBlock.CAULDRON_POSITIONS.contains(te3.getPos());
		else flagTE3 = true;
		return CauldronBlock.CAULDRON_POSITIONS.contains(pos) && flagTE1 && flagTE2 && flagTE3;
	}
	
	public void breakBlock()
	{
		if(isCauldron())
			controller.breakBlock(world);
	}
	
	public int getSize()
	{
		return isCauldron() ? this.controller.getSize() : legacySize;
	}
	
	public void setLegacySize(int legacySize) {
		this.legacySize = legacySize;
	}
	
	private void testForActivation()
	{
		int cauldronSize = -1;
		ArrayList<TileEntityCaulronBlock> tileEntites = new  ArrayList<>();
		cauldronSizeList:
		for(int i = MAX_LEVELS; i >= MIN_LEVELS; i--)
		{
			ArrayList<TileEntityCaulronBlock> te = new  ArrayList<>();
			boolean testCauldron = true;
			for(int x = 0; x < i; x++)
				for(int y = 0; y < i; y++)
					for(int z = 0; z <  i; z++)
						if(world.getBlockState(pos.add(x, y, z)).getBlock() != HarshenBlocks.CAULDRON_BLOCK)
							testCauldron = false;
						else
							te.add((TileEntityCaulronBlock)world.getTileEntity(pos.add(x, y, z)));
			if(testCauldron)
			{
				for(TileEntityCaulronBlock t : te)
					if(t.isCauldron() && t.controller.size >= i)
						continue cauldronSizeList;
				cauldronSize = i;
				tileEntites = te;
				break;
			}
		}
		if(cauldronSize > 0)
			LEADER_MAP.put(tileEntites.get(0), new CauldronMultiBlock(cauldronSize, tileEntites));
	}
	
	private static final HashMap<TileEntityCaulronBlock, CauldronMultiBlock> LEADER_MAP = new HashMap<>();
	
	public boolean isCauldron() {
		return controller != null;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.legacySize = getTileData().getInteger("size");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		getTileData().setInteger("size", getSize());
		return super.writeToNBT(compound);
	}
	
	public static class CauldronMultiBlock implements Serializable
	{
		private final ArrayList<TileEntityCaulronBlock> positions;
		private final int size;
				
		public CauldronMultiBlock(int size, ArrayList<TileEntityCaulronBlock> positions) {
			this.positions = positions;
			this.size = size;
		}
		
		public int getSize() {
			return size;
		}
		
		public void breakBlock(World world)
		{
			for(TileEntityCaulronBlock te : positions)
				te.deactivate();
		}
	}
}
