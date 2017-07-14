package kenijey.harshencastle.itemrenderer;

import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererHereticCauldron extends TileEntitySpecialRenderer<TileEntityHereticCauldron>
{
	
	public static EntityItem ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, new ItemStack(Blocks.STONE));
	
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
			GlStateManager.translate(0.5f,0.65f,0.5f);
			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0f, 0f, 0f, 0f, 0f, false);
			
		}
		GlStateManager.popMatrix();
	}
	
}
