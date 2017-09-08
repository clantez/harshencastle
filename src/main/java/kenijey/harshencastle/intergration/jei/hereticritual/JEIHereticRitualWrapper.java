package kenijey.harshencastle.intergration.jei.hereticritual;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshencastle.base.BaseJeiWrapper;
import kenijey.harshencastle.enums.blocks.CauldronLiquid;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEIHereticRitualWrapper extends BaseJeiWrapper	
{
	private final List<List<ItemStack>> input;
	private final ItemStack output;
	private final ItemStack cauldronInput;
	private final CauldronLiquid catalyst;
	private final List<List<ItemStack>> allInputs;

	@SuppressWarnings("unchecked")
	public JEIHereticRitualWrapper(HereticRitualRecipes recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		for(Object o : recipe.getPedestalItems())
			builder.add(ImmutableList.of((ItemStack) o));
		input = builder.build();
		output = recipe.getOutput();
		cauldronInput = recipe.getCauldronInput();
		builder.add(ImmutableList.of(cauldronInput));
		allInputs = builder.build();
		catalyst = recipe.getCatalyst();
	}
	

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, allInputs);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
}
