package kenijey.harshencastle.base;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.gui.GuiHarshenChest;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public abstract class BaseBlockHarshenStackedInventory extends Block implements ITileEntityProvider
{
	
	public BaseBlockHarshenStackedInventory(Material materialIn) 
	{
		super(materialIn);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return getTile();
	}
	
	public abstract BaseTileEntityHarshenStackedInventory getTile();
	
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{	
		BaseTileEntityHarshenStackedInventory te = (BaseTileEntityHarshenStackedInventory) worldIn.getTileEntity(pos);
		super.breakBlock(worldIn, pos, state);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for(int i = 0; i < handler.getSlots(); i++)
			if(worldIn.isRemote)
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(0));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		HarshenCastle.proxy.openGui(new GuiHarshenChest(playerIn.inventory, ((BaseTileEntityHarshenStackedInventory)worldIn.getTileEntity(pos))));
		return true;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
