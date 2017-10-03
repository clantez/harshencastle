package kenijey.harshencastle.items;

import java.util.UUID;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;

public class RingOfBlood extends BaseItemInventory
{
	public RingOfBlood()
	{
		setUnlocalizedName("ring_of_blood");
		setRegistryName("ring_of_blood");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
	
	@Override
	public void onAdd(EntityPlayer player) {
		IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b50a"), "ringOfBloodHealth4", 4, 0).setSaved(true);
		if(!attributeHealth.hasModifier(modifierHealth))	
			attributeHealth.applyModifier(modifierHealth);
	}
	
	@Override
	public void onRemove(EntityPlayer player) {
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b50a"));
	}

}
