package kenijey.harshencastle.handlers.client;

import org.lwjgl.input.Keyboard;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.inventory.GuiHandler;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketOpenInv;
import kenijey.harshencastle.network.packets.MessagePacketRingUpdate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class HandlerKeyBinding 
{

	private final KeyBinding telering;
	private final KeyBinding minering;
	
	private final KeyBinding openInventory;
	
	public HandlerKeyBinding()
	{
		telering = regKey("telering", Keyboard.KEY_G);
		minering = regKey("minering", Keyboard.KEY_V);
		openInventory = regKey("open_inventory", Keyboard.KEY_H);
	}
	
	private KeyBinding regKey(String name, int keycode)
	{
		KeyBinding key = new KeyBinding("key." +  name + ".description", keycode, "harshencastle");
		ClientRegistry.registerKeyBinding(key);
		return key;
	}
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{

		if(telering.isPressed())
			sendRingEvent(0);
		if(minering.isPressed())
			sendRingEvent(1);
		if(openInventory.isPressed())
		{
			EntityPlayer player = 	Minecraft.getMinecraft().player;
			player.openGui(HarshenCastle.instance, GuiHandler.CUSTOMINVENTORY, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
			HarshenNetwork.sendToServer(new MessagePacketOpenInv());
		}
	}
	
	private void sendRingEvent(int ringType)
	{
		HarshenNetwork.sendToServer(new MessagePacketRingUpdate(ringType));
	}
	
}