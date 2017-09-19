package kenijey.harshencastle.recipies;

import java.util.ArrayList;
import java.util.List;

import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualRecipes {

	private static ArrayList<RitualRecipes> allRecipies = new ArrayList<RitualRecipes>();
	private final List<ItemStack> inputs;
	private final ItemStack output;
	private final boolean useLightning;
	private BlockPos positionOfRitual;
	private boolean isFalse;
	private String tag;
	
	private RitualRecipes(List<ItemStack> inputs, ItemStack output, boolean useLightning) 
	{
		if(inputs.size() != 4)
			throw new IllegalArgumentException("input size for ritual recipe was not 4");
		for(int i = 0; i < 4; i++)
			if(HarshenUtils.isItemFalse(inputs.get(i)))
				isFalse = true;
		if(HarshenUtils.isItemFalse(output))
			isFalse = true;
		
		this.inputs = inputs;
		this.output = output;
		this.useLightning = useLightning;
		allRecipies.add(this);
	}
	
	private RitualRecipes(List<ItemStack> inputs, ItemStack output, boolean useLightning, BlockPos position) 
	{
		this(inputs, output, useLightning);
		this.positionOfRitual = position;
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
	
	public RitualRecipes setTag(String tag)
	{
		this.tag = tag;
		return this;
	}
	
	public String getTag() {
		return tag;
	}
	
	public RitualRecipes setUpRitual(World world, BlockPos position)
	{
		return new RitualRecipes(inputs, output, useLightning, position).setTag(HarshenUtils.getTagLine(world, position, "lightning_"));
	}
	
	public BlockPos getPositionOfRitual() {
		return positionOfRitual;
	}
	
	public ItemStack getOutput()
	{
		return output;
	}
	
	private boolean hasItem(ItemStack stack)
	{
		boolean flag = false;
		for(ItemStack stack1 : this.inputs)
			if(stack.isItemEqual(stack1))
				flag = true;
		return flag;
	}
	
	public boolean lightning()
	{
		return this.useLightning;
	}
	
	public static void addRecipe(List<ItemStack> inputs, ItemStack output, boolean useLightning)
	{
		RitualRecipes recipe = new RitualRecipes(inputs, output, useLightning);
		if(!recipe.isFalse)
			HarshenRecipes.allRitualRecipes.add(recipe);
	}
}
