package kenijey.harshencastle.handlers.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerClientHarshenInventoryEffects 
{
	@SubscribeEvent
	public void RenderEventPre(RenderGameOverlayEvent.Pre event)
	{
		for(Entity e : Minecraft.getMinecraft().world.getLoadedEntityList())
			e.setGlowing(e instanceof EntityLivingBase && HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.ender_pendant));
		
	}
	
	ArrayList<BlockPos> finalBlockPositions = new ArrayList<>();
	
	@SubscribeEvent
	public void renderWorldLast(RenderWorldLastEvent event)
	{
		if(HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.xray_pendant))
		{
			EntityPlayer player = Minecraft.getMinecraft().player;
			BlockPos pos = player.getPosition();
			finalBlockPositions.clear();
			ArrayList<Block> blocks = HarshenUtils.getBlocksFromString(HarshenUtils.getFirstOccuringItem(Minecraft.getMinecraft().player,  HarshenItems.xray_pendant).getTagCompound().getString("BlockToSearch"));
			ArrayList<BlockPos> allBlockPos = new ArrayList<>();
			HashMap<Double, BlockPos> distanceMap = new HashMap<>();
			for(int x = pos.getX() - 20; x < pos.getX() + 20; x++)
				for(int z = pos.getZ() - 20; z < pos.getZ() + 20; z++)
					for(int y = pos.getY() - 20; y < pos.getY() + 20; y++)
					{
						if(blocks.contains(Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y, z)).getBlock()))
						{
							BlockPos position = new BlockPos(x, y, z);
							allBlockPos.add(position);
							distanceMap.put(position.distanceSq(player.posX, player.posY, player.posZ), position);
						}
					}
						
			ArrayList<Double> keySet = new ArrayList<>();
			for(double d : distanceMap.keySet())
				keySet.add(d);		
			Collections.sort(keySet);
			int positionsFound = 0;
			for(double d : keySet)
				if(positionsFound < 50)
				{
					finalBlockPositions.add(distanceMap.get(d));
					positionsFound++;
				}
			Collections.reverse(finalBlockPositions);
			for(BlockPos finalPos : finalBlockPositions)
				HarshenClientUtils.renderGhostBlock(Minecraft.getMinecraft().world.getBlockState(finalPos), finalPos, new Color(0.1f, 0.5f, 1f), true, event.getPartialTicks());
		}
	}
}
