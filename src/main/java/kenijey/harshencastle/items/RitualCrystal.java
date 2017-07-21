package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.enums.items.EnumRitualCrystal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class RitualCrystal extends Item
{		
	public RitualCrystal() {
		setRegistryName("ritual_crystal");
		setUnlocalizedName("ritual_crystal");
		this.setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if(tab.equals(HarshenCastle.harshenTab))
			for(int i = 0; i < EnumRitualCrystal.values().length; i++)
				items.add(new ItemStack(this, 1, i));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		for(int i = 0; i < EnumRitualCrystal.values().length; i ++)
			if(stack.getItemDamage() == i)
				return this.getUnlocalizedName() + "." + EnumRitualCrystal.values()[i].getName();
		return this.getUnlocalizedName() + "." + EnumRitualCrystal.values()[0].getName();
	}
}
