package kenijey.harshencastle.intergration.jei.cauldron;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshencastle.base.BaseJeiCategory;
import kenijey.harshencastle.base.BaseJeiWrapper;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import kenijey.harshencastle.recipies.CauldronRecipes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class JEICauldronWrapper extends BaseJeiWrapper
{

	private final List<List<ItemStack>> input;
	private final ItemStack output;
	private final EnumHetericCauldronFluidType catalyst;

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
	
	public EnumHetericCauldronFluidType getCatalyst() 
	{
		return catalyst;
	}


}
