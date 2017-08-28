package kenijey.harshencastle.blocks;

import java.util.List;

import javax.annotation.Nullable;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.base.BaseBlockHarshenSingleInventory;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HereticCauldron extends BaseBlockHarshenSingleInventory
{
	
	public static final PropertyEnum<EnumHetericCauldronFluidType> LIQUID =  PropertyEnum.<EnumHetericCauldronFluidType>create("liquid_type", EnumHetericCauldronFluidType.class);
	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 1, 3);
	
	protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
    }
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return getTile(worldIn, pos) != null ? this.getDefaultState().withProperty(LIQUID, getTile(worldIn, pos).getFluid()).withProperty(LEVEL, getTile(worldIn, pos).getLevel()) : this.getDefaultState();
	}
	 
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	public HereticCauldron() {
		super(Material.IRON);
		setRegistryName("heretic_cauldron");
		setUnlocalizedName("heretic_cauldron");
		this.setDefaultState(this.blockState.getBaseState().withProperty(LIQUID, EnumHetericCauldronFluidType.NONE).withProperty(LEVEL, 1));
		TileEntityHereticCauldron.fluidMap.put(EnumHetericCauldronFluidType.LAVA, Items.LAVA_BUCKET);
		TileEntityHereticCauldron.fluidMap.put(EnumHetericCauldronFluidType.MILK, Items.MILK_BUCKET);
		setHardness(5.0F);
		setResistance(5.0F);
	}
	
	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityHereticCauldron();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		boolean flag = getTile(worldIn, pos).onActivated(playerIn, hand);
		if(flag)
		{
			TileEntityHereticCauldron cauldron = getTile(worldIn, pos);
			worldIn.setBlockState(pos, getActualState(state, worldIn, pos), 3);
			cauldron.reactivate(0);
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitZ, hitZ, hitZ);
    }
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;//state.getValue(LIQUID).getMetaId() + state.getValue(LEVEL);
	}
	
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();//this.getDefaultState().withProperty(LEVEL, (meta % 3) + 1).withProperty(LIQUID, EnumHetericCauldronFluidType.getMatch(Math.floorDiv(meta, 3)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {LIQUID, LEVEL});
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!(worldIn.getBlockState(pos.up()).getBlock() instanceof HereticCauldronTop) && worldIn.getBlockState(pos.up()).getBlock().isReplaceable(worldIn, pos.up()))
			worldIn.setBlockState(pos.up(), HarshenBlocks.heretic_cauldron_top.getDefaultState(), 3);
		else
			worldIn.destroyBlock(pos, true);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.getBlockState(pos.up()).getBlock() instanceof HereticCauldronTop)
			worldIn.setBlockToAir(pos.up());
		((TileEntityHereticCauldron)worldIn.getTileEntity(pos)).killRitual();
	}
	
	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }
	
	@Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return blockState.getValue(LEVEL).intValue();
    }
	
	private TileEntityHereticCauldron getTile(World worldIn, BlockPos pos)
	{
		return ((TileEntityHereticCauldron)worldIn.getTileEntity(pos));
	}
	
	private TileEntityHereticCauldron getTile(IBlockAccess worldIn, BlockPos pos)
	{
		return ((TileEntityHereticCauldron)worldIn.getTileEntity(pos));
	}
}
