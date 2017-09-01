package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class PontusRing extends Item implements IHarshenProvider
{
	public PontusRing()
	{
		setUnlocalizedName("pontus_ring");
		setRegistryName("pontus_ring");
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A75" + new TextComponentTranslation("accessoryitem").getFormattedText() + " \u00A77" + new TextComponentTranslation("ring").getFormattedText());
		tooltip.add(" ");
		tooltip.add("\u00A73" + new TextComponentTranslation("pontus1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("pontus2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}

	@Override
	public void onTick(EntityPlayer player, int tick) {
		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100, 0, false, false));
	}

}
