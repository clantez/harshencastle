package kenijey.harshencastle.itemrenderer;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererDimensionalPedestal extends TileEntitySpecialRenderer<TileEntityHarshenDimensionalPedestal>
{
	
	public static EntityItem ITEM;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(TileEntityHarshenDimensionalPedestal te, double x, double y, double z, float partialTicks,
			int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItem());
		ITEM.hoverStart = 0.0f;
		int rotateAngle = te.getTimer() * 6;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5f,0.65f,0.5f);
			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			GlStateManager.translate(0, Math.sin(rotateAngle) / 20f, 0);
			GlStateManager.rotate(rotateAngle % 360 * (te.isActive() ? te.getActiveTimer() / 10f: 1f), 0, 1, 0);
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0f, 0f, 0f, 0f, 0f, false);
			if(te.getItem().getItem() != Items.AIR)
				for(int i = 0; i < 15; i++)
					HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.ITEM, new Vec3d(te.getPos()).addVector(0.5, 0.9 + Math.sin(rotateAngle) / 20f, 0.5), Vec3d.ZERO, 0.85f, false, te.getItem());
		}
		GlStateManager.popMatrix();
	}
	
}
