package kenijey.harshencastle.base;

import kenijey.harshencastle.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public abstract class BaseHarshenParticle extends Particle
{
	private boolean disableMoving;
	private ResourceLocation location;
	
	protected abstract int getXIndex();
	protected abstract int getYIndex();
	
    public BaseHarshenParticle(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn, float par14, boolean disableMoving, ResourceLocation location)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
        
        this.particleTextureIndexX = getXIndex();
        this.particleTextureIndexY = getYIndex();
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += motionXIn;
        this.motionY += motionYIn;
        this.motionZ += motionZIn;
        this.particleScale *= 0.75F;
        this.particleScale *= par14;
        this.particleMaxAge = (int)((8.0D / (Math.random() * 0.8D + 0.2D)) * 8);
        this.particleMaxAge = (int)(this.particleMaxAge * par14);
        this.particleAge = (particleMaxAge / 2) + (particleMaxAge / 2) * world.rand.nextInt(7);
        this.particleAlpha = 1.0F;
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.canCollide = false;
        this.disableMoving = disableMoving;
        this.location = location;
    }
    
	@Override
    public int getFXLayer()
    {
        return 3;
    }
		
    @Override
    public void renderParticle(BufferBuilder buffer, Entity entity, float partialTicks, float rotX, float rotZ, float rotYZ, float rotXY, float rotXZ)
    {		
        GlStateManager.disableLighting();
        float f2 = 0.125F;
        float f3 = (float)(this.posX - interpPosX);
        float f4 = (float)(this.posY - interpPosY);
        float f5 = (float)(this.posZ - interpPosZ);
        float f6 = getBrightnessForRender(partialTicks);
        float size = 0.1F * this.particleScale;
        Minecraft.getMinecraft().getTextureManager().bindTexture(location);
        float k = (float)this.particleTextureIndexX / 16.0F;
        float k1 = k + 0.0624375F;
        float k2 = (float)this.particleTextureIndexY / 16.0F;
        float k3 = k2 + 0.0624375F;
        float k4 = 0.1F * this.particleScale;
        int i = this.getBrightnessForRender(partialTicks);
        int ij = i >> 16 & 65535;
        int ik = i & 65535;
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos((double)(f3 - rotX * size- rotXY * size), (double)f4 - rotZ * size, (double)(f5 - rotYZ * size - rotXZ * size)).tex((double)k1, (double)k3).color(f6, f6, f6, 1).endVertex();
        buffer.pos((double)(f3 - rotX * size + rotXY * size), (double)f4 + rotZ * size, (double)(f5 - rotYZ * size + rotXZ * size)).tex((double)k1, (double)k2).color(f6, f6, f6, 1).endVertex();
        buffer.pos((double)(f3 + rotX * size + rotXY * size), (double)f4 + rotZ * size, (double)(f5 + rotYZ * size + rotXZ * size)).tex((double)k, (double)k2).color(f6, f6, f6, 1).endVertex();
        buffer.pos((double)(f3 + rotX * size - rotXY * size), (double)f4 - rotZ * size, (double)(f5 + rotYZ * size - rotXZ * size)).tex((double)k, (double)k3).color(f6, f6, f6, 1).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.enableLighting();

    }
    
    @Override
    public int getBrightnessForRender(float p_189214_1_)
    {
    	BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
        return this.world.isBlockLoaded(blockpos) ? getLightCombonation(blockpos.add(0, 1, 0), 0) : 0;
    }
    
    private int getLightCombonation(BlockPos pos, int lightValue)
    {
    	int i = world.getLightFromNeighborsFor(EnumSkyBlock.SKY, pos);
        int j = world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, pos);

        if (j < lightValue)
        {
            j = lightValue;
        }
        return i << 20 | j << 4;
    }
    
    @Override
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        if (particleAge++ >= particleMaxAge)
        {
            this.setExpired();
        }

        this.particleTextureIndexX = 7 - particleAge * 8 / particleMaxAge;
        if(disableMoving)
    	{
        	motionX = 0;
        	motionY = 0;
        	motionZ = 0;
        	if(world.isAirBlock(new BlockPos(posX, posY, posZ)))
        		this.setExpired();
    	}
        this.move(motionX, motionY, motionZ);
        if (posY == prevPosY)
        {
            motionX *= 1.1D;
            motionZ *= 1.1D;
        }

        motionX *= 0.9599999785423279D;
        motionY *= 0.9599999785423279D;
        motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            motionX *= 0.699999988079071D;
            motionZ *= 0.699999988079071D;
        }
    }   
}
