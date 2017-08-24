package kenijey.harshencastle.handlers.client;

import kenijey.harshencastle.inventory.GuiHarshenButton;
import kenijey.harshencastle.inventory.GuiPlayerInventoryExtended;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerGuiEvent 
{
	@SubscribeEvent
	public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {

		if (event.getGui() instanceof GuiInventory || event.getGui() instanceof GuiPlayerInventoryExtended) {
			GuiContainer gui = (GuiContainer) event.getGui();
			event.getButtonList().add(new GuiHarshenButton(55, gui, 64, 9, 10, 10,
					I18n.translateToLocalFormatted((event.getGui() instanceof GuiInventory) ? "button.baubles" : "button.normal")));
		}
	}
}
