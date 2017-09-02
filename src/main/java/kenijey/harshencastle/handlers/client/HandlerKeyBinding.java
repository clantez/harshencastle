package kenijey.harshencastle.handlers.client;

import org.lwjgl.input.Keyboard;

import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketRingUpdate;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class HandlerKeyBinding 
{

	private final KeyBinding telering;
	private final KeyBinding minering;
	
	public HandlerKeyBinding()
	{
		telering = regKey("telering", Keyboard.KEY_G);
		minering = regKey("minering", Keyboard.KEY_V);

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
		if(telering.isKeyDown() != teleringPressed)
			update(telering.isKeyDown(), 0);
		if(minering.isKeyDown() != mineringPressed)
			update(minering.isKeyDown(), 1);
	}
	
	boolean teleringPressed;
	boolean mineringPressed;

	
	private void update(boolean updateTo, int ringType)
	{
		HarshenNetwork.sendToServer(new MessagePacketRingUpdate(updateTo, ringType));
		switch (ringType) {
		case 0:
			teleringPressed = updateTo;
			break;
		case 1:
			mineringPressed = updateTo;
			break;
		default:
			break;
		}
	}
	
}