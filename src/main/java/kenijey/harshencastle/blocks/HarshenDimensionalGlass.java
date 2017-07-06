package kenijey.harshencastle.blocks;

import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HarshenDimensionalGlass extends BlockGlass
{

	public HarshenDimensionalGlass() {
		super(Material.ROCK, false);
		setUnlocalizedName("harshen_dimensional_glass");
		setRegistryName("harshen_dimensional_glass");
		setHarvestLevel("pickaxe", 3);
        setHardness(2500.0f);
        setResistance(2500.0f);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) 
	{
			player.attackEntityFrom(DamageSource.MAGIC, 21);
			if(!worldIn.isRemote)
			{
				player.sendMessage((ITextComponent) new TextComponentTranslation("message.broken"));
			}


	}

}
