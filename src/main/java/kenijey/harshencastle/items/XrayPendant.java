package kenijey.harshencastle.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.HarshenEvent;
import kenijey.harshencastle.api.IHarshenProvider;
import kenijey.harshencastle.config.AccessoryConfig;
import kenijey.harshencastle.enums.gui.EnumGuiTypes;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class XrayPendant extends Item implements IHarshenProvider
{

	public XrayPendant() {
		setRegistryName("xray_pendant");
		setUnlocalizedName("xray_pendant");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		HarshenCastle.proxy.openGui(EnumGuiTypes.XRAY_PENDANT, playerIn.getHeldItem(handIn));
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,  playerIn.getHeldItem(handIn));
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public int toolTipLines() {
		return 4;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new XrayPendantHandler();
	}
	
	public static class XrayPendantHandler implements Runnable
	{
		static int localPosX, localPosY, localPosZ, prevLocalPosX, prevLocalPosZ;
		
		private static Thread thread = null;
		private static long nextTimeMs = System.currentTimeMillis();
		private static final int delayMs = 200;
		
		private static float partialTicks;
		
		private static ArrayList<BlockPos> finalBlockPos = new ArrayList<>();
		
		@HarshenEvent
		public void clientTick(ClientTickEvent event)
		{
			EntityPlayer player = HarshenUtils.getPlayer(event);
			if ( (event.phase == TickEvent.Phase.END) && (player != null) )
			{
				localPosX = MathHelper.floor(player.posX);
				localPosY = MathHelper.floor(player.posY);
				localPosZ = MathHelper.floor(player.posZ);
				prevLocalPosX = MathHelper.floor(player.prevPosX);
				prevLocalPosZ = MathHelper.floor(player.prevPosZ);

				if(((this.thread == null) || !this.thread.isAlive()) && (player.world != null) && (player != null))
				{
					this.thread = new Thread(this);
					this.thread.setDaemon(false);
					this.thread.setPriority(Thread.MAX_PRIORITY);
					this.thread.start();
				}
			}
		}
		
		
		@HarshenEvent
		public void renderWorldLast(RenderWorldLastEvent event)
		{
			for(BlockPos finalPos : finalBlockPos)
				if(net.minecraft.client.Minecraft.getMinecraft().world.getBlockState(finalPos).getBlock() != Blocks.AIR)
					HarshenClientUtils.renderGhostBlock(net.minecraft.client.Minecraft.getMinecraft().world.getBlockState(finalPos), finalPos, true, event.getPartialTicks());
		}


		@Override
		public void run() 
		{
			try
			{
				while( !this.thread.isInterrupted())
				{
					Minecraft mc = Minecraft.getMinecraft();
					boolean interupt = false;
					if (mc.world != null && mc.player != null)
					{
						if (nextTimeMs > System.currentTimeMillis())
							interupt = true;

						int px = localPosX;
						int py = localPosY;
						int pz = localPosZ;
						if(px == prevLocalPosX && pz == prevLocalPosZ)
							interupt = true;
						EntityPlayer player = mc.player;
						ItemStack stack = HarshenUtils.getFirstOccuringItem(player,  HarshenItems.XRAY_PENDANT);
						if(!stack.hasTagCompound())
							stack.setTagCompound(new NBTTagCompound());
						String blockName = stack.getTagCompound().getString("BlockToSearch");
						boolean flag = HarshenUtils.toArray(AccessoryConfig.blackListedXrays).contains(blockName);
						if(!flag)
						{
							ArrayList<Block> blocks = HarshenUtils.getBlocksFromString(blockName);
							ArrayList<BlockPos> allBlockPos = new ArrayList<>();
							HashMap<Double, BlockPos> distanceMap = new HashMap<>();
							for(int x = px - AccessoryConfig.xrayAreaX; x < px + AccessoryConfig.xrayAreaX; x++)
								for(int z = pz - AccessoryConfig.xrayAreaZ; z < pz + AccessoryConfig.xrayAreaZ; z++)
									for(int y = Math.min(0, py - AccessoryConfig.xrayAreaY); y < py + AccessoryConfig.xrayAreaY || y < 256; y++)
									{
										if(blocks.contains(net.minecraft.client.Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y, z)).getBlock())
												&& net.minecraft.client.Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y, z)).getBlock() != Blocks.AIR)
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
							ArrayList<BlockPos> finalBlockPositions = new ArrayList<>();
							for(double d : keySet)
								if(positionsFound < AccessoryConfig.xrayListSize)
								{
									finalBlockPositions.add(distanceMap.get(d));
									positionsFound++;
								}
							Collections.reverse(finalBlockPositions);
							this.finalBlockPos = new ArrayList<>(finalBlockPositions);
						}
						nextTimeMs = System.currentTimeMillis() + delayMs;
					}
					else
					    interupt = true;
					if(interupt)
						this.thread.interrupt();
				}
				this.thread = null;
			}
			catch (Exception exc)
			{
				HarshenCastle.LOGGER.error(" ClientTick Thread Interrupted!!! " + exc.toString());
			}
		}
	}

}
