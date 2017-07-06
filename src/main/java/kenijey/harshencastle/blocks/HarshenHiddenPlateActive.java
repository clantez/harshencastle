package kenijey.harshencastle.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HarshenHiddenPlateActive extends Block
{
	
	public HarshenHiddenPlateActive() {
		super(Material.ROCK);
		setTickRandomly(true);
        setUnlocalizedName("harshen_dimensional_plate_active");
        setRegistryName("harshen_dimensional_plate_active");
        setHarvestLevel("pickaxe", 3);
        setHardness(2500.0f);
        setResistance(2500.0f);
		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		for(EnumFacing face : EnumFacing.VALUES)
			worldIn.notifyNeighborsOfStateChange(pos.offset(facing), this, false);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		worldIn.setBlockState(pos, HarshenBlocks.harshen_hidden_plate.getDefaultState(), 3);
		super.updateTick(worldIn, pos, state, rand);
	}
	
	 public boolean canProvidePower(IBlockState state)
	 {
		 return true;
	 }

	 public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	 {
		 return 15;
	 }
	 
	 @Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(HarshenBlocks.harshen_hidden_plate);
	}
	
	
	
	


}
