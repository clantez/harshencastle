package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import net.minecraft.item.ItemStack;

public class PedestalSlabRecipes 
{
	private static ArrayList<PedestalSlabRecipes> allRecipes = new ArrayList<PedestalSlabRecipes>();
	
	private final ItemStack input;
	private final ItemStack output;
	
	private PedestalSlabRecipes(ItemStack input, ItemStack output)
	{
		this.input = input;
		this.output = output;
		allRecipes.add(this);
	}
	
	public static PedestalSlabRecipes getRecipe(ItemStack input) 
	{
		ArrayList<PedestalSlabRecipes> working = new ArrayList<PedestalSlabRecipes>();
		for(PedestalSlabRecipes recipe : allRecipes)
			if(recipe.getInput().getItem() == input.getItem())
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
		HarshenRecipes.allPedestalRecipes.add(new PedestalSlabRecipes(input, output));
	}
	
}
