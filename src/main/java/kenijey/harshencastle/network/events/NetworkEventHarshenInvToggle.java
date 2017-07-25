package kenijey.harshencastle.network.events;

import kenijey.harshencastle.handlers.HandlerHarshenInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class NetworkEventHarshenInvToggle {
	
	public static void go(EntityPlayer player)
	{
		ItemStack stack = player.getHeldItemMainhand();
		ItemStack newStack = stack.copy();
		int count  = stack.getCount();
		if(stack.getItem() == Item.getItemFromBlock(Blocks.AIR))
		{
			ItemStack item = HandlerHarshenInventory.instance.getItem();
			HandlerHarshenInventory.instance.delItem();
			player.setHeldItem(EnumHand.MAIN_HAND, item);
			return;
		}
		if(HandlerHarshenInventory.instance.hasItem())
		{
			count += 2;
			HandlerHarshenInventory.instance.delItem();
		}
		else
		{
			HandlerHarshenInventory.instance.setItem(player, stack);
			if(newStack.getCount() == 1)
				player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1f, 1f);
		}
		newStack.setCount(count - 1);
		player.setHeldItem(EnumHand.MAIN_HAND, newStack);
	}

}
