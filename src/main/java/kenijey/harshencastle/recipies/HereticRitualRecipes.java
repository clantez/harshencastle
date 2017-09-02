package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.enums.blocks.EnumHereticCauldronFluidType;
import net.minecraft.item.ItemStack;

public class HereticRitualRecipes 
{
	private static ArrayList<HereticRitualRecipes> allRecipes = new ArrayList<HereticRitualRecipes>();
	
	private final ItemStack cauldronItem;
	private final ItemStack output;
	private final ItemStack[] pedestalItems;
	private final EnumHereticCauldronFluidType catalyst;
	
	private HereticRitualRecipes(ItemStack cauldronItem, ItemStack output, EnumHereticCauldronFluidType catalyst, ItemStack... pedstalItems)
	{
		if(pedstalItems.length != 8)
			throw new IllegalArgumentException("input size for ritual recipe was not 8");
		this.cauldronItem = cauldronItem;
		this.output = output;
		this.catalyst = catalyst;
		this.pedestalItems = pedstalItems.clone();
		allRecipes.add(this);
	}
	
	public ItemStack[] getPedestalItems() {
		return pedestalItems;
	}
	
	public static HereticRitualRecipes getRecipe(ItemStack cauldronInput, EnumHereticCauldronFluidType fluid, ArrayList<ItemStack> pedestalItems) 
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
	
	public EnumHereticCauldronFluidType getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(ItemStack cauldronItem, ItemStack output, EnumHereticCauldronFluidType catalyst, ItemStack... pedstalItems)
	{
		HarshenRecipes.allHereticCauldronRecipes.add(new HereticRitualRecipes(cauldronItem, output, catalyst, pedstalItems));
	}
	
}
