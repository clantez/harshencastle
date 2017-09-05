package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.gui.EnumGuiTypes;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class XrayPendant extends BaseItemInventory
{

	public XrayPendant() {
		setRegistryName("xray_pendant");
		setUnlocalizedName("xray_pendant");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		HarshenCastle.proxy.openGui(EnumGuiTypes.XRAY_PENDANT, playerIn.getHeldItem(handIn));
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,  playerIn.getHeldItem(handIn));
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	protected int toolTipLines() {
		return 4;
	}

}
