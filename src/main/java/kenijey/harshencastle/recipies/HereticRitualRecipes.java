package kenijey.harshencastle.recipies;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.enums.CauldronLiquid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HereticRitualRecipes 
{
	private static ArrayList<HereticRitualRecipes> allRecipes = new ArrayList<HereticRitualRecipes>();
	
	private final ItemStack cauldronItem;
	private final ItemStack output;
	private final ArrayList<ItemStack> pedestalItems;
	private final CauldronLiquid catalyst;
	private boolean isFalse;
	private String tag;
	
	private HereticRitualRecipes(ItemStack cauldronItem, ItemStack output, CauldronLiquid catalyst, ItemStack... pedstalItems)
	{
		if(pedstalItems.length != 8)
			throw new IllegalArgumentException("input size for ritual recipe was not 8");
		ArrayList<ItemStack> stackList = new ArrayList<>();
		for(int i = 0; i < 8; i++)
			if(HarshenUtils.isItemFalse(pedstalItems[i]))
				isFalse = true;
			else
				stackList.add(pedstalItems[i]);
		if(HarshenUtils.isItemFalse(cauldronItem) || HarshenUtils.isItemFalse(output))
			isFalse = true;
		
		this.cauldronItem = cauldronItem;
		this.output = output;
		this.catalyst = catalyst;
		this.pedestalItems = stackList;
		allRecipes.add(this);
	}
	
	public ArrayList<ItemStack> getPedestalItems() {
		return pedestalItems;
	}
	
	public static HereticRitualRecipes getRecipe(ItemStack cauldronInput, CauldronLiquid fluid, ArrayList<ItemStack> pedestalItems) 
	{
		for(HereticRitualRecipes recipe : allRecipes)
			if(recipe.getCauldronInput().isItemEqual(cauldronInput) && recipe.getCatalyst() == fluid && HarshenUtils.areInputsEqual(recipe.getPedestalItems(), pedestalItems))
				return recipe;
		return null;
	}

	public ItemStack getCauldronInput() 
	{
		return cauldronItem;
	}
	
	public ItemStack getOutput() 
	{
		return output;
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(ItemStack cauldronItem, ItemStack output, CauldronLiquid catalyst, ItemStack... pedstalItems)
	{
		HereticRitualRecipes recipe = new HereticRitualRecipes(cauldronItem, output, catalyst, pedstalItems);
		if(!recipe.isFalse)
			HarshenRecipes.allHereticCauldronRecipes.add(recipe);
	}
	
	public HereticRitualRecipes setTag(String tag)
	{
		this.tag = tag;
		return this;
	}
	
	public String getTag() {
		return tag;
	}
	
	public HereticRitualRecipes setUp(World world, BlockPos position)
	{
		return this.setTag(HarshenUtils.getTagLine(world, position, "heretic_cauldron"));
	}
	
}
