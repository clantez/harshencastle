package kenijey.harshencastle.gui;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseGuiContainer;
import kenijey.harshencastle.base.BaseTileEntityHarshenStackedInventory;
import kenijey.harshencastle.blocks.HarshenChest;
import kenijey.harshencastle.container.ContainerHarshenChest;
import kenijey.harshencastle.tileentity.TileEntityHarshenChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiHarshenChest extends BaseGuiContainer
{

	public GuiHarshenChest(IInventory playerInv, BaseTileEntityHarshenStackedInventory te) {
		super(playerInv, te);
	}

	@Override
	protected String getTextureName() {
		return "";
	}


}
