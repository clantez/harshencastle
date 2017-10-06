package kenijey.harshencastle.gui;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.containers.ContainerMagicTable;
import kenijey.harshencastle.tileentity.TileEntityHarshenMagicTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMagicTable extends GuiContainer
{

	public GuiMagicTable(TileEntityHarshenMagicTable te, EntityPlayer player) {
		super(new ContainerMagicTable(te, player));
		ySize = 210;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.drawDefaultBackground();
		this.mc.getTextureManager().bindTexture(new ResourceLocation(HarshenCastle.MODID, "textures/gui/container/magic_table.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

}