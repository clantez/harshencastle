package kenijey.harshencastle.inventory;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.enums.gui.EnumGuiPage;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.tileentity.HarshenClientUtils;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiPlayerInventoryExtended extends InventoryEffectRenderer
{

	 /** The old x position of the mouse pointer */
    private float oldMouseX;
    /** The old y position of the mouse pointer */
    private float oldMouseY;	
	
	public static ResourceLocation background = new ResourceLocation(HarshenCastle.MODID,"textures/gui/inventory.png");
	public static ResourceLocation slotIcons = new ResourceLocation(HarshenCastle.MODID,"textures/gui/slot_pictures.png");

	private IInventory playerInv;
	
	public GuiPlayerInventoryExtended(EntityPlayer player)
	{
		super(new ContainerPlayerInventory(player));
		this.playerInv = player.inventory;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{	
		int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawDefaultBackground();
		this.mc.getTextureManager().bindTexture(background);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		for(EnumInventorySlots slot : EnumInventorySlots.values())
			if(!inventorySlots.getSlot(slot.getId()).getHasStack())
				HarshenClientUtils.drawTexture(slot.getDimension().width + this.guiLeft, slot.getDimension().height + this.guiTop,
						13.26f, 9.89f + (slot.getId()), 1f, 1f, 16, 16, 16, 16);
		GuiInventory.drawEntityOnScreen(i + 30, j + 75, 30, (float)(i + 51) - this.oldMouseX, (float)(j + 75 - 50) - this.oldMouseY, this.mc.player);
    }
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
	}
}

