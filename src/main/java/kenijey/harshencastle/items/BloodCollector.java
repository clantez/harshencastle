package kenijey.harshencastle.items;

import kenijey.harshencastle.itemenum.EnumBloodCollectorHandler.BloodLevels;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class BloodCollector extends Item
{
	
	private int blood;
	private int[] change = {12, 24, 36, 48, 60};
		
	public BloodCollector() {
		setRegistryName("blood_collector");
		setUnlocalizedName("blood_collector");
		this.setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
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
	
	public boolean fill()
	{
		return blood++ <= 60;
	}
	
	private void checkForMetaChange()
	{
		
	}
}
