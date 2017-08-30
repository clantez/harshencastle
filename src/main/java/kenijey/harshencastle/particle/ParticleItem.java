package kenijey.harshencastle.particle;

import java.util.List;
import java.util.Random;

import kenijey.harshencastle.base.BaseHarshenParticle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ParticleItem extends BaseHarshenParticle {

	public ParticleItem(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn,
			double motionYIn, double motionZIn, float par14, boolean disableMoving, ItemStack stack) {
		super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn, par14, disableMoving);
		this.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(stack.getItem(), stack.getMetadata()));
		List<BakedQuad> quadList = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, world, Minecraft.getMinecraft().player).getQuads((IBlockState)null, (EnumFacing)null, 0L);
		int i = 0;
		boolean flag = !stack.isEmpty();
		boolean flag2 = false;
		int color = 0;
		for (int j = quadList.size(); i < j; ++i)
        {
            BakedQuad bakedquad = quadList.get(i);
            int k = -1;
            if (flag && bakedquad.hasTintIndex())
            {
            	flag2 = true;
                k = Minecraft.getMinecraft().getItemColors().getColorFromItemstack(stack, bakedquad.getTintIndex());
                if (EntityRenderer.anaglyphEnable)
                    k = TextureUtil.anaglyphColor(k);

                k = k | -16777216;
                color = k;
            }
        }
		if(flag2)
		{
			this.particleRed = ((color >> 16) & 0xFF) * 255;
			this.particleGreen = ((color >> 8) & 0xFF) * 255;
			this.particleBlue = ((color >> 0) & 0xFF) * 255;
		}
	}
	
	@Override
	public int getFXLayer() 
	{
		return 1;
	}

	@Override
	protected int getXIndex() {
		return new Random().nextInt(8);
	}
	
	@Override
	protected int getYIndex() {
		return new Random().nextInt(8);
	}

}
