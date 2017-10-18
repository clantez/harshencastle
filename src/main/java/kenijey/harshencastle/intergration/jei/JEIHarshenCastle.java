package kenijey.harshencastle.intergration.jei;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.containers.ContainerMagicTable;
import kenijey.harshencastle.enchantment.HarshenEnchantmetns;
import kenijey.harshencastle.gui.GuiMagicTable;
import kenijey.harshencastle.intergration.jei.cauldron.JEICauldronCategory;
import kenijey.harshencastle.intergration.jei.cauldron.JEICauldronHandler;
import kenijey.harshencastle.intergration.jei.hereticritual.JEIHereticRitualCategory;
import kenijey.harshencastle.intergration.jei.hereticritual.JEIHereticRitualHandler;
import kenijey.harshencastle.intergration.jei.magictable.JEIMagicTableCategory;
import kenijey.harshencastle.intergration.jei.magictable.JEIMagicTableHandler;
import kenijey.harshencastle.intergration.jei.pedestalslab.JEIPedestalSlabCategory;
import kenijey.harshencastle.intergration.jei.pedestalslab.JEIPedestalSlabHandler;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualCategory;
import kenijey.harshencastle.intergration.jei.ritual.JEIRitualHandler;
import kenijey.harshencastle.internal.HarshenAPIHandler;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@JEIPlugin
public class JEIHarshenCastle implements IModPlugin 
{
	private IModRegistry registry;
	@Override
	public void register(IModRegistry registry) { 
		this.registry = registry;
		registry.addRecipeHandlers(
				new JEIRitualHandler(),
				new JEICauldronHandler(),
				new JEIPedestalSlabHandler(),
				new JEIHereticRitualHandler(),
				new JEIMagicTableHandler());
		
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerMagicTable.class, JEICategoryUIDs.MAGIC_TABLE, 0, 5, 5, 36);
		registry.addRecipeClickArea(GuiMagicTable.class, 105, 77, 59, 34, JEICategoryUIDs.MAGIC_TABLE);
		for(Item item : ForgeRegistries.ITEMS.getValues())
			if(HarshenEnchantmetns.MIXUP.canApply(new ItemStack(item)))
			{
				if(item.isEnchantable(new ItemStack(item)))
					continue;
				ItemStack stack = new ItemStack(item);
				stack.addEnchantment(HarshenEnchantmetns.MIXUP, 1);
				registry.addAnvilRecipe(new ItemStack(item), HarshenUtils.toArray(HarshenUtils.getMixupBook()), HarshenUtils.toArray(stack));
			}		
		
		registry.addRecipes(HarshenAPIHandler.allRitualRecipes, JEICategoryUIDs.RITUAL);
		registry.addRecipes(HarshenAPIHandler.allCauldronRecipes, JEICategoryUIDs.CAULDRON);
		registry.addRecipes(HarshenAPIHandler.allPedestalRecipes, JEICategoryUIDs.PENDESTAL_SLAB);
		registry.addRecipes(HarshenAPIHandler.allHereticCauldronRecipes, JEICategoryUIDs.HERETIC_RITUAL);
		registry.addRecipes(HarshenAPIHandler.allMagicTableRecipes, JEICategoryUIDs.MAGIC_TABLE);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL), JEICategoryUIDs.RITUAL);
		registry.addRecipeCatalyst(new ItemStack(HarshenItems.RITUAL_STICK), JEICategoryUIDs.CAULDRON);
		registry.addRecipeCatalyst(new ItemStack(HarshenItems.RITUAL_STICK, 1, 1), JEICategoryUIDs.HERETIC_RITUAL);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.PEDESTAL_SLAB), JEICategoryUIDs.PENDESTAL_SLAB);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.HARSHEN_MAGIC_TABLE), JEICategoryUIDs.MAGIC_TABLE);
		
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
		registry.addRecipeCategories(new JEIRitualCategory(JEICategoryUIDs.RITUAL, registry));
		registry.addRecipeCategories(new JEICauldronCategory(JEICategoryUIDs.CAULDRON, registry));
		registry.addRecipeCategories(new JEIPedestalSlabCategory(JEICategoryUIDs.PENDESTAL_SLAB, registry));
		registry.addRecipeCategories(new JEIHereticRitualCategory(JEICategoryUIDs.HERETIC_RITUAL, registry));
		registry.addRecipeCategories(new JEIMagicTableCategory(JEICategoryUIDs.MAGIC_TABLE, registry));

	}
}
