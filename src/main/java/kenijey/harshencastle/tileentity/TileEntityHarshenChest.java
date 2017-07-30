package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseTileEntityHarshenStackedInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityHarshenChest extends BaseTileEntityHarshenStackedInventory
{

	@Override
	public int getSlotCount() {
		return 5;
	}

	
}
