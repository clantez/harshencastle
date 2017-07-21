package kenijey.harshencastle.items;

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
		setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		return;
	}

	@Override
	protected String[] getNames() {
		return EnumProp.getNames();
	}
}