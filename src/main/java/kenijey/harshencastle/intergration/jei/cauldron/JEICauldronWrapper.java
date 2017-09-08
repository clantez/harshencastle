package kenijey.harshencastle.intergration.jei.cauldron;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshencastle.base.BaseJeiWrapper;
import kenijey.harshencastle.enums.blocks.CauldronLiquid;
import kenijey.harshencastle.recipies.CauldronRecipes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.input.InputHandler;
import mezz.jei.plugins.jei.JEIInternalPlugin;
import mezz.jei.runtime.JeiHelpers;
import mezz.jei.runtime.JeiRuntime;
import net.minecraft.client.Minecraft;
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
	
	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		// TODO Auto-generated method stub
		return super.handleClick(minecraft, mouseX, mouseY, mouseButton);
	}
}
