package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.itemenum.EnumBloodCollectorHandler.BloodLevels;
import kenijey.harshencastle.itemenum.EnumRitualCrystalItemHandler.CrystalAcive;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

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
		for(int i = 0; i < CrystalAcive.values().length; i++)
			items.add(new ItemStack(this, 1, i));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		for(int i = 0; i < CrystalAcive.values().length; i ++)
			if(stack.getItemDamage() == i)
				return this.getUnlocalizedName() + "." + CrystalAcive.values()[i].getName();
		return this.getUnlocalizedName() + "." + CrystalAcive.values()[0].getName();
	}
}
