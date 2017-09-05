package kenijey.harshencastle.blocks;

import java.util.HashMap;

import kenijey.harshencastle.HarshenUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockOfHeads extends Block
{	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private static HashMap<BlockPos, Boolean> blocksOnMap = new HashMap<>(HarshenUtils.HASH_LIMIT);
	
	public BlockOfHeads()
	{
		super(Material.ROCK);
		setRegistryName("block_of_heads");
		setUnlocalizedName("block_of_heads");
		setHardness(5.0F);
		setResistance(5.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
	
	@Override
    public IBlockState getStateFromMeta(int meta) {
    	for(EnumFacing facing : EnumFacing.HORIZONTALS)
    		if(facing.getHorizontalIndex() == meta)
    			return this.getDefaultState().withProperty(FACING, facing);
    	return this.getDefaultState();
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
    	return state.getValue(FACING).getHorizontalIndex();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }	

}
