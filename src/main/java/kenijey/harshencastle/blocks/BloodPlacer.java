package kenijey.harshencastle.blocks;

import java.util.HashMap;
import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.interfaces.IHudDisplay;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BloodPlacer extends BlockHorizontal
{
	
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static HashMap<BlockPos, Boolean> blocksOnMap = new HashMap<>(HarshenUtils.HASH_LIMIT);
	
	public BloodPlacer() {
		super(Material.ROCK);
		setRegistryName("blood_placer");
		setUnlocalizedName("blood_placer");
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(worldIn.isBlockPowered(pos) && worldIn.getBlockState(pos.offset(state.getValue(FACING))).getBlock().canPlaceBlockAt(worldIn, pos.offset(state.getValue(FACING)))
				&& !blocksOnMap.containsKey(pos) && worldIn.isSideSolid(pos.offset(state.getValue(FACING)).down(), EnumFacing.UP))
		{
			blocksOnMap.put(pos, true);
			worldIn.setBlockState(pos.offset(state.getValue(FACING)), HarshenBlocks.blood_block.getDefaultState(), 3);
		}
		else if(!worldIn.isBlockPowered(pos) && blocksOnMap.containsKey(pos))
			blocksOnMap.remove(pos);
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
