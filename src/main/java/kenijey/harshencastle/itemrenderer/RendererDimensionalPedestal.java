package kenijey.harshencastle.itemrenderer;

import java.util.ArrayList;

import kenijey.harshencastle.tileentity.HarshenDimensionalPedestalTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class RendererDimensionalPedestal extends TileEntitySpecialRenderer<HarshenDimensionalPedestalTileEntity>
{
	
	public static EntityItem ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, new ItemStack(Blocks.STONE));
	
	@Override
	public void render(HarshenDimensionalPedestalTileEntity te, double x, double y, double z, float partialTicks,
			int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItem());
		ITEM.hoverStart = 0.0f;
		int rotateAngle = te.getRotation();
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5f,0.65f,0.5f);
			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			GlStateManager.translate(0f,0f,0f);
			if(te.isActive())
			{
				BlockPos pos = te.getMoveDirection();
				if(pos != null)
				{
					float dx = pos.getX() * te.getMove() * 2;
					float dy = 0.5f * te.getMove();
					float dz = pos.getZ() * te.getMove() * 2;
					GlStateManager.translate(dx, dy, dz);
				}
				
			}
			else 
				GlStateManager.rotate(rotateAngle, 0, 1, 0);
			
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0f, 0f, 0f, 0f, 0f, false);
			
		}
		GlStateManager.popMatrix();
	}
	
}
