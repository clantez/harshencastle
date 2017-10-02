package kenijey.harshencastle.handlers.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenStructures;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.config.AccessoryConfig;
import kenijey.harshencastle.entity.EntityThrown;
import kenijey.harshencastle.template.HarshenTemplateRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
			e.setGlowing(e instanceof EntityLivingBase && HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.ender_pendant) &&
							Minecraft.getMinecraft().player.getDistanceToEntity(e) < AccessoryConfig.enderPendantDistance);
		
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
			String blockName = HarshenUtils.getFirstOccuringItem(Minecraft.getMinecraft().player,  HarshenItems.xray_pendant).getTagCompound().getString("BlockToSearch");
			boolean flag = HarshenUtils.toArray(AccessoryConfig.blackListedXrays).contains(blockName);
			if(!flag)
			{
				ArrayList<Block> blocks = HarshenUtils.getBlocksFromString(blockName);
				ArrayList<BlockPos> allBlockPos = new ArrayList<>();
				HashMap<Double, BlockPos> distanceMap = new HashMap<>();
				for(int x = pos.getX() - AccessoryConfig.xrayAreaX; x < pos.getX() + AccessoryConfig.xrayAreaX; x++)
					for(int z = pos.getZ() - AccessoryConfig.xrayAreaZ; z < pos.getZ() + AccessoryConfig.xrayAreaZ; z++)
						for(int y = pos.getY() - AccessoryConfig.xrayAreaY; y < pos.getY() + AccessoryConfig.xrayAreaY; y++)
						{
							if(blocks.contains(Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y, z)).getBlock()))
							{
								BlockPos position = new BlockPos(x, y, z);
								allBlockPos.add(position);
								distanceMap.put(position.distanceSq(player.posX, player.posY + player.getEyeHeight() - 0.2f, player.posZ), position);
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
					HarshenClientUtils.renderGhostBlock(Minecraft.getMinecraft().world.getBlockState(finalPos), finalPos, true, event.getPartialTicks());
			}
		}
		HarshenTemplateRenderer.getTemplate(HarshenStructures.castle.getLocation())
			.renderIntoWorld(Minecraft.getMinecraft().world, new BlockPos(100, 100, 100), event.getPartialTicks());
	}
}
