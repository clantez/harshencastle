package kenijey.harshencastle.intergration.jei;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualCategory;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualHandler;
import kenijey.harshencastle.recipies.HarshenRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIHarshenCastle implements IModPlugin 
{
	@Override
	public void register(IModRegistry registry) { 
		registry.addRecipeHandlers(new JEIRitualHandler());
		
		registry.addRecipes(HarshenRecipes.allRitualRecipes, JEIRitualCategory.UID);
		
		
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.harshen_dimensional_pedestal), JEIRitualCategory.UID);;
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new JEIRitualCategory("ritual", registry.getJeiHelpers().getGuiHelper()));
	}
}
