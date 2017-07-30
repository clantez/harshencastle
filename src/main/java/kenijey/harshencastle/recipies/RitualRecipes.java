package kenijey.harshencastle.recipies;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class RitualRecipes {

	private static ArrayList<RitualRecipes> allRecipies = new ArrayList<RitualRecipes>();
	private final List<ItemStack> inputs;
	private final ItemStack output;
	private final boolean useLightning;
	
	private RitualRecipes(List<ItemStack> inputs, ItemStack output, boolean useLightning) 
	{
		if(inputs.size() != 4)
			throw new IllegalArgumentException("input size for ritual recipe was not 4");
		this.inputs = inputs;
		this.output = output;
		this.useLightning = useLightning;
		allRecipies.add(this);
	}
	
	public static ArrayList<RitualRecipes> getRecipes(ItemStack stack)
	{
		ArrayList<RitualRecipes> working = new ArrayList<RitualRecipes>();
		for(RitualRecipes recipe : allRecipies)
			if(recipe.hasItem(stack))
				working.add(recipe);
		return working;
	}
	
	public List<ItemStack> getInputs()
	{
		return inputs;
	}
	
	public ItemStack getOutput()
	{
		return output;
	}
	
	private boolean hasItem(ItemStack stack)
	{
		boolean flag = false;
		for(ItemStack stack1 : this.inputs)
			if(stack.getItem() == stack1.getItem())
				flag = true;
		return flag;
	}
	
	public boolean lightning()
	{
		return this.useLightning;
	}
	
	public static void addRecipe(List<ItemStack> inputs, ItemStack output, boolean useLightning)
	{
		new RitualRecipes(inputs, output, useLightning);
	}
}
