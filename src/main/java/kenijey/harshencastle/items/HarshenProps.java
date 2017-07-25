package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.enums.items.EnumProp;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class HarshenProps extends BaseItemMetaData
{
	public HarshenProps()
	{
		setRegistryName("props");
		setUnlocalizedName("prop");
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		return;
	}

	@Override
	protected String[] getNames() {
		return EnumProp.getNames();
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}
}
