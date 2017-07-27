package kenijey.harshencastle.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.enums.gui.EnumGuiPage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiBookScreen extends GuiScreen
{
	private GuiButton buttonExit;
	private GuiButton categoryMobs;
	private GuiButton categoryPotion;
	private GuiButton categoryStructures;
	private GuiButton categoryDimension;
	private GuiButton categoryRitual;
	
	private ArrayList<GuiButton> categoryButtons = new ArrayList<GuiButton>();
	
	@Override
	public void initGui() {
		this.buttonExit = button(0, this.width / 2 - 100, (int) this.height - 30, 200, 20, "gui.done");
		categorybuttons();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == 0)
			this.mc.displayGuiScreen((GuiScreen)null);
		if(button.id <= 5 && button.id >=1)
			System.out.println(EnumGuiPage.values()[button.id - 1].getName());
	}
	
	private void categorybuttons()
	{
		for(EnumGuiPage page : EnumGuiPage.values())
			categoryButtons.add(button(page.getId() + 1, getWidth(page.getId(), 75), 10, 75, 20, "gui.category." + page.getName()));
	}
	
	private int getWidth(int id, int width)
	{
		return (id * (width + 10)) + this.width / 2 - (((width + 10) * EnumGuiPage.values().length - 10) / 2);
	}
	
	private GuiButton button(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
	{
		return addButton(new GuiButton(buttonId, x, y, widthIn, heightIn, I18n.format(buttonText)));
	}
}
