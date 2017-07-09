package kenijey.harshencastle.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HarshenDimensionalGate extends Block
{

	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	
	public HarshenDimensionalGate() {
		super(Material.ROCK);
		setRegistryName("harshen_dimensional_gate");
		setUnlocalizedName("harshen_dimensional_gate");
		this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, Boolean.valueOf(false)));
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0625f, 0.0625f, 0.0625f, 0.9375f, 0.9375f, 0.9375f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 0f, 0f, 1, 0.0625, 0.0625));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 0f, 0f, 0.0625, 0.0625, 1));
		addCollisionBoxToList(pos, entityBox, collidingBoxes,  new AxisAlignedBB(0f, 0f, 0f, 0.0625, 0.0625, 1));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 0f, 0.9375f, 1f, 0.0625, 1f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.9375f, 0f, 0f, 1f, 0.0625, 1f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 1f, 0f, 1,0.9375f, 0.0625));
		addCollisionBoxToList(pos, entityBox, collidingBoxes,  new AxisAlignedBB(0f, 1f, 0f, 0.0625, 0.9375f, 1));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 1f, 0.9375f, 1f, 0.9375f, 1f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.9375f, 1f, 0f, 1f, 0.9375f, 1f));
								
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(ACTIVE, Boolean.valueOf(meta == 1));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((Boolean)state.getValue(ACTIVE)).booleanValue() ? 1 : 0;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {ACTIVE});
    }

}
