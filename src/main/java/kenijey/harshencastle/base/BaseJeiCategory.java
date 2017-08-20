package kenijey.harshencastle.base;

import javax.annotation.Nonnull;

import kenijey.harshencastle.HarshenCastle;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public abstract class BaseJeiCategory implements IRecipeCategory{

	public static String UID = "harshencastle.";
	private final IDrawable background;
	private final String localizedName;
	private final IDrawable overlay;

	public BaseJeiCategory(String name, IGuiHelper guiHelper) {
		UID += name;
		background = guiHelper.createBlankDrawable(150, 110);
		localizedName = I18n.format("jei." + UID + ".name");
		overlay = guiHelper.createDrawable(new ResourceLocation(HarshenCastle.MODID, "textures/gui/jei/" + UID.split("\\.")[UID.split("\\.").length-1] + ".png"),
				0, 0, 150, 110);
	}
	
	@Override
	public String getUid() {
		return UID;
	}

	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public String getModName() {
		return HarshenCastle.MODID;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void drawExtras(@Nonnull Minecraft minecraft) {
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		overlay.draw(minecraft);
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();
	}
}
	
