package kenijey.harshencastle.blocks;

import java.util.Random;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.items.HarshenItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class HarshenDimensionalDirt extends HarshenBlockCastle
{
	public HarshenDimensionalDirt() 
	{
		super(Material.GRASS, "shovel");
        setUnlocalizedName("harshen_dimensional_dirt");
        setRegistryName("harshen_dimensional_dirt");
        blockSoundType = blockSoundType.GROUND;
        setTickRandomly(true);
    }
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		if(worldIn.getBlockState(pos.add(0, 1, 0)).getBlock() instanceof BlockBush)
			worldIn.setBlockState(pos.add(0, 1, 0), HarshenBlocks.harshen_dimensional_glass.getDefaultState(), 2);
		if(worldIn.getBlockState(pos.add(0, 2, 0)).getBlock() instanceof BlockFlower)
			worldIn.setBlockState(pos.add(0, 2, 0),Blocks.AIR.getDefaultState(), 2);
	}
	
	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		return true;
	}
	
}
