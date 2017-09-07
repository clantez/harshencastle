package kenijey.harshencastle.itemrenderer;

import java.util.HashMap;
import java.util.Random;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.enums.blocks.EnumHereticCauldronFluidType;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.particle.ParticleCauldronTop;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererHereticCauldron extends TileEntitySpecialRenderer<TileEntityHereticCauldron>
{
	
	public static EntityItem ITEM;
	private boolean switched = false, switchedItem = false;
	public boolean finished = false;
	private HashMap<BlockPos, Particle> particleMap = new HashMap<>();
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(TileEntityHereticCauldron te, double x, double y, double z, float partialTicks,
			int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItem());
		ITEM.hoverStart = 0.0f;
		GlStateManager.pushMatrix();
		{
			if((ParticleCauldronTop)particleMap.get(te.getPos()) != null)
				((ParticleCauldronTop)particleMap.get(te.getPos())).kill();
			if(te.getLevel() > 0)
				particleMap.put(te.getPos(), HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.CAULDRON_LIQUID,  new Vec3d(te.getPos()).addVector(0.5D, 0.05D + te.getLevel() / 4d, 0.5D), Vec3d.ZERO, 3.0001f, true, te.getFluid().getStateOrLoc()));
			
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5f, 1.45f,0.5f);
			boolean flag = true;
			if(te.isActive)
			{
				if(te.getActiveTimer() < 50)
					GlStateManager.translate(0, -te.getActiveTimer() / 40f, 0);

				else 
				{
					GlStateManager.translate(0, -1.225f, 0);
					if(te.getActiveTimer() >= 130)
						GlStateManager.translate(0, ((te.getActiveTimer()-130) / 40f), 0);
					else if(te.getActiveTimer() >= 50)
					{
						ItemStack stack = null;
						if(new Random().nextInt(MathHelper.floor(MathHelper.clamp(130 - te.getActiveTimer(), 1, 130))) <= 2 && !te.getItem().isEmpty())
							stack = te.getItem();
						else if(!te.getSwitchedItem().isEmpty())
							stack = te.getSwitchedItem();
						flag = false;
						for(int i = 0; i < 45; i ++)
						{
							HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.ITEM, 
									new Vec3d(te.getPos()).addVector(0.5d, 0.6d + (te.getActiveTimer() >= 130? (te.getActiveTimer()-130) / 40f : 0), 0.5d), Vec3d.ZERO, 1f, false, stack);
						}
					}
					else if (te.getActiveTimer() <= 105)
						for(int i = 0; i < 15; i ++)
						{
							Vec3d vec = new Vec3d((new Random().nextDouble() - 0.5D) / 1.5D, new Random().nextDouble() / 4D, (new Random().nextDouble() - 0.5D) / 1.5D);
							HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.CAULDRON, new Vec3d(te.getPos()).addVector(0.5d, 0.225d, 0.5d).add(vec), 
									new Vec3d(-vec.x / 20D, -vec.y / 20D, -vec.z / 20D), 1f, false, te.getWorkingFluid().getResourceLoc());
						}
							
				}	
			}
			else
				GlStateManager.translate(0, Math.sin(te.getTimer() / 5f) / 15f, 0);

			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			GlStateManager.rotate(te.getTimer() % 360 * 10, 0, 1, 0);
			if(flag)
				Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0f, 0f, 0f, 0f, 0f, false);
		}
		GlStateManager.popMatrix();
		
	}
	
}
