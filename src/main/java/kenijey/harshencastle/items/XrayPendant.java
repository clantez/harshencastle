package kenijey.harshencastle.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.config.AccessoryConfig;
import kenijey.harshencastle.enums.gui.EnumGuiTypes;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.HarshenEvent;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;

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
	
	public class XrayPendantHandler
	{
		@HarshenEvent
		public void renderWorldLast(RenderWorldLastEvent event)
		{
			EntityPlayer player = net.minecraft.client.Minecraft.getMinecraft().player;
			BlockPos pos = player.getPosition();
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
				for(int x = pos.getX() - AccessoryConfig.xrayAreaX; x < pos.getX() + AccessoryConfig.xrayAreaX; x++)
					for(int z = pos.getZ() - AccessoryConfig.xrayAreaZ; z < pos.getZ() + AccessoryConfig.xrayAreaZ; z++)
						for(int y = pos.getY() - AccessoryConfig.xrayAreaY; y < pos.getY() + AccessoryConfig.xrayAreaY; y++)
						{
							if(blocks.contains(net.minecraft.client.Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y, z)).getBlock()))
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
					if(positionsFound < 50)
					{
						finalBlockPositions.add(distanceMap.get(d));
						positionsFound++;
					}
				Collections.reverse(finalBlockPositions);
				for(BlockPos finalPos : finalBlockPositions)
					HarshenClientUtils.renderGhostBlock(net.minecraft.client.Minecraft.getMinecraft().world.getBlockState(finalPos), finalPos, /**/ true, event.getPartialTicks());
			}
		}
	}

}
