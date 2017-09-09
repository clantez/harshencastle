package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.enums.CauldronLiquid;
import net.minecraft.item.ItemStack;

public class HereticRitualRecipes 
{
	private static ArrayList<HereticRitualRecipes> allRecipes = new ArrayList<HereticRitualRecipes>();
	
	private final ItemStack cauldronItem;
	private final ItemStack output;
	private final ItemStack[] pedestalItems;
	private final CauldronLiquid catalyst;
	private boolean isFalse;
	
	private HereticRitualRecipes(ItemStack cauldronItem, ItemStack output, CauldronLiquid catalyst, ItemStack... pedstalItems)
	{
		if(pedstalItems.length != 8)
			throw new IllegalArgumentException("input size for ritual recipe was not 8");
		
		for(int i = 0; i < 8; i++)
			if(HarshenUtils.isItemFalse(pedstalItems[i]))
				isFalse = true;
		if(HarshenUtils.isItemFalse(cauldronItem) || HarshenUtils.isItemFalse(output))
			isFalse = true;
		
		this.cauldronItem = cauldronItem;
		this.output = output;
		this.catalyst = catalyst;
		this.pedestalItems = pedstalItems.clone();
		allRecipes.add(this);
	}
	
	public ItemStack[] getPedestalItems() {
		return pedestalItems;
	}
	
	public static HereticRitualRecipes getRecipe(ItemStack cauldronInput, CauldronLiquid fluid, ArrayList<ItemStack> pedestalItems) 
	{
		for(HereticRitualRecipes recipe : allRecipes)
			if(recipe.getCauldronInput().isItemEqual(cauldronInput) && recipe.getCatalyst() == fluid)
			{
				ArrayList<ItemStack> doneItems = new ArrayList<>(pedestalItems);
				stackTestingLoop:
				for(ItemStack stack : recipe.getPedestalItems())
					for(ItemStack stack1 : pedestalItems)
						if(stack1.isItemEqual(stack) && doneItems.contains(stack1))
						{
							doneItems.remove(stack1);
							continue stackTestingLoop;
						}
				if(doneItems.isEmpty())
					return recipe;
			}
				
		return null;
	}
	
	public ItemStack getCauldronInput() 
	{
		return cauldronItem;
	}
	
	public ItemStack getOutput() 
	{
		return output;
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(ItemStack cauldronItem, ItemStack output, CauldronLiquid catalyst, ItemStack... pedstalItems)
	{
		HereticRitualRecipes recipe = new HereticRitualRecipes(cauldronItem, output, catalyst, pedstalItems);
		if(!recipe.isFalse)
			HarshenRecipes.allHereticCauldronRecipes.add(recipe);
	}
	
}
