package kenijey.harshencastle.handlers;

import com.google.common.eventbus.Subscribe;

import kenijey.harshencastle.HarshenBlocks;
import net.minecraft.item.Item;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

public class HandlerBlockBurn 
{
	@Subscribe
	public void itemBurn(FurnaceFuelBurnTimeEvent furnaceBurn)
	{
		Item item = furnaceBurn.getItemStack().getItem();
		if(item == Item.getItemFromBlock(HarshenBlocks.harshen_magic_table))
			furnaceBurn.setBurnTime(500);
	}
}
