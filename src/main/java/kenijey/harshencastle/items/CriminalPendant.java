package kenijey.harshencastle.items;

import java.util.List;
import java.util.UUID;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class CriminalPendant extends Item implements IHarshenProvider
{
	public CriminalPendant()
	{
		setUnlocalizedName("criminal_pendant");
		setRegistryName("criminal_pendant");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A75" + new TextComponentTranslation("accessoryitem").getFormattedText() + " \u00A77" + new TextComponentTranslation("pendant").getFormattedText());
		tooltip.add(" ");
		tooltip.add("\u00A73" + new TextComponentTranslation("cpendant").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public void onTick(EntityPlayer player, int tick) {
		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 500, 0, false, false));
	}
	
	@Override
	public void onAdd(EntityPlayer player) {
		IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("d20525ee-98b2-402f-b298-61bc19a9e0c5"), "criminalPendantHealth4", 4, 0).setSaved(true);
		if(!attributeHealth.hasModifier(modifierHealth))	
			attributeHealth.applyModifier(modifierHealth);
	}
}
