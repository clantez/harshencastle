package kenijey.harshencastle;

import org.lwjgl.input.Keyboard;

import kenijey.harshencastle.handlers.HandlerHarshenInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class HarshenKeybinding 
{

	private final KeyBinding key_openSlot;
	
	public HarshenKeybinding()
	{
		key_openSlot = new KeyBinding("key_openSlot.description", Keyboard.KEY_G, "harshencastle");
		ClientRegistry.registerKeyBinding(key_openSlot);

	}
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		voidKeyOpenSlot();
	}
	
	public void voidKeyOpenSlot()
	{
		if(key_openSlot.isPressed())
			Minecraft.getMinecraft().player.setHeldItem(EnumHand.MAIN_HAND, (weightedItemstack(Minecraft.getMinecraft().player.getHeldItemMainhand())));		
	}
	
	public ItemStack weightedItemstack(ItemStack stack)
	{
		ItemStack newStack = stack.copy();
		int count  = stack.getCount();
		if(stack.getItem() == Item.getItemFromBlock(Blocks.AIR))
		{
			ItemStack item = HandlerHarshenInventory.instance.getItem();
			HandlerHarshenInventory.instance.delItem();
			return item;
		}
		if(HandlerHarshenInventory.instance.hasItem())
		{
			count += 2;
			HandlerHarshenInventory.instance.delItem();
		}
		else
		{
			HandlerHarshenInventory.instance.setItem(stack);
			if(newStack.getCount() == 1)
				Minecraft.getMinecraft().player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1f, 1f);
		}
		newStack.setCount(count - 1);
		return newStack;
	}
}
