package kenijey.harshencastle.intergration.jei.magictable;

import javax.annotation.Nonnull;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.intergration.jei.JEICategoryUIDs;
import kenijey.harshencastle.recipies.MagicTableRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIMagicTableHandler extends BaseJeiHandler<MagicTableRecipe> {

	@Nonnull
	@Override
	public Class<MagicTableRecipe> getRecipeClass() {
		return MagicTableRecipe.class;
	}

	@Override
	public String getRecipeCategoryUid(MagicTableRecipe recipe) {
		return JEICategoryUIDs.CAULDRON;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(MagicTableRecipe recipe) {
		return new JEIMagicTableWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(MagicTableRecipe recipe) {
		return true;
	}
}
