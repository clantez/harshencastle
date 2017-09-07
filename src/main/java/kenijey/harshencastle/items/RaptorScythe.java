package kenijey.harshencastle.items;

import com.google.common.collect.Multimap;

import kenijey.harshencastle.HarshenItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class RaptorScythe extends ItemSword
{
	protected float attackSpeed;
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("iron_scythe", 3, 1010, 13.5f, 8f, 30);

	public RaptorScythe() 
	{
		super(toolMaterial);
		setUnlocalizedName("raptor_scythe");
		setRegistryName("raptor_scythe");
		this.attackSpeed = -3.0F;
	}
	
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)this.attackSpeed, 0));
        }

        return multimap;
    }
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == HarshenItems.soul_infused_ingot;
	}
}
