package kenijey.harshencastle.handlers.client;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.objecthandlers.FaceRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerRenderError 
{
	public static ArrayList<FaceRenderer> erroredPositions = new ArrayList<>();
	
	@SubscribeEvent
	public void playerOverLay(RenderWorldLastEvent event)
	{
		ArrayList<FaceRenderer> erroredPositions = new ArrayList<>(this.erroredPositions);
		for(FaceRenderer render: erroredPositions)
			if(Minecraft.getMinecraft().world.getBlockState(render.getPosition()).getBlock() == render.getState().getBlock() &&
				Minecraft.getMinecraft().world.getBlockState(render.getPosition()).getBlock().getMetaFromState(Minecraft.getMinecraft().world.getBlockState(render.getPosition())) == 
					render.getState().getBlock().getMetaFromState(render.getState()))
				this.erroredPositions.remove(render);
			else if(render.getPosition().distanceSq(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ) < 400)
				HarshenClientUtils.renderGhostBlock(render.getState(), render.getPosition(), true, event.getPartialTicks());
	}
}
