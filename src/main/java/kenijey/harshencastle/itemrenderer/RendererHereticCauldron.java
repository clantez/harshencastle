package kenijey.harshencastle.itemrenderer;

import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererHereticCauldron extends TileEntitySpecialRenderer<TileEntityHereticCauldron>
{
	
	public static EntityItem ITEM;
	private boolean switched = false, switchedItem = false;
	public boolean finished = false;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(TileEntityHereticCauldron te, double x, double y, double z, float partialTicks,
			int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItem());
		ITEM.hoverStart = 0.0f;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5f, 1.45f,0.5f);
			if(te.isActive)
			{
				if(te.getActiveTimer() < 50)
					GlStateManager.translate(0, -te.getActiveTimer() / 40f, 0);

				else 
				{
					GlStateManager.translate(0, -1.225f, 0);
					if(te.getActiveTimer() >= 130)
						GlStateManager.translate(0, ((te.getActiveTimer()-130) / 40f), 0);
				}	
			}
			else
				GlStateManager.translate(0, Math.sin(te.getTimer() / 5f) / 15f, 0);

			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			GlStateManager.rotate(te.getTimer() % 360 * 10, 0, 1, 0);
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0f, 0f, 0f, 0f, 0f, false);
			
		}
		GlStateManager.popMatrix();
	}
	
}
