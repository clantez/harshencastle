package kenijey.harshencastle.itemrenderer;

import java.util.Random;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
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
			for(int i = 0; i < 15; i ++)
			{
				Vec3d vec = new Vec3d((new Random().nextDouble() - 0.5D) / 1.5D, new Random().nextDouble() / 4D, (new Random().nextDouble() - 0.5D) / 1.5D);
				HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.BLOOD, new Vec3d(te.getPos()).addVector(0.5d, 1, 0.5d).add(vec), Vec3d.ZERO,
						1f, false, EnumHetericCauldronFluidType.values()[new Random().nextInt(EnumHetericCauldronFluidType.values().length)].getResourceLoc());
			}

		}
		GlStateManager.popMatrix();
	}
	
}
