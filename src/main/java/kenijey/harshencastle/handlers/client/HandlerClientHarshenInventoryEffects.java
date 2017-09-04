package kenijey.harshencastle.handlers.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.blocks.HarshenDimensionalFlatPlate;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

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
			if(tickCounter ++ % 20 == 0)
			{
				finalBlockPositions.clear();
				ArrayList<Block> blocks = new ArrayList<Block>();
				String blockName = HarshenUtils.getFirstOccuringItem(Minecraft.getMinecraft().player,  HarshenItems.xray_pendant).getTagCompound().getString("BlockToSearch");
				if(!Arrays.asList(Blocks.AIR, null).contains(Block.getBlockFromName(blockName)))
						blocks.add(Block.getBlockFromName(blockName));
				for(ItemStack oreStack : OreDictionary.getOres(blockName))
					if(oreStack.getItem() instanceof ItemBlock)
						blocks.add(((ItemBlock)oreStack.getItem()).getBlock());
				ArrayList<BlockPos> allBlockPos = new ArrayList<>();
				HashMap<Double, BlockPos> distanceMap = new HashMap<>(HarshenUtils.HASH_LIMIT);
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
				for(EnumFacing face : EnumFacing.values())
					if(!finalBlockPositions.contains(pos.offset(face)))
						HarshenClientUtils.renderBoxAt(finalPos, event.getPartialTicks());
		}
	}
}
