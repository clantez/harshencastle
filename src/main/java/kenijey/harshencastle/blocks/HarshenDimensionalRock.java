package kenijey.harshencastle.blocks;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.items.HarshenItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HarshenDimensionalRock extends HarshenBlockCastle 
{
	public HarshenDimensionalRock() 
	{
        setUnlocalizedName("harshen_dimensional_rock");
        setRegistryName("harshen_dimensional_rock");
    }
}
