package kenijey.harshencastle.blocks;

import kenijey.harshencastle.base.BaseHarshenBlockCastle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HarshenDimensionalWoodCrate extends BaseHarshenBlockCastle
{
	public HarshenDimensionalWoodCrate()
	{
		super(Material.WOOD);
        setUnlocalizedName("harshen_dimensional_wood_crate");
        setRegistryName("harshen_dimensional_wood_crate");
        blockSoundType = blockSoundType.WOOD;
	}
}
