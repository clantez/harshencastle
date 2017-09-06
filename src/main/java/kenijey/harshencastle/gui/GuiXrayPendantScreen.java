package kenijey.harshencastle.gui;

import java.io.IOException;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketUpdateXrayBlock;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GuiXrayPendantScreen extends GuiScreen
{
	private GuiButton buttonExit;
	private GuiTextField textInput;
	private ItemStack stack;
	
	public GuiXrayPendantScreen(ItemStack itemStack) {
		stack = itemStack;
	}

	@Override
	public void initGui() {
		this.buttonExit = addButton(new GuiButton(0, this.width / 2 - 100, (int) this.height - 30, 200, 20, I18n.format("gui.done")));
		this.textInput =  new GuiTextField(1, this.fontRenderer, this.width / 2 - 152, this.height / 2, 300, 20);
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		this.textInput.setText(stack.getTagCompound().getString("BlockToSearch"));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, I18n.format("gui.xray.block"), this.width / 2, this.height / 2 - 20, 16777215);
		this.drawCenteredString(this.fontRenderer, I18n.format("gui.xray.example"), this.width / 2, this.height / 2 + 40, 16777215);
		this.textInput.drawTextBox();
		this.textInput.setFocused(true);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}	
	
	@Override
	public void drawDefaultBackground() {
		super.drawDefaultBackground();
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button){
		if(button.id == 0)
		{
			this.mc.displayGuiScreen((GuiScreen)null);
			HarshenUtils.getNBT(stack).setString("BlockToSearch", textInput.getText());
			HarshenNetwork.sendToServer(new MessagePacketUpdateXrayBlock(stack.serializeNBT()));
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		this.textInput.textboxKeyTyped(typedChar, keyCode);
		super.keyTyped(typedChar, keyCode);
	}
}
