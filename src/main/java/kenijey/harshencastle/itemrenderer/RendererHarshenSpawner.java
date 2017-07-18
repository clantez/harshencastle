package kenijey.harshencastle.itemrenderer;

import kenijey.harshencastle.tileentity.TileEntityHarshenSpawner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererHarshenSpawner extends TileEntitySpecialRenderer<TileEntityHarshenSpawner>
{
	
	public static EntityItem ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, new ItemStack(Blocks.STONE));
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(TileEntityHarshenSpawner te, double x, double y, double z, float partialTicks,
			int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItem());
		ITEM.hoverStart = 0.0f;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5f,0.35f,0.5f);
			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			GlStateManager.rotate(te.getTimer() % 360 * 10f, 0, 1, 0);
			
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0f, 0f, 0f, 0f, 0f, false);
			
		}
		GlStateManager.popMatrix();
	}
	
}
