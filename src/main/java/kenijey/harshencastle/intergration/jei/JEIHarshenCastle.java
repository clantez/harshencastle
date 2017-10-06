package kenijey.harshencastle.intergration.jei;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.intergration.jei.cauldron.JEICauldronCategory;
import kenijey.harshencastle.intergration.jei.cauldron.JEICauldronHandler;
import kenijey.harshencastle.intergration.jei.hereticritual.JEIHereticRitualCategory;
import kenijey.harshencastle.intergration.jei.hereticritual.JEIHereticRitualHandler;
import kenijey.harshencastle.intergration.jei.pedestalslab.JEIPedestalSlabCategory;
import kenijey.harshencastle.intergration.jei.pedestalslab.JEIPedestalSlabHandler;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualCategory;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualHandler;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIHarshenCastle implements IModPlugin 
{
	private IModRegistry registry;
	@Override
	public void register(IModRegistry registry) { 
		this.registry = registry;
		registry.addRecipeHandlers(new JEIRitualHandler(), new JEICauldronHandler(), new JEIPedestalSlabHandler(), new JEIHereticRitualHandler());
		
		registry.addRecipes(HarshenRecipes.allRitualRecipes, JEICategoryUIDs.ritual);
		registry.addRecipes(HarshenRecipes.allCauldronRecipes, JEICategoryUIDs.cauldron);
		registry.addRecipes(HarshenRecipes.allPedestalRecipes, JEICategoryUIDs.pedestalslab);
		registry.addRecipes(HarshenRecipes.allHereticCauldronRecipes, JEICategoryUIDs.hereticritual);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL), JEICategoryUIDs.ritual);
		registry.addRecipeCatalyst(new ItemStack(HarshenItems.RITUAL_STICK), JEICategoryUIDs.cauldron);
		registry.addRecipeCatalyst(new ItemStack(HarshenItems.RITUAL_STICK, 1, 1), JEICategoryUIDs.hereticritual);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.PEDESTAL_SLAB), JEICategoryUIDs.pedestalslab);
		
		info(HarshenItems.HARSHEN_SOUL_FRAGMENT);
		info(HarshenItems.HARSHEN_CRYSTAL);
		info(HarshenItems.LIGHT_EMITTED_SEED);
		info(HarshenItems.LIGHT_EMITTED_ESSENCE);
		info(HarshenItems.SOUL_HARSHER_PICKAXE);
		info(HarshenItems.PONTUS_WORLD_GATE_PARTS);
		info(HarshenItems.BLOOD_ESSENCE);
		info(HarshenItems.BLOOD_COLLECTOR);
		info(HarshenItems.VALOR_BADGE);
		info(HarshenItems.IRON_HEART);
		info(HarshenItems.LIGHTNING_STAFF);
		
		info(HarshenBlocks.BLOOD_VESSEL);
		info(HarshenBlocks.HARSHEN_SOUL_FLOWER);
		info(HarshenBlocks.BLOOD_BLOCK);
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
		registry.addRecipeCategories(new JEIHereticRitualCategory(JEICategoryUIDs.hereticritual, registry));
	}
}
