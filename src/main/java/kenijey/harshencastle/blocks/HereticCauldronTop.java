package kenijey.harshencastle.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import kenijey.harshencastle.HarshenBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HereticCauldronTop extends Block
{

	public HereticCauldronTop() {
		super(Material.IRON);
		setRegistryName("heretic_cauldron_top");
		setUnlocalizedName("heretic_cauldron");
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
    }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.1, 0, 0.3, 0.9, 0.7, 0.7);
    }
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!(worldIn.getBlockState(pos.down()).getBlock() instanceof HereticCauldron) && worldIn.getBlockState(pos.down()).getBlock().isReplaceable(worldIn, pos.up()))
			worldIn.setBlockState(pos.down(), HarshenBlocks.heretic_cauldron.getDefaultState(), 3);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		super.onBlockHarvested(worldIn, pos, state, player);
		if(worldIn.getBlockState(pos.down()).getBlock() instanceof HereticCauldron)
			worldIn.destroyBlock(pos.down(), !player.isCreative());
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return ((HereticCauldron)worldIn.getBlockState(pos.down()).getBlock()).onBlockActivated(worldIn, pos.down(), worldIn.getBlockState(pos.down()), playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

	@Override
    protected boolean canSilkHarvest()
    {
        return false;
    }
	
	 @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

}
