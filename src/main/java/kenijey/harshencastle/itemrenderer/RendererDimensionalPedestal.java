package kenijey.harshencastle.itemrenderer;

import kenijey.harshencastle.tileentity.HarshenDimensionalPedestalTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RendererDimensionalPedestal extends TileEntitySpecialRenderer<HarshenDimensionalPedestalTileEntity>
{
	
	public static EntityItem ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, new ItemStack(Blocks.STONE));
	
	@Override
	public void render(HarshenDimensionalPedestalTileEntity te, double x, double y, double z, float partialTicks,
			int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItem());
		ITEM.hoverStart = 0.0f;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.75f,-0.25f,0.75f);
			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			GlStateManager.translate(0f,0f,0f);
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0f, 0f, 0f, 0f, 0f, false);
			
		}
		GlStateManager.popMatrix();
	}
	
}
