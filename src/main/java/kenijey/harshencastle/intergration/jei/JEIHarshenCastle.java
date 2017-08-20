package kenijey.harshencastle.intergration.jei;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.intergration.jei.cauldron.JEICauldronCategory;
import kenijey.harshencastle.intergration.jei.cauldron.JEICauldronHandler;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualCategory;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualHandler;
import kenijey.harshencastle.recipies.CauldronRecipes;
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
		registry.addRecipeHandlers(new JEIRitualHandler(), new JEICauldronHandler());
		
		registry.addRecipes(HarshenRecipes.allRitualRecipes, JEICategoryUIDs.ritual);
		registry.addRecipes(HarshenRecipes.allCauldronRecipes, JEICategoryUIDs.cauldron);
		
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.harshen_dimensional_pedestal), JEICategoryUIDs.ritual);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.heretic_cauldron), JEICategoryUIDs.cauldron);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new JEIRitualCategory(JEICategoryUIDs.ritual, registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new JEICauldronCategory(JEICategoryUIDs.cauldron, registry.getJeiHelpers().getGuiHelper()));

	}
}
