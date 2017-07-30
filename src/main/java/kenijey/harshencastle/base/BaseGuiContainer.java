package kenijey.harshencastle.base;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.container.ContainerHarshenChest;
import kenijey.harshencastle.tileentity.TileEntityHarshenChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public abstract class BaseGuiContainer extends GuiContainer
{
	private BaseTileEntityHarshenStackedInventory te;
	private IInventory playerInv;
	
	public BaseGuiContainer(IInventory playerInv, BaseTileEntityHarshenStackedInventory te)
	{
		super(new BaseContainer(playerInv, te));
		this.xSize = 176;
		this.ySize = 134;
		this.te = te;
		this.playerInv = playerInv;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{	
		int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
		GlStateManager.color(1f,1f,1f,1f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(HarshenCastle.MODID,"textures/gui/container/" + getTextureName() + ".png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);		
	}
	
	protected abstract String getTextureName();
}
