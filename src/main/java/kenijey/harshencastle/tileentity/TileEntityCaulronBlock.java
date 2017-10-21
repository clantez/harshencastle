package kenijey.harshencastle.tileentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketUpdateCauldron;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityCaulronBlock extends TileEntity implements Serializable 
{
	public final static int MIN_LEVELS = 2;
	public final static int MAX_LEVELS = 5;

	public final static int LEVEL_RANGE = (MAX_LEVELS - MIN_LEVELS) + 1;
	
	private CauldronMultiBlock controller;
	
	private int legacySize;
	
	private boolean isLeader;

	public static void testForCauldron(World world)
	{
		if(world.isRemote)
			return;
		ArrayList<TileEntityCaulronBlock> blocksNearby = new ArrayList<>();
		for(TileEntity te : new ArrayList<TileEntity>(world.loadedTileEntityList))
			if(te instanceof TileEntityCaulronBlock)
				blocksNearby.add(((TileEntityCaulronBlock)te));
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
					for(int z = 0; z < cmb.size; z++)
						if(!(world.getTileEntity(cmb.positions.get(0).pos.add(x, 0, z)) instanceof TileEntityCaulronBlock
								&& !((TileEntityCaulronBlock)world.getTileEntity(cmb.positions.get(0).pos.add(x, 0, z))).isCauldron()))
							isFalse = true;;
				if(!isFalse)
				{
					for(int x = 0; x < cmb.size; x++)
						for(int z = 0; z < cmb.size; z++)
							((TileEntityCaulronBlock)world.getTileEntity(cmb.positions.get(0).pos.add(x, 0, z))).activate(cmb);
				}
			}				
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return isLeader ? new AxisAlignedBB(pos, pos.add(getSize(), 1, getSize())) : super.getRenderBoundingBox();
	}
		
	private void activate(CauldronMultiBlock controller)
	{
		if(isCauldron() && controller.size < this.controller.size)
			return;
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketUpdateCauldron(pos, true, controller.positions.get(0) == this, controller.size));
		world.scheduleBlockUpdate(pos, HarshenBlocks.CAULDRON_BLOCK, 500, 0);
		this.controller = controller;
	}
	
	public void deactivate()
	{
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketUpdateCauldron(pos, false, false, -1));
		world.scheduleBlockUpdate(pos, HarshenBlocks.CAULDRON_BLOCK, 500, 0);
		controller = null;
		isLeader = false;
	}
	
	public boolean isLeader() {
		return isLeader;
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
				for(int z = 0; z <  i; z++)
					if(world.getBlockState(pos.add(x, 0, z)).getBlock() != HarshenBlocks.CAULDRON_BLOCK)
						testCauldron = false;
					else
						te.add((TileEntityCaulronBlock)world.getTileEntity(pos.add(x, 0, z)));
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
	
	public void setLeader(boolean leader)
	{
		this.isLeader = leader;
	}
	
	public boolean isCauldron() {
		return controller != null;
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
