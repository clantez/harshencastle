package kenijey.harshencastle.handlers.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
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
			e.setGlowing(e instanceof EntityMob && HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.ender_pendant));
		
	}
	
	ArrayList<BlockPos> finalBlockPositions = new ArrayList<>();
	private int tickCounter;
	
	@SubscribeEvent
	public void renderWorldLast(RenderWorldLastEvent event)
	{
		if(HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.xray_pendant))
		{
			EntityPlayer player = Minecraft.getMinecraft().player;
			BlockPos pos = player.getPosition();
			if(tickCounter ++ % 10 == 0)
			{
				finalBlockPositions.clear();
				ArrayList<Block> blocks = HarshenUtils.getBlocksFromString(HarshenUtils.getFirstOccuringItem(Minecraft.getMinecraft().player,  HarshenItems.xray_pendant).getTagCompound().getString("BlockToSearch"));
				ArrayList<BlockPos> allBlockPos = new ArrayList<>();
				HashMap<Double, BlockPos> distanceMap = new HashMap<>();
				for(int x = pos.getX() - 35; x < pos.getX() + 35; x++)
					for(int z = pos.getZ() - 35; z < pos.getZ() + 35; z++)
						for(int y = pos.getY() - 10; y < pos.getY() + 10; y++)
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
			}
			for(BlockPos finalPos : finalBlockPositions)
				HarshenClientUtils.renderFullBoxAt(finalPos, event.getPartialTicks());

		}
	}
}
