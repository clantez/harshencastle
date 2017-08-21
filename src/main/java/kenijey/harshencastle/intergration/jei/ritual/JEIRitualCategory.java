package kenijey.harshencastle.intergration.jei.ritual;

import java.awt.Dimension;
import java.util.List;

import javax.annotation.Nonnull;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseJeiCategory;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class JEIRitualCategory extends BaseJeiCategory
{	
	public JEIRitualCategory(String UID, IRecipeCategoryRegistration reg) {
		super(UID, reg);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEIRitualWrapper))
			return;
		JEIRitualWrapper wrapper = (JEIRitualWrapper) recipeWrapper;
		for(int i = 0; i < 4; i++)
			addSlot(recipeLayout, ingredients.getInputs(ItemStack.class), i);
		recipeLayout.getItemStacks().init(4, false, 129, 46);
		recipeLayout.getItemStacks().set(4, ingredients.getOutputs(ItemStack.class).get(0));
	}
	
	Dimension[] positionsOfSlots = {new Dimension(21, 6), new Dimension(56, 6), new Dimension(12, 26), new Dimension(69, 22)};
	
	private void addSlot(IRecipeLayout recipeLayout, List<List<ItemStack>> list, int id)
	{
		recipeLayout.getItemStacks().init(id, true, positionsOfSlots[id].width, positionsOfSlots[id].height);
		recipeLayout.getItemStacks().set(id, list.get(id));
	}

}
