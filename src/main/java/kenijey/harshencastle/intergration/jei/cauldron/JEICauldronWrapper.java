package kenijey.harshencastle.intergration.jei.cauldron;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseJeiWrapper;
import kenijey.harshencastle.enums.CauldronLiquid;
import kenijey.harshencastle.enums.ItemLiquidTypeset;
import kenijey.harshencastle.recipies.CauldronRecipes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEICauldronWrapper extends BaseJeiWrapper
{

	private final List<List<ItemStack>> input;
	private final ItemStack output;
	private final CauldronLiquid catalyst;

	@SuppressWarnings("unchecked")
	public JEICauldronWrapper(CauldronRecipes recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		builder.add(ImmutableList.of(recipe.getInput()));
		if(HarshenUtils.glassContainerHasBlock(recipe.getCatalyst()))
			builder.add(ImmutableList.of(new ItemStack(HarshenItems.item_liquid, 1, ItemLiquidTypeset.getMetaFromType(recipe.getCatalyst()))));
		builder.add(ImmutableList.of(new ItemStack(HarshenBlocks.heretic_cauldron)));
		input = builder.build();
		output = recipe.getOutput();
		catalyst = recipe.getCatalyst();
	}
	

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
}
