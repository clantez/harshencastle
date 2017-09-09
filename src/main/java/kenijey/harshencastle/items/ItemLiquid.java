package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.enums.CauldronLiquid;
import kenijey.harshencastle.enums.items.EnumItemLiquid;
import net.minecraft.item.ItemStack;

public class ItemLiquid extends BaseItemMetaData
{

	public ItemLiquid() {
		setRegistryName("item_liquid");
		setUnlocalizedName("item_liquid");
	}
	
	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}
	
	public CauldronLiquid getLiquid(ItemStack stack)
	{
		return EnumItemLiquid.getFromMeta(stack.getMetadata()).getFluid();
	}

	@Override
	protected String[] getNames() {
		return EnumItemLiquid.getNames();
	}
	
}
