package kenijey.harshencastle.armor;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class HarshenJaguarArmor extends ItemArmor
{

	public HarshenJaguarArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) 
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation("harshencastle", name));
	}
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
			ModelBiped _default) {
		if (itemStack != null) {
			if (itemStack.getItem() instanceof ItemArmor) {

				EntityEquipmentSlot type = ((ItemArmor) itemStack.getItem()).armorType;
				ModelBiped armorModel = null;
				switch (type) {
				case HEAD:
				case LEGS:
					armorModel = HarshenCastle.proxy.getArmorModel(0);
					break;
				case FEET:
				case CHEST:
					armorModel =  HarshenCastle.proxy.getArmorModel(1);
					break;
				default:
					break;
				}

				armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST);
				armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS);
				armorModel.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS);
				armorModel.isSneak = _default.isSneak;
				armorModel.isRiding = _default.isRiding;
				armorModel.isChild = _default.isChild;
				armorModel.rightArmPose = _default.rightArmPose;
				armorModel.leftArmPose = _default.leftArmPose;
				return armorModel;
			}
		}
		return null;
	}
}
