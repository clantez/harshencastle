package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.internal.HarshenAPIHandler;
import net.minecraft.item.ItemStack;

public class PedestalSlabRecipes 
{
	private static ArrayList<PedestalSlabRecipes> allRecipes = new ArrayList<PedestalSlabRecipes>();
	
	private final HarshenStack input;
	private final ItemStack output;
	private boolean isFalse;
	
	private PedestalSlabRecipes(HarshenStack input, ItemStack output)
	{
		if(HarshenUtils.isItemFalse(input) || HarshenUtils.isItemFalse(output))
			isFalse = true;
		this.input = input;
		this.output = output;
		allRecipes.add(this);
	}
	
	public static PedestalSlabRecipes getRecipe(ItemStack input) 
	{
		ArrayList<PedestalSlabRecipes> working = new ArrayList<PedestalSlabRecipes>();
		for(PedestalSlabRecipes recipe : allRecipes)
			if(recipe.getInput().containsItem(input))
				return recipe;
		return null;
	}
	
	public HarshenStack getInput() 
	{
		return input;
	}
	
	public ItemStack getOutput() 
	{
		return output;
	}
	
	public static void addRecipe(HarshenStack input, ItemStack output)
	{
		PedestalSlabRecipes recipe = new PedestalSlabRecipes(input, output);
		if(!recipe.isFalse)
			HarshenAPIHandler.allPedestalRecipes.add(recipe);
	}
	
}
