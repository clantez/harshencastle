package kenijey.harshencastle.handlers.client;

import java.util.ArrayList;

import javax.vecmath.Vector4f;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.blocks.HarshenDimensionalFlatPlate;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerFlatPlateRenderer 
{
	
	public static ArrayList<BlockPos> platePositions = new ArrayList<BlockPos>();
	
	@SubscribeEvent
	public void lastRender(RenderWorldLastEvent event)
	{
		if(!Minecraft.getMinecraft().player.capabilities.isCreativeMode && !Minecraft.getMinecraft().player.isSpectator())
			return;
		ArrayList<BlockPos> nonRemovedPlates = new ArrayList<>();
		for(BlockPos pos : platePositions)
			if(Minecraft.getMinecraft().world.getBlockState(pos).getBlock() instanceof HarshenDimensionalFlatPlate)
				nonRemovedPlates.add(renderAt(pos, event.getPartialTicks()));
		platePositions = nonRemovedPlates;
	}
	
	private BlockPos renderAt(BlockPos pos, float partialTicks)
	{
		HarshenClientUtils.renderFaceAt(EnumFacing.DOWN, pos, partialTicks, new Vector4f(0, 0, 1, 1));
        return pos;
	}
}
