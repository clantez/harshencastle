package kenijey.harshencastle.inventory;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IVanillaProvider;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class SlotHarshenInventory extends SlotItemHandler
{
	
	private final EnumInventorySlots slotType;
	
	public SlotHarshenInventory(IItemHandler itemHandler, EnumInventorySlots slotType, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		this.slotType = slotType;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return HarshenUtils.hasProvider(stack) && HarshenUtils.isSlotAllowed(stack, this.slotType, HarshenUtils.getProvider(stack).getSlot());
	}
	
}
