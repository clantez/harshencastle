package kenijey.harshencastle.blocks;

import java.util.List;

import kenijey.harshencastle.tileentity.HarshenDimensionalPedestalTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class HarshenDimensionalPedestal extends Block implements ITileEntityProvider
{

	public HarshenDimensionalPedestal() {
		super(Material.ROCK);
		setRegistryName("harshen_dimensional_pedestal");
		setUnlocalizedName("harshen_dimensional_pedestal");
		setHardness(50.0F);
		setResistance(50.0F);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25f, 0f, 0.25f, 0.75f, 0.875f, 0.75f);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new HarshenDimensionalPedestalTileEntity();
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new HarshenDimensionalPedestalTileEntity();
	}

	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		HarshenDimensionalPedestalTileEntity te = (HarshenDimensionalPedestalTileEntity) worldIn.getTileEntity(pos);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		ItemStack stack = handler.getStackInSlot(0);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack item = playerIn.getHeldItem(hand);
		if(!worldIn.isRemote)
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity instanceof HarshenDimensionalPedestalTileEntity)
				if(((HarshenDimensionalPedestalTileEntity)tileEntity).canAddItem())
				{
					playerIn.setHeldItem(hand, new ItemStack(item.getItem(), item.getCount()-1, item.getMetadata()));
					((HarshenDimensionalPedestalTileEntity)tileEntity).addItem(item);
				}
				else if (((HarshenDimensionalPedestalTileEntity) tileEntity).getItem().getItem() != Item.getItemFromBlock(Blocks.AIR))
				{
					InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY() + 0.7f, pos.getZ(), ((HarshenDimensionalPedestalTileEntity) tileEntity).getItem());
					((HarshenDimensionalPedestalTileEntity)tileEntity).delItem();
				}



				
		}
		return true;
	}

}
