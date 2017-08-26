package kenijey.harshencastle.intergration.jei;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.intergration.jei.cauldron.JEICauldronCategory;
import kenijey.harshencastle.intergration.jei.cauldron.JEICauldronHandler;
import kenijey.harshencastle.intergration.jei.pedestalslab.JEIPedestalSlabCategory;
import kenijey.harshencastle.intergration.jei.pedestalslab.JEIPedestalSlabHandler;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualCategory;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualHandler;
import kenijey.harshencastle.items.HarshenCrystal;
import kenijey.harshencastle.items.HarshenSoulFragment;
import kenijey.harshencastle.recipies.HarshenRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIHarshenCastle implements IModPlugin 
{
	private IModRegistry registry;
	@Override
	public void register(IModRegistry registry) { 
		this.registry = registry;
		registry.addRecipeHandlers(new JEIRitualHandler(), new JEICauldronHandler(), new JEIPedestalSlabHandler());
		
		registry.addRecipes(HarshenRecipes.allRitualRecipes, JEICategoryUIDs.ritual);
		registry.addRecipes(HarshenRecipes.allCauldronRecipes, JEICategoryUIDs.cauldron);
		registry.addRecipes(HarshenRecipes.allPedestalRecipes, JEICategoryUIDs.pedestalslab);
		
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.harshen_dimensional_pedestal), JEICategoryUIDs.ritual);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.heretic_cauldron), JEICategoryUIDs.cauldron);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.pedestal_slab), JEICategoryUIDs.pedestalslab);
		
		info(HarshenItems.harshen_soul_fragment);
		info(HarshenItems.harshen_crystal);
		info(HarshenItems.light_emitted_essence);
		info(HarshenItems.light_emitted_seed);
		info(HarshenItems.soul_harsher_pickaxe);
		info(HarshenItems.pontus_world_gate_parts);
		info(HarshenItems.blood_essence);
		
		info(HarshenBlocks.harshen_soul_flower);
		info(HarshenBlocks.blood_block);
	}

	private void info(Block block)
	{
		registry.addDescription(new ItemStack(block), "jei." + block.getRegistryName().getResourcePath() + ".description");
	}
	
	private void info(Item item)
	{
		registry.addDescription(new ItemStack(item), "jei." + item.getRegistryName().getResourcePath() + ".description");
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new JEIRitualCategory(JEICategoryUIDs.ritual, registry));
		registry.addRecipeCategories(new JEICauldronCategory(JEICategoryUIDs.cauldron, registry));
		registry.addRecipeCategories(new JEIPedestalSlabCategory(JEICategoryUIDs.pedestalslab, registry));

	}
}
