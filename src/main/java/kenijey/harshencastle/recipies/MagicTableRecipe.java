package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.item.ItemStack;

public class MagicTableRecipe 
{	
	
	private final ArrayList<ItemStack> inputStacks;
	private final ItemStack output;
	private boolean isFalse;
	
	private MagicTableRecipe(ArrayList<ItemStack> inputStacks, ItemStack output) {
		for(ItemStack stack : inputStacks)
			if(HarshenUtils.isItemFalse(stack) || HarshenUtils.isItemFalse(output))
				isFalse = true;
		this.inputStacks = inputStacks;
		this.output = output;
	}
	
	public static MagicTableRecipe getRecipeFromStacks(ArrayList<ItemStack> stacks)
	{
		for(MagicTableRecipe recipe : HarshenRecipes.allMagicTableRecipes)
			if(HarshenUtils.areInputsEqual(recipe.getInputStacks(), stacks))
				return recipe;
		return null;
	}
	
	public ArrayList<ItemStack> getInputStacks() {
		return inputStacks;
	}
	
	public ItemStack getOutput() {
		return output;
	}
	
	public static void addRecipe(ArrayList<ItemStack> inputStacks, ItemStack output)
	{
		MagicTableRecipe recipe = new MagicTableRecipe(inputStacks, output);
		if(!recipe.isFalse)
			HarshenRecipes.allMagicTableRecipes.add(recipe);
	}
}
