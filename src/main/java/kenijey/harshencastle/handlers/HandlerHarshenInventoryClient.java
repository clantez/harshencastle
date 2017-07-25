package kenijey.harshencastle.handlers;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.items.ItemStackHandler;

public class HandlerHarshenInventoryClient 
{
	
	public static HandlerHarshenInventoryClient getInvForPlayer(String UUID)
	{
		for(HandlerHarshenInventoryClient handler : all)
			if(UUID.equals(handler.getPlayerUID()))
				return handler;
		return new HandlerHarshenInventoryClient();
	}
	
	private static ArrayList<HandlerHarshenInventoryClient> all = new ArrayList<HandlerHarshenInventoryClient>();
	private final String UUID;
	
	public HandlerHarshenInventoryClient()
	{
		all.add(this);
		UUID = HarshenCastle.proxy.getPlayer().getCachedUniqueIdString();
	}
	
	public String getPlayerUID()
	{
		return UUID;
	}
	
	
	private ItemStackHandler handler = new ItemStackHandler(1);
	public void save(EntityPlayer player)
	{
		player.getEntityData().setTag("ItemStackHandler", handler.serializeNBT());
	}
	
	public void load(EntityPlayer player)
	{
		this.handler.deserializeNBT(player.getEntityData().getCompoundTag("ItemStackHandler"));
	}
	
	@SubscribeEvent
	public void playerLoggedIn(PlayerLoggedInEvent event)
	{
		this.load(event.player);
	}
	
	public boolean canAddItem()
	{	 
		return this.handler.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.AIR);
	}
	
	public ItemStack setItem(EntityPlayer player, ItemStack item)
	{
		load(player);
		item.setCount(1);
		ItemStack stack = handler.getStackInSlot(0);
		this.handler.setStackInSlot(0, item);
		this.save(Minecraft.getMinecraft().player);

		return stack;
	}
		
	public void delItem()
	{
		this.handler.setStackInSlot(0, new ItemStack(Blocks.AIR));
		this.save(Minecraft.getMinecraft().player);
	}
	
	public boolean hasItem()
	{
		return getItem().getItem() != Item.getItemFromBlock(Blocks.AIR);
	}
	
	public ItemStack getItem()
	{
		load(Minecraft.getMinecraft().player);
		return handler.getStackInSlot(0);
	}
}
