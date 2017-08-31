package kenijey.harshencastle.particle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kenijey.harshencastle.base.BaseHarshenParticle;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBed;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.UniversalBucket;

public class ParticleItem extends BaseHarshenParticle {

	private int fxLayer = 1;
	
	public ParticleItem(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn,
			double motionYIn, double motionZIn, float par14, boolean disableMoving, ItemStack stack) {
		super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn, par14, disableMoving);
		this.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(stack.getItem(), stack.getMetadata()));
		if(stack.getItem() instanceof ItemBlock)
	        this.setParticleTexture(Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata())));
		if(stack.getItem() instanceof UniversalBucket)
			if(new Random().nextBoolean())
				this.setParticleTexture(Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(((UniversalBucket)stack.getItem()).getFluid(stack).getFluid().getBlock().getDefaultState()));
			else
				this.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(Items.BUCKET));
		if(stack.getItem() instanceof ItemBed)
		{
			fxLayer = 3;
			setLocation(new ResourceLocation("textures/entity/bed/" + EnumDyeColor.byMetadata(stack.getMetadata()).getName() + ".png"));
			return;
		}
		List<BakedQuad> quadList = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, world, Minecraft.getMinecraft().player).getQuads((IBlockState)null, (EnumFacing)null, 0L);
		int i = 0;
		boolean flag = !stack.isEmpty();
		boolean flag2 = false;
		ArrayList<Integer> colors = new ArrayList<Integer>();
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
                colors.add(k);
            }
        }
		if(flag2)
		{
			int color = colors.get(new Random().nextInt(colors.size()));
			this.particleRed = ((color >> 16) & 0xFF) * 255;
			this.particleGreen = ((color >> 8) & 0xFF) * 255;
			this.particleBlue = ((color >> 0) & 0xFF) * 255;
		}
	}
	
	@Override
	public int getFXLayer() 
	{
		return fxLayer;
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
