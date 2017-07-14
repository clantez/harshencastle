package kenijey.harshencastle.blocks;

import java.util.List;
import java.util.Random;

import kenijey.harshencastle.HarshenItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PlantOfGleam extends BlockFlower
{
	
	public PlantOfGleam() {
		setUnlocalizedName("plant_of_gleam");
        setRegistryName("plant_of_gleam");
        blockSoundType = blockSoundType.PLANT;
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25f, 0f, 0.25f, 0.75f, 0.8125f, 0.75f);
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		int[] luck = {50,60,80,100};
		if(rand.nextInt(101) <= luck[fortune])
			return HarshenItems.light_emitted_seed;
		return null;
	}
	

	@Override
	public EnumFlowerColor getBlockType() {
		return EnumFlowerColor.YELLOW;
	}
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
	        return true;
	}
}
