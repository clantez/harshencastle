package kenijey.harshencastle.itemrenderer;

import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererHarshenDisplayBlock extends TileEntitySpecialRenderer<TileEntityHarshenDisplayBlock>
{
	
	public static EntityItem ITEM;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(TileEntityHarshenDisplayBlock te, double x, double y, double z, float partialTicks,
			int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItem());
		ITEM.hoverStart = 0.0f;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5f, 0.9f,0.5f);
			GlStateManager.scale(2, 2, 2);
			GlStateManager.translate(0, Math.sin(te.getTimer() / 10f) / 15f, 0);
			GlStateManager.rotate(te.getTimer() % 360 * 5, 0, 1, 0);
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0f, 0f, 0f, 0f, 0f, false);
			
		}
		GlStateManager.popMatrix();
	}
	
}
