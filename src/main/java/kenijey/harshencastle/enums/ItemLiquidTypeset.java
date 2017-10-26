package kenijey.harshencastle.enums;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.enums.items.GlassContainerValue;
import kenijey.harshencastle.enums.items.GlassContainerValues;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class ItemLiquidTypeset 
{
	private static ArrayList<ItemLiquidTypeset> allItemSet = new ArrayList<>();
		
	private final String name;
	private final CauldronLiquid type;
	
	private ItemLiquidTypeset(GlassContainerValue glass) 
	{
		if(glass != GlassContainerValues.EMPTY && !HarshenUtils.glassContainerHasBlock(glass))
			new IllegalArgumentException(glass.getName() + " container cant be passed as an itemliquid as it has no item").printStackTrace();
		name = glass.getName() + "ite";
		type = glass.getType();
	}
	
	public static ItemLiquidTypeset initiate(GlassContainerValue glass)
	{
		ItemLiquidTypeset set = new ItemLiquidTypeset(glass);
		allItemSet.add(set);
		return set;
	}
	
	public static ItemLiquidTypeset getFromMeta(int meta)
	{
		for(ItemLiquidTypeset set : allItemSet)
			if(set.getMeta() == meta)
				return set;
		return new ItemLiquidTypeset(GlassContainerValues.EMPTY);
	}
	
	public static int getMetaFromType(CauldronLiquid type)
	{
		for(ItemLiquidTypeset set : allItemSet)
			if(set.getType()== type)
				return set.getMeta();
		return 0;
	}
	
	public static ItemStack getStackFromType(CauldronLiquid type)
	{
		return new ItemStack(HarshenItems.ITEM_LIQUID, 1, getMetaFromType(type));
	}
	
	public String getName() {
		return name;
	}
	
	public static String[] getNames()
	{
		String[] ret = new String[allItemSet.size()];
		for(int i = 0; i < allItemSet.size(); i++)
		{
			ret[i] = ((IBlockState) allItemSet.get(i).getType().getStateOrLoc()).getBlock().getLocalizedName();
			if(ret[i].split(" ").length > 2 && ret[i].split(" ")[0].equalsIgnoreCase("block") && ret[i].split(" ")[1].equalsIgnoreCase("of"))
			{
				String s = "";
				for(int i1 = 2; i1 < ret[i].split(" ").length; i1++)
					s += ret[i].split(" ")[i1];
				ret[i] = s;
			}
		}
		return ret;
	}
	
	public int getMeta()
	{
		return allItemSet.indexOf(this);
	}

	public CauldronLiquid getType() {
		return type;
	}
	
	public static int length()
	{
		return allItemSet.size();
	}
}
