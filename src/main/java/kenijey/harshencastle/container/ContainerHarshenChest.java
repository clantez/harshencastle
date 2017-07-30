package kenijey.harshencastle.container;

import kenijey.harshencastle.base.BaseContainer;
import kenijey.harshencastle.base.BaseTileEntityHarshenStackedInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerHarshenChest extends BaseContainer
{

	public ContainerHarshenChest(IInventory playerInv, BaseTileEntityHarshenStackedInventory te) {
		super(playerInv, te);
	}
	
}

