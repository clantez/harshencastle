package kenijey.harshencastle.handlers.client;

import java.util.ArrayList;

import javax.vecmath.Vector4f;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.objecthandlers.FaceRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerRenderError 
{
	public static ArrayList<FaceRenderer> erroredPositions = new ArrayList<>();
	
	@SubscribeEvent
	public void playerOverLay(RenderWorldLastEvent event)
	{

		for(FaceRenderer render: erroredPositions)
			if(render.getPosition().distanceSq(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ) < 400)
				if(render.getFace() == null)
					HarshenClientUtils.renderFullBoxAt(render.getPosition(), event.getPartialTicks(), new Vector4f(1, 0, 0, 1), 5);
				else
					HarshenClientUtils.renderFaceAt(render.getFace(), render.getPosition(), event.getPartialTicks(), new Vector4f(1, 0, 0, 1), 5);
	}
}
