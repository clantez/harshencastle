package kenijey.harshencastle.skyrenders;

import java.util.Random;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BasePontusResourceBiome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.IRenderHandler;

public class WeatherPontus extends IRenderHandler
{    
	private final float[] rainXCoords = new float[1024];
    private final float[] rainYCoords = new float[1024];
    private int update = 0;
	private Random random;
    
	public WeatherPontus() {
		random = new Random();
		for (int i = 0; i < 32; ++i)
        {
            for (int j = 0; j < 32; ++j)
            {
                float f = (float)(j - 16);
                float f1 = (float)(i - 16);
                float f2 = MathHelper.sqrt(f * f + f1 * f1);
                this.rainXCoords[i << 5 | j] = -f1 / f2;
                this.rainYCoords[i << 5 | j] = f / f2;
            }
        }
	}

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) 
	{
		update += 1f;
        Entity entity = mc.getRenderViewEntity();
        int i = MathHelper.floor(entity.posX);
        int j = MathHelper.floor(entity.posY);
        int k = MathHelper.floor(entity.posZ);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.disableCull();
        GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.alphaFunc(516, 0.1F);
        double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
        double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
        double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
        int l = MathHelper.floor(d1);
        int j1 = -1;
        float f1 = update + partialTicks;
        bufferbuilder.setTranslation(-d0, -d1, -d2);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int t = 15;
        for (int k1 = k - t; k1 <= k + t; ++k1)
        {
            for (int l1 = i - t; l1 <= i + t; ++l1)
            {
                int i2 = (k1 - k + 16) * 32 + l1 - i + 16;
                double d3 = (double)this.rainXCoords[i2] * 0.5D;
                double d4 = (double)this.rainYCoords[i2] * 0.5D;
                blockpos$mutableblockpos.setPos(l1, 0, k1);
                BasePontusResourceBiome biome = (BasePontusResourceBiome) world.getBiome(blockpos$mutableblockpos);
                int j2 = 0;
                int i1 = 25;
                int k2 = j - i1;
                int l2 = j + i1;

                if (k2 < j2)
                {
                    k2 = j2;
                }

                if (l2 < j2)
                {
                    l2 = j2;
                }

                int i3 = j2;

                if (j2 < l)
                {
                    i3 = l;
                }
                this.random.setSeed((long)(l1 * l1 * 3121 + l1 * 45238971 ^ k1 * k1 * 418711 + k1 * 13761));
                blockpos$mutableblockpos.setPos(l1, k2, k1);
                if(!HarshenUtils.isLevelAcceptable(mc.world, blockpos$mutableblockpos, mc.player))
                {
                	if (j1 != 1)
                    {
                        if (j1 >= 0)
                        {
                            tessellator.draw();
                        }
                        mc.getTextureManager().bindTexture(new ResourceLocation(HarshenCastle.MODID, "textures/biome-walls/" + biome.getBiomeName().toLowerCase() + ".png"));
                        j1 = 1;
                        bufferbuilder.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
                    }
                    double d8 = (double)(-((float)(update & 511) + partialTicks) / 512.0F) * biome.scrollDownSpeed();
                    double d9 = 0.5 + (double)f1 * 0.01D * biome.scrollAcrossSpeed();
                    double d10 = 0.5 + (double)(f1 * 0.5) * 0.001D;
                    double d11 = (double)((float)l1 + 0.5F) - entity.posX;
                    double d12 = (double)((float)k1 + 0.5F) - entity.posZ;
                    float f6 = MathHelper.sqrt(d11 * d11 + d12 * d12) / (float)i1;
                    blockpos$mutableblockpos.setPos(l1, i3, k1);
                    float f5 = 1;
                    int i4 = world.getCombinedLight(blockpos$mutableblockpos, 0);
                    int j4 = i4 >> 16 & 65535;
                    int k4 = i4 & 65535;
                    bufferbuilder.pos((double)l1 - d3 + 0.5D, (double)l2, (double)k1 - d4 + 0.5D).tex(0.0D + d9, (double)k2 * 0.25D + d8 + d10).color(1.0F, 1.0F, 1.0F, f5).lightmap(j4, k4).endVertex();
                    bufferbuilder.pos((double)l1 + d3 + 0.5D, (double)l2, (double)k1 + d4 + 0.5D).tex(1.0D + d9, (double)k2 * 0.25D + d8 + d10).color(1.0F, 1.0F, 1.0F, f5).lightmap(j4, k4).endVertex();
                    bufferbuilder.pos((double)l1 + d3 + 0.5D, (double)k2, (double)k1 + d4 + 0.5D).tex(1.0D + d9, (double)l2 * 0.25D + d8 + d10).color(1.0F, 1.0F, 1.0F, f5).lightmap(j4, k4).endVertex();
                    bufferbuilder.pos((double)l1 - d3 + 0.5D, (double)k2, (double)k1 - d4 + 0.5D).tex(0.0D + d9, (double)l2 * 0.25D + d8 + d10).color(1.0F, 1.0F, 1.0F, f5).lightmap(j4, k4).endVertex();
                }
            }
        }

        if (j1 >= 0)
        {
            tessellator.draw();
        }

        bufferbuilder.setTranslation(0.0D, 0.0D, 0.0D);
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc(516, 0.1F);
	}
	
}