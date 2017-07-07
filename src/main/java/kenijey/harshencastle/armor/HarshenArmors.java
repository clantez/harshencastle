package kenijey.harshencastle.armor;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenArmors 
{
	public static ArmorMaterial harshen_material = EnumHelper.addArmorMaterial("harshen", "harshencastle:Harshen", 3000, new int[] {5,9,10,5}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0F);

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
	
	private static ArrayList<ItemArmor> armours = new ArrayList<ItemArmor>();
	
	public static void register()
	{
		registerItem(harshen_jaguar_armor_helmet);
		registerItem(harshen_jaguar_armor_chestplate);
		registerItem(harshen_jaguar_armor_leggings);
		registerItem(harshen_jaguar_armor_boots);
		
	}
	
	public static void regRenders()
	{
		for(ItemArmor item : armours)
			regRender(item);
	}
	
	private static void registerItem(ItemArmor item)
	{
		armours.add(item);
		ForgeRegistries.ITEMS.register((Item) item);
	}
	
	private static void regRender(ItemArmor item)
	{
		item.setCreativeTab(HarshenCastle.harshenTab);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}

}
