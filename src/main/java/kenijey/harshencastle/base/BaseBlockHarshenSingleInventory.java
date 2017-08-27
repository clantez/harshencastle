package kenijey.harshencastle.base;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public abstract class BaseBlockHarshenSingleInventory extends Block implements ITileEntityProvider
{
	
	public BaseBlockHarshenSingleInventory(Material materialIn) 
	{
		super(materialIn);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return getTile();
	}
	
	public abstract BaseTileEntityHarshenSingleItemInventory getTile();
	
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{	
		BaseTileEntityHarshenSingleItemInventory te = (BaseTileEntityHarshenSingleItemInventory) worldIn.getTileEntity(pos);
		super.breakBlock(worldIn, pos, state);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		ItemStack stack = handler.getStackInSlot(0);
		if(!worldIn.isRemote)
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack item = playerIn.getHeldItem(hand);
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if(tileEntity instanceof BaseTileEntityHarshenSingleItemInventory)
			if(((BaseTileEntityHarshenSingleItemInventory)tileEntity).canAddItem() && Item.getItemFromBlock(Blocks.AIR) != item.getItem())
			{
				int i =  item.getCount() - 1;
				if(((BaseTileEntityHarshenSingleItemInventory)tileEntity).setItem(item))
					playerIn.setHeldItem(hand, new ItemStack(item.getItem(), i, item.getMetadata(), item.serializeNBT()));
				
			}
			else if (!((BaseTileEntityHarshenSingleItemInventory) tileEntity).canAddItem())
			{
				ItemStack stack = ((BaseTileEntityHarshenSingleItemInventory)tileEntity).getItem();
				((BaseTileEntityHarshenSingleItemInventory)tileEntity).delItem();
				if(!worldIn.isRemote)
					InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY() + 0.7f, pos.getZ(), stack);
			}

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
