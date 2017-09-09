package kenijey.harshencastle.intergration.jei.cauldron;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseJeiCategory;
import kenijey.harshencastle.enums.CauldronLiquid;
import kenijey.harshencastle.items.GlassContainer;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class JEICauldronCategory extends BaseJeiCategory
{
	public JEICauldronCategory(String UID, IRecipeCategoryRegistration reg) {
		super(UID, reg);
	}

	private static HashMap<CauldronLiquid, IDrawable> fluidTypes = new HashMap<>();
	
	private IDrawable currentFluid;
	private IDrawable frontOfCauldron;
	private String name;
	private CauldronLiquid catalyst;

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEICauldronWrapper))
			return;
		JEICauldronWrapper wrapper = (JEICauldronWrapper) recipeWrapper;
		catalyst = wrapper.getCatalyst();
		if(!catalyst.hasState())
			currentFluid = fluidTypes.get(catalyst);
		else
			currentFluid = null;
		name = GlassContainer.getGlassContaining(catalyst);
		recipeLayout.getItemStacks().init(0, true, 66, 0);
		recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(0));
		recipeLayout.getItemStacks().init(1, false, 66, 29);
		recipeLayout.getItemStacks().set(1, Arrays.asList(new ItemStack(HarshenItems.ritual_stick)));
		recipeLayout.getItemStacks().init(2, false, 130, 46);
		recipeLayout.getItemStacks().set(2, ingredients.getOutputs(ItemStack.class).get(0));
	}
	
	@Override
	protected void createDrawable(IGuiHelper helper) {
		for(CauldronLiquid fluid : CauldronLiquid.allLiquids)
			if(!fluid.hasState())
				fluidTypes.put(fluid, helper.createDrawable((ResourceLocation) fluid.getStateOrLoc(), 0, 0, 38, 14, 38, 14));
		
		frontOfCauldron = helper.createDrawable(new ResourceLocation(HarshenCastle.MODID, "textures/gui/jei/cauldron-front.png"), 0, 0, 150, 110, 150, 110);
	}
	
	@Override
	protected void drawMore(Minecraft minecraft) {
		if(currentFluid != null)
			currentFluid.draw(minecraft, 56, 49);
		else
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			TextureAtlasSprite textureSprite = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture((IBlockState) catalyst.getStateOrLoc());
			Minecraft.getMinecraft().currentScreen.drawTexturedModalRect(56, 49, textureSprite, 38, 14);
		}

		frontOfCauldron.draw(minecraft);

	}
	
	@Override
	public List getTooltipStrings(int mouseX, int mouseY) {
		if(mouseX > 56 && mouseX < 94 && mouseY > 49 && mouseY < 63)
			return Arrays.asList(new TextComponentTranslation(name).getFormattedText());
		return super.getTooltipStrings(mouseX, mouseY);
	}

}
