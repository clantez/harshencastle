package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import net.minecraft.item.ItemStack;

public class LargeRitualRecipe 
{
	private static ArrayList<LargeRitualRecipe> allRecipes = new ArrayList<LargeRitualRecipe>();
	
	private final ItemStack cauldronItem;
	private final ItemStack output;
	private final ItemStack[] pedestalItems;
	private final EnumHetericCauldronFluidType catalyst;
	
	private LargeRitualRecipe(ItemStack cauldronItem, ItemStack output, EnumHetericCauldronFluidType catalyst, ItemStack... pedstalItems)
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
	
	public static LargeRitualRecipe getRecipe(ItemStack cauldronInput, EnumHetericCauldronFluidType fluid, ArrayList<ItemStack> pedestalItems) 
	{
		ArrayList<LargeRitualRecipe> working = new ArrayList<LargeRitualRecipe>();
		for(LargeRitualRecipe recipe : allRecipes)
			if(recipe.getCauldronInput().isItemEqual(cauldronInput) && recipe.getCatalyst() == fluid)
			{
				boolean flag = false;
				ArrayList<ItemStack> doneItems = new ArrayList<>();
				stackTestingLoop:
				for(ItemStack stack : recipe.getPedestalItems())
					for(ItemStack stack1 : pedestalItems)
						if(stack1.isItemEqual(stack))
						{
							doneItems.add(stack);
							continue stackTestingLoop;
						}
				if(!flag && doneItems.size() == pedestalItems.size())
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
	
	public EnumHetericCauldronFluidType getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(ItemStack cauldronItem, ItemStack output, EnumHetericCauldronFluidType catalyst, ItemStack... pedstalItems)
	{
		HarshenRecipes.allLargeRitualRecipes.add(new LargeRitualRecipe(cauldronItem, output, catalyst, pedstalItems));
	}
	
}
