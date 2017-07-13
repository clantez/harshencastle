package kenijey.harshencastle;

import java.util.ArrayList;

import kenijey.harshencastle.itemenum.EnumBloodCollectorHandler.BloodLevels;
import kenijey.harshencastle.itemenum.EnumRitualCrystalItemHandler.CrystalAcive;
import kenijey.harshencastle.items.BloodCollector;
import kenijey.harshencastle.items.BloodEssence;
import kenijey.harshencastle.items.BloodyEarring;
import kenijey.harshencastle.items.HarshenCrystal;
import kenijey.harshencastle.items.HarshenDimensionalDoor;
import kenijey.harshencastle.items.HarshenSoulFragment;
import kenijey.harshencastle.items.HarshenSoulIngot;
import kenijey.harshencastle.items.Itium;
import kenijey.harshencastle.items.LightEmittedEssence;
import kenijey.harshencastle.items.LightEmittedSeed;
import kenijey.harshencastle.items.PontusRing;
import kenijey.harshencastle.items.PontusWorldGatePart1;
import kenijey.harshencastle.items.PontusWorldGatePart2;
import kenijey.harshencastle.items.PontusWorldGatePart3;
import kenijey.harshencastle.items.PontusWorldGateSpawner;
import kenijey.harshencastle.items.RitualCrystal;
import kenijey.harshencastle.items.SoulHarsherPickaxe;
import kenijey.harshencastle.items.SoulHarsherSword;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenItems 
{
	public static Item harshen_soul_fragment;
	public static Item soul_harsher_sword;
	public static Item soul_harsher_pickaxe;
	public static Item item_harshen_dimensional_door;
	public static Item itium;
	public static Item harshen_crystal;
	public static Item harshen_soul_ingot;
	public static Item pontus_ring;
	public static Item bloody_earring;
	public static Item blood_essence;
	public static Item pontus_world_gate_part_1;
	public static Item pontus_world_gate_part_2;
	public static Item pontus_world_gate_part_3;
	public static Item pontus_world_gate_spawner;
	public static Item light_emitted_seed;
	public static Item light_emitted_essence;
	public static Item blood_collector;
	public static Item ritual_crystal;
	
	
	public static void preInit()
	{
		harshen_soul_fragment = new HarshenSoulFragment();
		soul_harsher_sword = new SoulHarsherSword();
		soul_harsher_pickaxe = new SoulHarsherPickaxe();
		item_harshen_dimensional_door = new HarshenDimensionalDoor();
		itium = new Itium();
		harshen_crystal = new HarshenCrystal();
		harshen_soul_ingot = new HarshenSoulIngot();
		pontus_ring = new PontusRing();
		bloody_earring = new BloodyEarring();
		blood_essence = new BloodEssence();
		pontus_world_gate_part_1 = new PontusWorldGatePart1();
		pontus_world_gate_part_2 = new PontusWorldGatePart2();
		pontus_world_gate_part_3 = new PontusWorldGatePart3();
		pontus_world_gate_spawner = new PontusWorldGateSpawner();
		light_emitted_seed = new LightEmittedSeed();
		light_emitted_essence = new LightEmittedEssence();
		blood_collector = new BloodCollector();
		ritual_crystal = new RitualCrystal();
	}
	
	public static void reg()
	{
		regItem(harshen_soul_fragment, 8);
		regItem(soul_harsher_sword, 1);
		regItem(soul_harsher_pickaxe, 1);
		regItem(item_harshen_dimensional_door,8);
		regItem(itium,8);
		regItem(harshen_crystal, 8);
		regItem(harshen_soul_ingot, 8);
		regItem(pontus_ring, 1);
		regItem(bloody_earring, 1);
		regItem(blood_essence, 8);
		regItem(pontus_world_gate_part_1,1);
		regItem(pontus_world_gate_part_2,1);
		regItem(pontus_world_gate_part_3,1);
		regItem(pontus_world_gate_spawner,1);
		regItem(light_emitted_essence,8);
		regItem(light_emitted_seed,16);
		
		regMetaItem(blood_collector, 1, BloodLevels.getNames(), "blood_collector_");
		regMetaItem(ritual_crystal, CrystalAcive.getNames(), "ritual_crystal_");
	}
	
	public static ArrayList<Item> items = new ArrayList<Item>();
	
	public static void regRenders()
	{
		for(Item item : items)
			regRender(item);
		regRenderMeta();
	}
	
	public static void regItem(Item item, int stackSize)
	{
		items.add(item);
		item.setMaxStackSize(stackSize);
		ForgeRegistries.ITEMS.register(item);
	}
	
	public static void regMetaItem(Item item, int stackSize, String[] names, String prefix)
	{
		item.setMaxStackSize(stackSize);
		regMetaItem(item, names, prefix);
	}
	
	private static ArrayList<Item> allMetaItems = new ArrayList<Item>();
	private static ArrayList<String[]> allMetaNames = new ArrayList<String[]>();
	private static ArrayList<String> allMetaPrefix = new ArrayList<String>();
	
	public static void regMetaItem(Item item, String[] names, String prefix)
	{
		ForgeRegistries.ITEMS.register(item);
		allMetaItems.add(item);
		allMetaNames.add(names);
		allMetaPrefix.add(prefix);
	}
	
	
	public static void regRender(Item item)
	{
		item.setCreativeTab(HarshenCastle.harshenTab);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void regRenderMeta()
	{
		for(int i = 0; i < allMetaItems.size(); i++)
		{
			for(int j = 0; j < allMetaNames.get(i).length; j++)
				regRender(allMetaItems.get(i), j, allMetaPrefix.get(i) + allMetaNames.get(i)[j]);
			System.out.println(allMetaItems.get(i));
			System.out.println(allMetaNames.get(i));
			System.out.println(allMetaPrefix.get(i));
		}
			
	}
	
	public static void regRender(Item item, int meta, String fileName)
	{
		new ItemStack(item, 1, meta).getItem().setCreativeTab(HarshenCastle.harshenTab);
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(HarshenCastle.MODID, fileName), "inventory"));
	}
	
}
