package kenijey.harshencastle.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HarshenDimensionalWoodCrate extends Block
{
	public HarshenDimensionalWoodCrate()
	{
		super(Material.WOOD);
        setUnlocalizedName("harshen_dimensional_wood_crate");
        setRegistryName("harshen_dimensional_wood_crate");
        setHarvestLevel("axe", 3);
        setHardness(2500.0f);
        setResistance(2500.0f);
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
