package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.enums.blocks.EnumHereticCauldronFluidType;
import net.minecraft.item.ItemStack;

public class CauldronRecipes 
{
	private static ArrayList<CauldronRecipes> allRecipes = new ArrayList<CauldronRecipes>();
	
	private final ItemStack input;
	private final ItemStack output;
	private final EnumHereticCauldronFluidType catalyst;
	private boolean isFalse = false;
	
	private CauldronRecipes(ItemStack input, ItemStack output, EnumHereticCauldronFluidType catalyst)
	{
		if(HarshenUtils.isItemFalse(input) || HarshenUtils.isItemFalse(output))
			isFalse = true;
		this.input = input;
		this.output = output;
		this.catalyst = catalyst;
		allRecipes.add(this);
	}
	
	public static CauldronRecipes getRecipe(ItemStack input, EnumHereticCauldronFluidType fluid) 
	{
		ArrayList<CauldronRecipes> working = new ArrayList<CauldronRecipes>();
		for(CauldronRecipes recipe : allRecipes)
			if(recipe.getInput().isItemEqual(input) && recipe.getCatalyst() == fluid)
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
	
	public EnumHereticCauldronFluidType getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(ItemStack input, ItemStack output, EnumHereticCauldronFluidType catalyst)
	{
		CauldronRecipes recipe = new CauldronRecipes(input, output, catalyst);
		if(!recipe.isFalse)
			HarshenRecipes.allCauldronRecipes.add(recipe);
	}
	
}
