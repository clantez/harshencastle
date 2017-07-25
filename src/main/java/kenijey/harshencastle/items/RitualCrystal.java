package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.enums.items.EnumRitualCrystal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class RitualCrystal extends BaseItemMetaData
{		
	public RitualCrystal() {
		setRegistryName("ritual_crystal");
		setUnlocalizedName("ritual_crystal");
		this.setHasSubtypes(true);
	}
	
	@Override
	protected String[] getNames() {
		return EnumRitualCrystal.getNames();
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}
	
}
