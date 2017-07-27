package kenijey.harshencastle.network.events;

import java.util.ArrayList;

import kenijey.harshencastle.handlers.HandlerHarshenInventoryClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class NetworkEventHarshenInvToggle {
	
	public static ArrayList<HandlerHarshenInventoryClient> all = new ArrayList<HandlerHarshenInventoryClient>();

	
	public static void go(EntityPlayer player)
	{
		ItemStack stack = player.getHeldItemMainhand();
		ItemStack newStack = stack.copy();
		int count  = stack.getCount();
		HandlerHarshenInventoryClient inv = getInvForPlayer(player);
		if(stack.getItem() == Item.getItemFromBlock(Blocks.AIR))
		{
			ItemStack item = inv.getItem();
			inv.delItem();
			if(!player.world.isRemote)
				player.setHeldItem(EnumHand.MAIN_HAND, item);
			return;
		}
		if(inv.hasItem())
		{
			if(stack.getItem() != inv.getItem().getItem() || stack.getCount() == 64)
				return;
			count += 2;
			inv.delItem();
		}
		else
			inv.setItem(player, stack);

		newStack.setCount(count - 1);
		if(!player.world.isRemote)
			player.setHeldItem(EnumHand.MAIN_HAND, newStack);
	}
	
	public static HandlerHarshenInventoryClient getInvForPlayer(EntityPlayer player)
	{
		for(HandlerHarshenInventoryClient handler : all)
			if(player.getCachedUniqueIdString().equals(handler.getPlayerUID()))
				return handler;
		return new HandlerHarshenInventoryClient(player);
	}

}
