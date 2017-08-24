package kenijey.harshencastle.blocks;

import java.util.HashMap;
import java.util.List;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.tileentity.TileEntityBloodVessel;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class BloodVessel extends Block implements ITileEntityProvider
{
	
	private static HashMap<BlockPos, Boolean> creativeBreakMap = new HashMap<>(HarshenUtils.HASH_LIMIT);
	
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		((TileEntityBloodVessel)worldIn.getTileEntity(pos)).setBloodLevel(stack.serializeNBT().getInteger("BloodLevel"));
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityBloodVessel te = (TileEntityBloodVessel) worldIn.getTileEntity(pos);
		int amount = te.getPossibleRemove();
		int max = te.getMax();
		worldIn.removeTileEntity(pos);
		ItemStack stack = new ItemStack(this);
		if(amount != 0)
		{
			NBTTagCompound nbttagcompound = new NBTTagCompound();
	        nbttagcompound.setInteger("BloodLevel", amount);
	        stack.setTagCompound(nbttagcompound);
	        stack.setStackDisplayName("§r" + getLocalizedName() + " (" + amount + "/" + max + ")");
		}
		if(!creativeBreakMap.get(pos))
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack));
		creativeBreakMap.remove(pos);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		creativeBreakMap.put(pos, player.capabilities.isCreativeMode);
		super.onBlockHarvested(worldIn, pos, state, player);
	}

	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A74" + new TextComponentTranslation("vessel1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
