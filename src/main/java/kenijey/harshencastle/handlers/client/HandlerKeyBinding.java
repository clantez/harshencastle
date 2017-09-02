package kenijey.harshencastle.handlers.client;

import org.lwjgl.input.Keyboard;

import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketSendTeleringUpdate;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class HandlerKeyBinding 
{

	private final KeyBinding telering;
	
	public HandlerKeyBinding()
	{
		telering = regKey("telering", Keyboard.KEY_LSHIFT);

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
		voidKeyOpenSlot();
	}
	
	public void voidKeyOpenSlot()
	{
		if(telering.isKeyDown() != teleringPressed)
			update(telering.isKeyDown());

	}
	
	boolean teleringPressed;
	
	private void update(boolean updateTo)
	{
		HarshenNetwork.sendToServer(new MessagePacketSendTeleringUpdate(updateTo));
		teleringPressed = updateTo;
	}
	
}