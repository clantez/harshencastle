package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.enums.items.EnumBloodCollectorHandler.BloodLevels;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class BloodCollector extends Item
{		
	public BloodCollector() {
		setRegistryName("blood_collector");
		setUnlocalizedName("blood_collector");
		this.setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if(tab.equals(HarshenCastle.harshenTab))
			items.add(new ItemStack(this, 1, 0));	
	}
	
	
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		for(int i = 0; i < BloodLevels.values().length; i ++)
			if(stack.getItemDamage() == i)
				return this.getUnlocalizedName() + "." + BloodLevels.values()[i].getName();
		return this.getUnlocalizedName() + "." + BloodLevels.ZERO.getName();
	}
	
	public boolean fill(World world, EntityPlayer player, EnumHand hand)
	{
		if(world.isRemote)
			return false;
		
		boolean flag = false; 
		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound nbt;
		if (stack.hasTagCompound())
	        nbt = stack.getTagCompound();
	    else
	    	nbt = new NBTTagCompound();

		if (nbt.hasKey("Blood"))
			if(nbt.getInteger("Blood") < 50)
			{
				nbt.setInteger("Blood", nbt.getInteger("Blood") + 1);
				flag = true;
			}
			else;
		else
		{
			nbt.setInteger("Blood", 1);
			flag = true;
		}
		
		for(int i = 0; i < BloodLevels.values().length; i ++)
		{
			if(BloodLevels.values()[i].getAmount() <= nbt.getInteger("Blood") && (i + 1 == BloodLevels.values().length || BloodLevels.values()[i + 1].getAmount() > nbt.getInteger("Blood")))
				stack.setItemDamage(i);	
		}
			
        stack.setTagCompound(nbt);
        player.setHeldItem(hand, stack);
        
		return flag;
	}
	
	public void remove(int amount)
	{
		//TODO
	}
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Blood"))
			tooltip.add("Blood: " + Integer.toString(stack.getTagCompound().getInteger("Blood")) + " / 50");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
