package kenijey.harshencastle.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CauldronBlock extends Block implements ITileEntityProvider
{
	public static final ArrayList<BlockPos> CAULDRON_POSITIONS = new ArrayList<>();
	
	public final static HashMap<Integer, IBakedModel> MODELS = new HashMap<>();
	
	public CauldronBlock() 
	{
		super(Material.IRON);
		setRegistryName("cauldron_block");
		setUnlocalizedName("cauldron_block");
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		if(CAULDRON_POSITIONS.contains(pos))
			if(worldIn.getBlockState(pos.down()).getBlock() != this)
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0, 0, 1, 0.5, 1));
			else
			{
				ArrayList<EnumFacing> openDirections = new ArrayList<>();
				for(EnumFacing face : EnumFacing.HORIZONTALS)
					if(worldIn.getBlockState(pos.offset(face)).getBlock() != this)
						openDirections.add(face);
				if(openDirections.isEmpty())
					addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
				else
				{
					if(openDirections.contains(EnumFacing.NORTH))
						addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0, 0, 1, 1, 0.5));
					if(openDirections.contains(EnumFacing.EAST))
						addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1, 0, 0, 0.5, 1, 1));
					if(openDirections.contains(EnumFacing.SOUTH))
						addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0, 1, 1, 1, 0.5));
					if(openDirections.contains(EnumFacing.WEST))
						addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0, 0, 0.5, 1, 1));
				}
			}
		else
			addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);		

	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	public static void registerModels()
	{
		for(int i = TileEntityCaulronBlock.MIN_LEVELS; i <= TileEntityCaulronBlock.MAX_LEVELS; i++)
			MODELS.put(i, HarshenClientUtils.getModel(new ResourceLocation(HarshenCastle.MODID, "special/cauldron_" + i)));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntityCaulronBlock.testForCauldron(worldIn, pos);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		((TileEntityCaulronBlock) worldIn.getTileEntity(pos)).breakBlock();
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCaulronBlock();
	}
	
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return state.isOpaqueCube();
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return !CAULDRON_POSITIONS.contains(pos);
	}

}
