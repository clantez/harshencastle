package kenijey.harshencastle.intergration.jei.ritual;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.base.BaseJeiWrapper;
import kenijey.harshencastle.recipies.RitualRecipes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEIRitualWrapper extends BaseJeiWrapper
{

	private final List<List<ItemStack>> input;
	private final ItemStack output;

	@SuppressWarnings("unchecked")
	public JEIRitualWrapper(RitualRecipes recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		for(HarshenStack hStack : recipe.getInputs())
			builder.add(hStack.getStackList());
		input = builder.build();
		output = recipe.getOutput();
	}
	

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}


}
