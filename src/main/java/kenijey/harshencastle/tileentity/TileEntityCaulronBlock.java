package kenijey.harshencastle.tileentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.blocks.CauldronBlock;
import kenijey.harshencastle.enums.items.GlassContainerValues;
import kenijey.harshencastle.internal.HarshenRegistry;
import kenijey.harshencastle.items.BloodCollector;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketForceCauldronUpdate;
import kenijey.harshencastle.network.packets.MessagePacketUpdateCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
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
		((TileEntityCaulronBlock)world.getTileEntity(position)).addAdjacent(blocksNearby);
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
	
	public boolean onActivated(EntityPlayer playerIn, EnumHand hand)
	{
		if(!isCauldron())
			return false;
		ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (itemstack.isEmpty())
        	return false;
        boolean flag;
        if(item instanceof BloodCollector && (controller.fluid ==  GlassContainerValues.BLOOD.getType() || controller.fluid == CauldronLiquid.NONE))
        {
        	if(controller.level != 3)
        		if (playerIn.capabilities.isCreativeMode || (!playerIn.capabilities.isCreativeMode && ((BloodCollector)item).remove(playerIn, hand, 3)))
                {
        			this.world.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
        			controller.level ++;
        			controller.fluid = GlassContainerValues.BLOOD.getType();
        		}
        	return true;
        }
        CauldronLiquid potentionalLiquid = HarshenRegistry.getLiquidFromStack(itemstack);
        if(potentionalLiquid != null && (controller.level <= 0 || (controller.fluid == potentionalLiquid && controller.level + potentionalLiquid.getFillBy() < 4)))
        {
        	controller.fluid = HarshenRegistry.getRelativeFluid(potentionalLiquid);
        	controller.level += potentionalLiquid.getFillBy();
        	itemstack.shrink(1);
        	if(HarshenRegistry.getOutPutItem(potentionalLiquid) != null)
        		HarshenUtils.give(playerIn, hand, HarshenRegistry.getOutPutItem(potentionalLiquid));
        	return true;
        }
        ItemStack potentionalItem = HarshenRegistry.getOutPutItem(controller.fluid);
        if(potentionalItem != null && !itemstack.isEmpty() && potentionalItem.isItemEqual(itemstack) && controller.level - controller.fluid.getFillBy() >= 0)
        {
        	controller.level -= controller.fluid.getFillBy();
        	ItemStack oldStack = itemstack.copy();
        	itemstack.shrink(1);
        	HarshenUtils.give(playerIn, hand, HarshenRegistry.getInputFromOutput(controller.fluid));
        	return true;
        }
        
        return false;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return isLeader() ? new AxisAlignedBB(pos, pos.add(getSize(), getSize(), getSize())).grow(5) : super.getRenderBoundingBox();
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
	
	public void setupCauldronMultiblock(int size)
	{
		ArrayList<TileEntityCaulronBlock> cauldrons = new ArrayList<>();
		if(isLeader())
			for(int x = 0; x < size; x++)
				for(int y = 0; y < size; y++)
					for(int z = 0; z < size; z++)
						if(world.getTileEntity(pos.add(x, y, z)) instanceof TileEntityCaulronBlock)
							cauldrons.add((TileEntityCaulronBlock) world.getTileEntity(pos.add(x, y, z)));
		CauldronMultiBlock cmb = new CauldronMultiBlock(size, cauldrons);
		for(TileEntityCaulronBlock te : cauldrons)
			te.setController(cmb);
	}
	
	public void setController(CauldronMultiBlock controller) {
		if(world.isRemote)
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
		if(world != null && world.isRemote && getTileData().getBoolean("isLeader"))
			HarshenNetwork.sendToServer(new MessagePacketForceCauldronUpdate(pos, getTileData().getInteger("level"), getTileData().getString("fluid")));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		getTileData().setInteger("size", getSize());
		getTileData().setBoolean("isLeader", isLeader());
		getTileData().setInteger("level", getLevel());
		if(getFluid() != null)
			getTileData().setString("fluid", getFluid().getName());
		return super.writeToNBT(compound);
	}
	
	public int getLevel() {
		if(controller == null)
			return 0;
		return controller.level;
	}
	
	public CauldronMultiBlock getController() {
		return controller;
	}
	
	public CauldronLiquid getFluid() {
		if(controller == null)
			return null;
		return controller.fluid;
	}
	
	public static class CauldronMultiBlock implements Serializable
	{
		public CauldronLiquid fluid = CauldronLiquid.NONE;
		public int level = 0;
		
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
