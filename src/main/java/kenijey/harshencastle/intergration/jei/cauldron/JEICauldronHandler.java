package kenijey.harshencastle.intergration.jei.cauldron;

import javax.annotation.Nonnull;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.recipies.RitualRecipes;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEICauldronHandler extends BaseJeiHandler<RitualRecipes> {

	@Nonnull
	@Override
	public Class<RitualRecipes> getRecipeClass() {
		return RitualRecipes.class;
	}

	@Override
	public String getRecipeCategoryUid(RitualRecipes recipe) {
		return JEICauldronCategory.UID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RitualRecipes recipe) {
		return new JEICauldronWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(RitualRecipes recipe) {
		return recipe.getInputs().size() == 4;
	}

}
