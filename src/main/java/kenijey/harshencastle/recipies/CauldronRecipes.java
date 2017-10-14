package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.enums.CauldronLiquid;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import net.minecraft.item.ItemStack;

public class CauldronRecipes 
{
	private static ArrayList<CauldronRecipes> allRecipes = new ArrayList<CauldronRecipes>();
	
	private final HarshenStack input;
	private final ItemStack output;
	private final CauldronLiquid catalyst;
	private boolean isFalse = false;
	
	private CauldronRecipes(HarshenStack input, ItemStack output, CauldronLiquid catalyst)
	{
		if(HarshenUtils.isItemFalse(input) || HarshenUtils.isItemFalse(output))
			isFalse = true;
		this.input = input;
		this.output = output;
		this.catalyst = catalyst;
		allRecipes.add(this);
	}
	
	public static CauldronRecipes getRecipe(ItemStack input, CauldronLiquid fluid) 
	{
		ArrayList<CauldronRecipes> working = new ArrayList<CauldronRecipes>();
		for(CauldronRecipes recipe : allRecipes)
			if(recipe.getInput().containsItem(input) && recipe.getCatalyst() == fluid)
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
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(HarshenStack input, ItemStack output, EnumGlassContainer type)
	{
		CauldronRecipes recipe = new CauldronRecipes(input, output, type.getType());
		if(!recipe.isFalse)
			HarshenRecipes.allCauldronRecipes.add(recipe);
	}
	
}
