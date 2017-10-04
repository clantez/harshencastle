package kenijey.harshencastle.items;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.google.common.collect.Multimap;

import kenijey.harshencastle.base.BaseHarshenScythe;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class IronScythe extends BaseHarshenScythe
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("iron_scythe", 2, 1010, 13.5f, 7f, 30);

	public IronScythe() 
	{
		super(toolMaterial);
		setUnlocalizedName("iron_scythe");
		setRegistryName("iron_scythe");
	}

	@Override
	protected float getSpeed() {
		return 1.45f;
	}

	@Override
	protected Item getRepairItem() {
		return Items.IRON_INGOT;
	}

	@Override
	public double getReach() {
		return 6;
	}
}
