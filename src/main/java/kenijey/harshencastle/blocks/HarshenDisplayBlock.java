package kenijey.harshencastle.blocks;

import java.util.HashMap;
import java.util.Random;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseBlockHarshenSingleInventory;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class HarshenDisplayBlock extends BaseBlockHarshenSingleInventory
{
	private static HashMap<BlockPos, Boolean> creativeBreakMap = new HashMap<>(HarshenUtils.HASH_LIMIT);


	public HarshenDisplayBlock() {
		super(Material.ROCK);
		setRegistryName("harshen_display_block");
		setUnlocalizedName("harshen_display_block");
		setHardness(25.0F);
		setResistance(25.0F);
	}

	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityHarshenDisplayBlock();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		ItemStackHandler handlerStack = new ItemStackHandler(1);
		handlerStack.deserializeNBT(stack.serializeNBT().getCompoundTag("tag").getCompoundTag("ItemStackHandler"));
		((TileEntityHarshenDisplayBlock)worldIn.getTileEntity(pos)).setItem(handlerStack.getStackInSlot(0));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return playerIn.capabilities.isCreativeMode ? super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ) : false;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		BaseTileEntityHarshenSingleItemInventory te = (BaseTileEntityHarshenSingleItemInventory) worldIn.getTileEntity(pos);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		worldIn.removeTileEntity(pos);
		ItemStackHandler handlerStack = new ItemStackHandler(1);
		handlerStack.setStackInSlot(0, handler.getStackInSlot(0));
		ItemStack stack = new ItemStack(this);
		if(handlerStack.getStackInSlot(0).getItem() != Item.getItemFromBlock(Blocks.AIR))
		{
			NBTTagCompound nbttagcompound = new NBTTagCompound();
	        nbttagcompound.setTag("ItemStackHandler", handlerStack.serializeNBT());
	        stack.setTagCompound(nbttagcompound);
	        stack.setStackDisplayName("§r" + getLocalizedName() + " (" + I18n.translateToLocal(handlerStack.getStackInSlot(0).getItem().getUnlocalizedName() + ".name") + ")");
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
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
}
