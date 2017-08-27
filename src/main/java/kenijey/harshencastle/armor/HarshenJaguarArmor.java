package kenijey.harshencastle.armor;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.models.ModelArmour;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
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
	public boolean hasOverlay(ItemStack stack) {
		return false;
	}
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
			ModelBiped _default) {
		if (itemStack != null) {
			if (itemStack.getItem() instanceof ItemArmor) {

				EntityEquipmentSlot type = ((ItemArmor) itemStack.getItem()).armorType;
				ModelArmour armorModel = null;
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
				
				armorModel.slotActive = armorSlot;
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
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return HarshenCastle.MODID + ":textures/models/armor/jaguar.png";
	}
}
