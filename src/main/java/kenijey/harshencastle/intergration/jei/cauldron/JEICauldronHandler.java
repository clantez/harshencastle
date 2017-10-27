package kenijey.harshencastle.intergration.jei.cauldron;

import javax.annotation.Nonnull;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.intergration.jei.JEICategoryUIDs;
import kenijey.harshencastle.recipies.CauldronRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEICauldronHandler extends BaseJeiHandler<CauldronRecipes> {

	@Override
	public IRecipeWrapper getRecipeWrapper(CauldronRecipes recipe) {
		return new JEICauldronWrapper(recipe);
	}
	
	
}
