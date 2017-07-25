package kenijey.harshencastle.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.items.ItemStackHandler;

public class HandlerHarshenInventory 
{
	
	public static HandlerHarshenInventory instance = new HandlerHarshenInventory();
	
	private ItemStackHandler handler = new ItemStackHandler(1);
	public void save(EntityPlayer player)
	{
		player.getEntityData().setTag("ItemStackHandler", handler.serializeNBT());
	}
	
	public void load(EntityPlayer player)
	{
		this.handler.deserializeNBT(player.getEntityData().getCompoundTag("ItemStackHandler"));
	}
	
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
		item.setCount(1);
		ItemStack stack = handler.getStackInSlot(0);
		this.handler.setStackInSlot(0, item);
		save(player);
		return stack;
	}
		
	public void delItem()
	{
		this.handler.setStackInSlot(0, new ItemStack(Blocks.AIR));
	}
	
	public boolean hasItem()
	{
		return getItem().getItem() != Item.getItemFromBlock(Blocks.AIR);
	}
	
	public ItemStack getItem()
	{
		return handler.getStackInSlot(0);
	}
}
