package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.enums.gui.EnumGuiTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GuidanceOfHarshenCastle extends Item
{
	public GuidanceOfHarshenCastle() {
		setRegistryName("harshen_book");
		setUnlocalizedName("harshen_book");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		HarshenCastle.proxy.openGui(EnumGuiTypes.BOOK);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
