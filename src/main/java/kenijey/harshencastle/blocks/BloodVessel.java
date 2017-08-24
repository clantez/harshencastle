package kenijey.harshencastle.blocks;

import java.util.HashMap;
import java.util.List;

import kenijey.harshencastle.interfaces.IHudDisplay;
import kenijey.harshencastle.tileentity.TileEntityBloodVessel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BloodVessel extends Block implements ITileEntityProvider
{
	public BloodVessel() {
		super(Material.IRON);
		setRegistryName("blood_vessel");
		setUnlocalizedName("blood_vessel");
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBloodVessel();
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
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
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A74" + new TextComponentTranslation("vessel1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
