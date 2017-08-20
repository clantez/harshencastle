package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import net.minecraft.item.ItemStack;

public class CauldronRecipes 
{
	private static ArrayList<CauldronRecipes> allRecipes = new ArrayList<CauldronRecipes>();
	
	private final ItemStack input;
	private final ItemStack output;
	private final EnumHetericCauldronFluidType catalyst;
	
	private CauldronRecipes(ItemStack input, ItemStack output, EnumHetericCauldronFluidType catalyst)
	{
		this.input = input;
		this.output = output;
		this.catalyst = catalyst;
		allRecipes.add(this);
	}
	
	public static CauldronRecipes getRecipe(ItemStack input) 
	{
		ArrayList<CauldronRecipes> working = new ArrayList<CauldronRecipes>();
		for(CauldronRecipes recipe : allRecipes)
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
	
	public EnumHetericCauldronFluidType getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(ItemStack input, ItemStack output, EnumHetericCauldronFluidType catalyst)
	{
		HarshenRecipes.allCauldronRecipes.add(new CauldronRecipes(input, output, catalyst));
	}
	
}
