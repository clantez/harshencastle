package kenijey.harshencastle.items;

import java.util.UUID;

import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.IHarshenProvider;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class CriminalPendant extends Item implements IHarshenProvider
{
	public CriminalPendant()
	{
		setUnlocalizedName("criminal_pendant");
		setRegistryName("criminal_pendant");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public void onAdd(EntityPlayer player) {
		IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("72eb8438-8f2b-11e7-bb31-be2e44b06b34"), "criminalPendantHealth6", 6, 0).setSaved(true);
		if(!attributeHealth.hasModifier(modifierHealth))	
			attributeHealth.applyModifier(modifierHealth);
	}
	
	@Override
	public void onRemove(EntityPlayer player) {
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("72eb8438-8f2b-11e7-bb31-be2e44b06b34"));
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}

}
