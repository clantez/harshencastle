package kenijey.harshencastle.intergration.jei.magictable;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.recipies.MagicTableRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIMagicTableHandler extends BaseJeiHandler<MagicTableRecipe> {

	@Override
	public IRecipeWrapper getRecipeWrapper(MagicTableRecipe recipe) {
		return new JEIMagicTableWrapper(recipe);
	}
}
