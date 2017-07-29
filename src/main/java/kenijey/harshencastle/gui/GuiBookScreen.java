package kenijey.harshencastle.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.enums.gui.EnumGuiPage;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiBookScreen extends GuiScreen
{
	private GuiButton buttonExit;
	private GuiTextField mainText;
	private ArrayList<GuiButton> categoryButtons = new ArrayList<GuiButton>();
	private ArrayList<Gui> allComponants = new ArrayList<Gui>();
	private EnumGuiPage mode = EnumGuiPage.values()[0];
    private List<String> lines = Lists.<String>newArrayList();
	
	@Override
	public void initGui() {
		this.buttonExit = button(0, this.width / 2 - 100, (int) this.height - 30, 200, 20, "gui.done");
		categorybuttons();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.lines.clear();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, "Harshen Castle " + mode.getName(), this.width / 2, 4, 16777215);
		try {
            String s = "";
			InputStream inputstream = this.mc.getResourceManager().getResource(new ResourceLocation(HarshenCastle.MODID, "texts/category." + mode.getName().toLowerCase() + ".txt")).getInputStream();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));
            String s1;
            while ((s1 = bufferedreader.readLine()) != null)
                this.lines.addAll(this.mc.fontRenderer.listFormattedStringToWidth(s1, 274));
            inputstream.close();
            for(int i = 0; i < this.lines.size(); i++)
            	this.drawCenteredString(this.fontRenderer, this.lines.get(i), this.width / 2, 75 + (i*10), 16777215);
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}	
	
	@Override
	protected void actionPerformed(GuiButton button){
		if(button.id == 0)
			this.mc.displayGuiScreen((GuiScreen)null);
		if(button.id > 8000)
			this.mode = EnumGuiPage.values()[button.id - 8800];
	}
	
	private void categorybuttons()
	{
		for(EnumGuiPage page : EnumGuiPage.values())
			categoryButtons.add(button(page.getId() + 8800, (page.getId() * (75 + 10)) + this.width / 2 - (((75 + 10)
					* EnumGuiPage.values().length - 10) / 2), 15, 75, 20, "gui.category." + page.getName().toLowerCase()));
		for(GuiButton b : categoryButtons)
			allComponants.add(b);
	}
	private GuiButton button(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
	{
		allComponants.add(addButton(new GuiButton(buttonId, x, y, widthIn, heightIn, I18n.format(buttonText))));
		return (GuiButton) allComponants.get(allComponants.size() - 1);
	}
}
