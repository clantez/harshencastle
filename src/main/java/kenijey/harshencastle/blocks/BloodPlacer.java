package kenijey.harshencastle.blocks;

import java.util.HashMap;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketTileEntityBloodPlacerUpdated;
import kenijey.harshencastle.tileentity.TileEntityBloodVessel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
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
				&& !(worldIn.getBlockState(pos.offset(state.getValue(FACING))).getBlock() instanceof BloodBlock)
				&& !blocksOnMap.containsKey(pos) && worldIn.isSideSolid(pos.offset(state.getValue(FACING)).down(), EnumFacing.UP)
				&& worldIn.getBlockState(pos.up()).getBlock() instanceof BloodVessel 
				&& ((TileEntityBloodVessel)worldIn.getTileEntity(pos.up())).canRemove(1))
		{
			blocksOnMap.put(pos, true);
			((TileEntityBloodVessel)worldIn.getTileEntity(pos.up())).remove(1);
			HarshenNetwork.sendToAll(new MessagePacketTileEntityBloodPlacerUpdated(pos.up(), ((TileEntityBloodVessel)worldIn.getTileEntity(pos.up())).getPossibleRemove()));
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
