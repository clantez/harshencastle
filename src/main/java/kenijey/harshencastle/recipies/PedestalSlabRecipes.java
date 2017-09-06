package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenUtils;
import net.minecraft.item.ItemStack;

public class PedestalSlabRecipes 
{
	private static ArrayList<PedestalSlabRecipes> allRecipes = new ArrayList<PedestalSlabRecipes>();
	
	private final ItemStack input;
	private final ItemStack output;
	private boolean isFalse;
	
	private PedestalSlabRecipes(ItemStack input, ItemStack output)
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
			if(recipe.getInput().isItemEqual(input))
				return recipe;
		return null;
	}
	
	public ItemStack getInput() 
	{
		return input;
	}
	
	public ItemStack getOutput() 
	{
		return output;
	}
	
	public static void addRecipe(ItemStack input, ItemStack output)
	{
		PedestalSlabRecipes recipe = new PedestalSlabRecipes(input, output);
		if(!recipe.isFalse)
			HarshenRecipes.allPedestalRecipes.add(recipe);
	}
	
}
