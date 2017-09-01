package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IBloodSupply;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BloodyEarring extends Item implements IBloodSupply, IHarshenProvider
{
	public BloodyEarring()
	{
		setUnlocalizedName("bloody_earring");
		setRegistryName("bloody_earring");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A75" + new TextComponentTranslation("accessoryitem").getFormattedText() + " \u00A77" + new TextComponentTranslation("earring").getFormattedText());
		tooltip.add(" ");
		tooltip.add("\u00A7o" + new TextComponentTranslation("bearring1").getFormattedText());
		tooltip.add("\u00A7o" + new TextComponentTranslation("bearring2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public int getAmountPerSecond() {
		return 1;
	}

	@Override
	public int ticksUntillUsed() {
		return 100;
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}

	@Override
	public void onTick(EntityPlayer player, int tick) {
		if(tick % 120 == 0)
			player.heal(2f);
	}
}
