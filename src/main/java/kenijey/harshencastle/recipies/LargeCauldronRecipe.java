package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.internal.HarshenAPIHandler;
import net.minecraft.item.ItemStack;

public class LargeCauldronRecipe 
{
	private static ArrayList<LargeCauldronRecipe> allRecipes = new ArrayList<LargeCauldronRecipe>();
	
	private final ItemStack output;
	private final ArrayList<HarshenStack> inputStacks;
	private final CauldronLiquid catalyst;
	private boolean isFalse;
	private final int cauldronLevel;
	
	private LargeCauldronRecipe(ItemStack output, CauldronLiquid catalyst, int cauldronLevel, HarshenStack... pedstalItems)
	{
		ArrayList<HarshenStack> stackList = new ArrayList<>();
		for(HarshenStack stack : pedstalItems)
			if(HarshenUtils.isItemFalse(stack))
				isFalse = true;
			else
				stackList.add(stack);
		if(HarshenUtils.isItemFalse(output))
			isFalse = true;
		
		this.output = output;
		this.catalyst = catalyst;
		this.cauldronLevel = cauldronLevel;
		this.inputStacks = stackList;
		allRecipes.add(this);
	}
	
	public ArrayList<HarshenStack> getInputStacks() {
		return inputStacks;
	}
	
	public static LargeCauldronRecipe getRecipe(CauldronLiquid fluid, int cauldronLevel, int fluidLevel, ArrayList<ItemStack> inputItems) 
	{
		inputItems = new ArrayList<>(inputItems);
		for(LargeCauldronRecipe recipe : allRecipes)
			if(recipe.getCatalyst() == fluid && HarshenUtils.areHStacksEqual(recipe.getInputStacks(), inputItems) && recipe.getInputStacks().size() == inputItems.size()
					&& cauldronLevel >= recipe.getCauldronLevel() && fluidLevel >= recipe.getCauldronLevel()*recipe.getCauldronLevel()*recipe.getCauldronLevel())
				return recipe;
		return null;
	}

	
	public ItemStack getOutput() 
	{
		return output;
	}
	
	public int getCauldronLevel() {
		return cauldronLevel;
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(ItemStack output, CauldronLiquid catalyst, int cauldronLevel, HarshenStack... pedstalItems)
	{
		LargeCauldronRecipe recipe = new LargeCauldronRecipe(output, catalyst, cauldronLevel, pedstalItems);
		if(!recipe.isFalse)
			HarshenAPIHandler.allLargeCauldronRecipes.add(recipe);
	}
	
	
	
}
