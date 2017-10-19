package kenijey.harshencastle.handlers.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.objecthandlers.FaceRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerRenderError 
{
	public static ArrayList<FaceRenderer> erroredPositions = new ArrayList<>();
	
	@SubscribeEvent
	public void playerOverLay(RenderWorldLastEvent event)
	{
		boolean flag = false;
		for(EnumHand hand : EnumHand.values())
			if(Minecraft.getMinecraft().player.getHeldItem(hand).isItemEqual(new ItemStack(HarshenItems.RITUAL_STICK, 1, 1)))
				flag = true;
		if(!flag)
			return;
		ArrayList<FaceRenderer> erroredPositions = new ArrayList<>(this.erroredPositions);
		HashMap<Double, FaceRenderer> map = new HashMap<>();
		for(FaceRenderer erroredPosition : this.erroredPositions)
			map.put(Minecraft.getMinecraft().player.getDistanceSq(erroredPosition.getPosition()), erroredPosition);
		ArrayList<Double> dMap = new ArrayList<>();
		for(Double d : map.keySet()) dMap.add(d);
		Collections.sort(dMap);
		Collections.reverse(dMap);
		for(Double d : dMap)

			if(Minecraft.getMinecraft().world.getBlockState(map.get(d).getPosition()).getBlock() == map.get(d).getState().getBlock() &&
			Minecraft.getMinecraft().world.getBlockState(map.get(d).getPosition()).getBlock().getMetaFromState(Minecraft.getMinecraft().world.getBlockState(map.get(d).getPosition())) == 
					map.get(d).getState().getBlock().getMetaFromState(map.get(d).getState()))
				this.erroredPositions.remove(map.get(d));
		else if(map.get(d).getPosition().distanceSq(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ) < 400)
			HarshenClientUtils.renderGhostBlock(map.get(d).getState(), map.get(d).getPosition(), new Color(1f, 1f, 1f), true, event.getPartialTicks());
	
	}
}
