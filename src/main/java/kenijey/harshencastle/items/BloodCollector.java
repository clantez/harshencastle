package kenijey.harshencastle.items;

import java.util.List;
import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.blocks.BloodVessel;
import kenijey.harshencastle.enums.items.EnumBloodCollector;
import kenijey.harshencastle.tileentity.TileEntityBloodVessel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloodCollector extends BaseItemMetaData
{		

	public BloodCollector() {
		setRegistryName("blood_collector");
		setUnlocalizedName("blood_collector");
	}
	
	public boolean fill(EntityPlayer player, EnumHand hand, int amount)
	{
		boolean flag = false; 
		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound nbt = getNBT(stack);

		if(nbt.getInteger("Blood") + amount <= 50)
		{
			nbt.setInteger("Blood", nbt.getInteger("Blood") + amount);
			flag = true;
		}
		stack.setItemDamage(metaChange(nbt));
        stack.setTagCompound(nbt);
        player.setHeldItem(hand, stack);
		return flag;
	}
	
	public boolean remove(EntityPlayer player, EnumHand hand, int amount)
	{

		if(player.capabilities.isCreativeMode)
			return true;
		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound nbt = getNBT(stack);
		if(nbt.getInteger("Blood") - amount < 0)
			return false;
		nbt.setInteger("Blood", nbt.getInteger("Blood") - amount);
		stack.setItemDamage(metaChange(nbt));
		return true;
	}
	
	private NBTTagCompound getNBT(ItemStack stack)
	{
		NBTTagCompound nbt;
		if (stack.hasTagCompound())
	        nbt = stack.getTagCompound();
	    else
	    	nbt = new NBTTagCompound();
		
		if (!nbt.hasKey("Blood"))
			nbt.setInteger("Blood", 0);

		return nbt;
	}
	
	private int metaChange(NBTTagCompound nbt)
	{
		for(int i = 0; i < EnumBloodCollector.values().length; i ++)
		{
			if(EnumBloodCollector.values()[i].getAmount() <= nbt.getInteger("Blood") && (i + 1 == EnumBloodCollector.values().length || EnumBloodCollector.values()[i + 1].getAmount() > nbt.getInteger("Blood")))
				return i;
		}
		return 0;
	}
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A74" + "Blood: " + Integer.toString(getNBT(stack).getInteger("Blood")) + " / 50");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		boolean flag = false;
		if(worldIn.getBlockState(pos).getBlock() instanceof BloodVessel)
		{
			TileEntityBloodVessel vessel = ((TileEntityBloodVessel)worldIn.getTileEntity(pos));
			switch (hand) {
			case MAIN_HAND:
				int tileEntityRemove = player.isSneaking() ? getNBT(player.getHeldItem(hand)).getInteger("Blood") : 1;
				if(vessel.canAdd(tileEntityRemove) && remove(player, hand, tileEntityRemove))
					vessel.change(tileEntityRemove);
				else if(vessel.canAdd(vessel.getPossibleAdd()) && remove(player, hand, vessel.getPossibleAdd()))
					vessel.change(vessel.getPossibleAdd());
				break;
			default:
				int tileEntityRemoveOffHand = player.isSneaking() ? Math.min(vessel.getPossibleRemove(), 50 - getNBT(player.getHeldItem(hand)).getInteger("Blood")) : 1;
				if(vessel.canRemove(tileEntityRemoveOffHand) && fill(player, hand, tileEntityRemoveOffHand))
					vessel.change(-tileEntityRemoveOffHand);
				break;
			}
		}
		else if(!flag && player.isSneaking() && worldIn.getBlockState(pos.offset(facing).down()).isSideSolid(worldIn, pos, EnumFacing.UP) && remove(player, hand, 1))
		{	
			worldIn.setBlockState(pos.offset(facing), HarshenBlocks.BLOOD_BLOCK.getDefaultState(), 3);
			worldIn.getBlockState(pos.offset(facing)).getBlock().onBlockAdded(worldIn, pos.offset(facing), worldIn.getBlockState(pos.offset(facing))); 
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), HarshenSounds.bloodCollectorUse, SoundCategory.BLOCKS, 1f, new Random().nextFloat(), false);
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	protected String[] getNames() {
		return EnumBloodCollector.getNames();
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return HarshenUtils.toArray(0);
	}
}
