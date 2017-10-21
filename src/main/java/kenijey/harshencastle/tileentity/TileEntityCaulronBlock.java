package kenijey.harshencastle.tileentity;

import java.io.Serializable;
import java.util.ArrayList;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketUpdateCauldron;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCaulronBlock extends TileEntity implements Serializable 
{
	private CauldronMultiBlock controller;
	
	private boolean isLeader;
	//TODO fix ~~
	/**
	 * Make it so that after it collects all the possible ones, it adds them to a list then for each leader in each group, making sure the leaders are going
	 * from ones with the highest cauldron size to lowest. If all the cauldron blocks are not active, then turn them active and set this to a global leader;
	 * sending the info to the client. this way itll stop there being multiple cauldron making up one big cauldron. If this dosnt work set it so the blocks cant have 
	 * adjacent blocks or that they have to right clicked
	 * @param world
	 */
	//
	public static void testForCauldron(World world)
	{
		if(!world.isRemote)
			for(TileEntity te : new ArrayList<TileEntity>(world.loadedTileEntityList))
				if(te instanceof TileEntityCaulronBlock)
					((TileEntityCaulronBlock)te).testForActivation();
	}
	
	private void activate(CauldronMultiBlock controller)
	{
		if(isCauldron() && controller.size < this.controller.size)
			return;
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketUpdateCauldron(pos, true, controller.positions.get(0) == this));
		world.scheduleBlockUpdate(pos, HarshenBlocks.CAULDRON_BLOCK, 500, 0);
		this.controller = controller;
	}
	
	public void deactivate()
	{
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketUpdateCauldron(pos, false, false));
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
		return this.controller.size;
	}
	
	private void testForActivation()
	{
		int cauldronSize = -1;
		ArrayList<TileEntityCaulronBlock> tileEntites = new  ArrayList<>();
		cauldronSizeList:
		for(int i = 5; i >= 2; i--)
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
		{
			CauldronMultiBlock multiBlock = new CauldronMultiBlock(cauldronSize, tileEntites);
			tileEntites.get(0).setLeader(true);
			for(TileEntityCaulronBlock te : tileEntites)
				te.activate(multiBlock);
		}
	}
	
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
		
		public void breakBlock(World world)
		{
			for(TileEntityCaulronBlock te : positions)
				te.deactivate();
		}
		
	}
}
