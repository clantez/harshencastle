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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ElementalPendant extends Item implements IHarshenProvider
{
	public ElementalPendant()
	{
		setUnlocalizedName("elemental_pendant");
		setRegistryName("elemental_pendant");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A75" + new TextComponentTranslation("accessoryitem").getFormattedText() + " \u00A77" + new TextComponentTranslation("pendant").getFormattedText());
		tooltip.add(" ");
		tooltip.add("\u00A73" + new TextComponentTranslation("ependant1").getFormattedText());
		tooltip.add("\u00A7o" + new TextComponentTranslation("ependant2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public void onAdd(EntityPlayer player) {
		IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("d20525ee-98b2-402f-b298-61bc19a9e0c5"), "elementalPendantHealth4", 8, 0).setSaved(true);
		if(!attributeHealth.hasModifier(modifierHealth))	
			attributeHealth.applyModifier(modifierHealth);
		
		IAttributeInstance attributeArmour = player.getEntityAttribute(SharedMonsterAttributes.ARMOR);
		AttributeModifier modifierArmour = new AttributeModifier(UUID.fromString("82b79e32-3c85-4132-b121-03807793338c"), "elementalPendantArmour2", 4, 0).setSaved(true);
		if(!attributeArmour.hasModifier(modifierArmour))	
			attributeArmour.applyModifier(modifierArmour);
	}
	
	@Override
	public void onRemove(EntityPlayer player) {
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("d20525ee-98b2-402f-b298-61bc19a9e0c5"));
		player.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(UUID.fromString("82b79e32-3c85-4132-b121-03807793338c"));
	}
}
