package kenijey.harshencastle.intergration.jei.magictable;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshencastle.base.BaseJeiWrapper;
import kenijey.harshencastle.recipies.MagicTableRecipe;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEIMagicTableWrapper extends BaseJeiWrapper
{

	private final List<List<ItemStack>> input;
	private final ItemStack output;

	@SuppressWarnings("unchecked")
	public JEIMagicTableWrapper(MagicTableRecipe recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		for(ItemStack stack : recipe.getInputStacks())
			builder.add(ImmutableList.of(stack));
		input = builder.build();
		output = recipe.getOutput();
	}
	

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}

}
