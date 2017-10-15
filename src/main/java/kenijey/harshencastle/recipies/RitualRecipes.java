package kenijey.harshencastle.recipies;

import java.util.ArrayList;
import java.util.List;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.internal.HarshenAPIHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualRecipes {

	private static ArrayList<RitualRecipes> allRecipies = new ArrayList<RitualRecipes>();
	private final List<HarshenStack> inputs;
	private final ItemStack output;
	private final boolean useLightning;
	private BlockPos positionOfRitual;
	private boolean isFalse;
	private String tag;
	
	private RitualRecipes(List<HarshenStack> inputs, ItemStack output, boolean useLightning) 
	{
		if(inputs.size() != 4)
			throw new IllegalArgumentException("input size for ritual recipe was not 4");
		for(int i = 0; i < 4; i++)
			if(HarshenUtils.isItemFalse(inputs.get(i)))
				isFalse = true;
		if(!HarshenUtils.isItemAvalible(output))
			isFalse = true;
		
		this.inputs = new ArrayList<HarshenStack>(inputs);
		this.output = output.copy();
		this.useLightning = useLightning;
		allRecipies.add(this);
	}
	
	private RitualRecipes(List<HarshenStack> inputs, ItemStack output, boolean useLightning, BlockPos position) 
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
	
	public List<HarshenStack> getInputs() {
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
		for(HarshenStack hStack : this.inputs)
			for(ItemStack stack1 : hStack.getStackList())
				if(stack.isItemEqual(stack1))
					flag = true;
		return flag;
	}
	
	public boolean lightning()
	{
		return this.useLightning;
	}
	
	public static void addRecipe(List<HarshenStack> inputs, ItemStack output, boolean useLightning)
	{
		RitualRecipes recipe = new RitualRecipes(inputs, output, useLightning);
		if(!recipe.isFalse)
			HarshenAPIHandler.allRitualRecipes.add(recipe);
	}
}
