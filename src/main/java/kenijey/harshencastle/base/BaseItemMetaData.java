package kenijey.harshencastle.base;

import java.util.ArrayList;
import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public abstract class BaseItemMetaData extends Item
{
	
	/**
	 * Leave null for all
	 */
	protected abstract List<Integer> getMetaForTab();
	
	public BaseItemMetaData() {
		setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if(tab.equals(HarshenCastle.harshenTab) || tab.equals(CreativeTabs.SEARCH))
			if(getMetaForTab() == null)
				for(int i = 0; i < getNames().length; i++)
					items.add(new ItemStack(this, 1, i));
			else
				for(int i : getMetaForTab())
					items.add(new ItemStack(this, 1, i));
	}
	
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for(int i = 0; i < getNames().length; i ++)
			if(stack.getItemDamage() == i)
				return this.getUnlocalizedName() + "." + getNames()[i];
		return this.getUnlocalizedName() + "." + getNames()[0];
	}
	
	protected abstract String[] getNames();
	
}
