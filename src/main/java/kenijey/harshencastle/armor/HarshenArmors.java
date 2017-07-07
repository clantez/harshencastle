package kenijey.harshencastle.armor;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenArmors 
{
	public static ArmorMaterial harshen_material = EnumHelper.addArmorMaterial("harshen", "Harshen", 3000, new int[] {5,9,10,5}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0F);

	public static ItemArmor harshen_jaguar_armor_helmet;
	public static ItemArmor harshen_jaguar_armor_chestplate;
	public static ItemArmor harshen_jaguar_armor_leggings;
	public static ItemArmor harshen_jaguar_armor_boots;
	
	public static void init()
	{
		harshen_jaguar_armor_helmet = new HarshenJaguarArmor(harshen_material, 1, EntityEquipmentSlot.HEAD, "harshen_jaguar_armor_helmet");
		harshen_jaguar_armor_chestplate = new HarshenJaguarArmor(harshen_material, 1, EntityEquipmentSlot.CHEST, "harshen_jaguar_armor_chestplate");
		harshen_jaguar_armor_leggings = new HarshenJaguarArmor(harshen_material, 1, EntityEquipmentSlot.LEGS, "harshen_jaguar_armor_leggings");
		harshen_jaguar_armor_boots = new HarshenJaguarArmor(harshen_material, 1, EntityEquipmentSlot.FEET, "harshen_jaguar_armor_boots");
		
	}
	
	public static void register()
	{
		registerItem(harshen_jaguar_armor_helmet);
		registerItem(harshen_jaguar_armor_chestplate);
		registerItem(harshen_jaguar_armor_leggings);
		registerItem(harshen_jaguar_armor_boots);
		
	}
	
	public static void registerRenders()
	{
		registerItem(harshen_jaguar_armor_helmet);
		registerItem(harshen_jaguar_armor_chestplate);
		registerItem(harshen_jaguar_armor_leggings);
		registerItem(harshen_jaguar_armor_boots);
	}
	
	public static void registerItem(Item item)
	{
		item.setCreativeTab(HarshenCastle.harshenTab);
		ForgeRegistries.ITEMS.register(item);
	}

}
