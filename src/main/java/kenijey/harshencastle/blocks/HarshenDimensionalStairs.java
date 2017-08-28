package kenijey.harshencastle.blocks;

import kenijey.harshencastle.HarshenBlocks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HarshenDimensionalStairs extends BlockStairs
{

	public HarshenDimensionalStairs() {
		super(HarshenBlocks.harshen_dimensional_stone.getDefaultState());
		setUnlocalizedName("harshen_dimensional_stairs");
		setRegistryName("harshen_dimensional_stairs");
		setHarvestLevel("pickaxe", 3);
        setHardness(3000.0f);
        setResistance(3000.0f);
		useNeighborBrightness = true;
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) 
	{
		if(player.capabilities.isCreativeMode)
		{
			super.onBlockHarvested(worldIn, pos, state, player);
			return;
		}
		player.attackEntityFrom(DamageSource.MAGIC, 21);
		if(!worldIn.isRemote)
		{
			player.sendMessage((ITextComponent) new TextComponentTranslation("message.broken"));
		}


	}
	
}
