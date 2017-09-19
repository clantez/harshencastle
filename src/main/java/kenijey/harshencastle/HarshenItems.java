package kenijey.harshencastle;

import java.util.ArrayList;

import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.config.HarshenConfigs;
import kenijey.harshencastle.enums.ItemLiquidTypeset;
import kenijey.harshencastle.enums.items.EnumBloodCollector;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.enums.items.EnumPontusGateSpawner;
import kenijey.harshencastle.enums.items.EnumPontusGateSpawnerParts;
import kenijey.harshencastle.enums.items.EnumProp;
import kenijey.harshencastle.enums.items.EnumRitualCrystal;
import kenijey.harshencastle.enums.items.EnumRitualStick;
import kenijey.harshencastle.items.BloodCollector;
import kenijey.harshencastle.items.BloodEssence;
import kenijey.harshencastle.items.BloodInfusedEnderEye;
import kenijey.harshencastle.items.BloodyApple;
import kenijey.harshencastle.items.BloodyEarring;
import kenijey.harshencastle.items.BloodyPontusCube;
import kenijey.harshencastle.items.BrokenArrow;
import kenijey.harshencastle.items.CombatPendant;
import kenijey.harshencastle.items.CriminalPendant;
import kenijey.harshencastle.items.DiamondShard;
import kenijey.harshencastle.items.ElementalPendant;
import kenijey.harshencastle.items.ElytraPendant;
import kenijey.harshencastle.items.EmpoweredSoulHarsherSword;
import kenijey.harshencastle.items.EnderBow;
import kenijey.harshencastle.items.EnderPendant;
import kenijey.harshencastle.items.EnionBow;
import kenijey.harshencastle.items.Fearring;
import kenijey.harshencastle.items.FeatherEarring;
import kenijey.harshencastle.items.FieryRing;
import kenijey.harshencastle.items.GlassContainer;
import kenijey.harshencastle.items.GuidanceOfHarshenCastle;
import kenijey.harshencastle.items.HarshenCrystal;
import kenijey.harshencastle.items.HarshenDimensionalDoor;
import kenijey.harshencastle.items.HarshenProps;
import kenijey.harshencastle.items.HarshenSoulFragment;
import kenijey.harshencastle.items.HarshenSoulIngot;
import kenijey.harshencastle.items.IronBow;
import kenijey.harshencastle.items.IronScythe;
import kenijey.harshencastle.items.ItemLiquid;
import kenijey.harshencastle.items.Itium;
import kenijey.harshencastle.items.LightEmittedEssence;
import kenijey.harshencastle.items.LightEmittedSeed;
import kenijey.harshencastle.items.LightningStaff;
import kenijey.harshencastle.items.LootingEarring;
import kenijey.harshencastle.items.MineRing;
import kenijey.harshencastle.items.MysticFeather;
import kenijey.harshencastle.items.OneRing;
import kenijey.harshencastle.items.PontusCube;
import kenijey.harshencastle.items.PontusRing;
import kenijey.harshencastle.items.PontusWorldGatePart;
import kenijey.harshencastle.items.PontusWorldGateSpawner;
import kenijey.harshencastle.items.PowderOfHeretism;
import kenijey.harshencastle.items.PunchyRing;
import kenijey.harshencastle.items.RaptorScythe;
import kenijey.harshencastle.items.RitualCrystal;
import kenijey.harshencastle.items.RitualStick;
import kenijey.harshencastle.items.SolidifyingPaste;
import kenijey.harshencastle.items.SoulHarsherPickaxe;
import kenijey.harshencastle.items.SoulHarsherSword;
import kenijey.harshencastle.items.SoulInfusedIngot;
import kenijey.harshencastle.items.SoulRipperBow;
import kenijey.harshencastle.items.SoulShield;
import kenijey.harshencastle.items.Telering;
import kenijey.harshencastle.items.ValorBadge;
import kenijey.harshencastle.items.WaterEarring;
import kenijey.harshencastle.items.XrayPendant;
import kenijey.harshencastle.items.ZombiPendant;
import kenijey.harshencastle.items.ZombieEye;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenItems 
{
	public static Item harshen_book;
	public static Item harshen_soul_fragment;
	public static Item soul_harsher_sword;
	public static Item empowered_soul_harsher_sword;
	public static Item soul_harsher_pickaxe;
	public static Item item_harshen_dimensional_door;
	public static Item itium;
	public static Item harshen_crystal;
	public static Item harshen_soul_ingot;
	public static Item pontus_ring;
	public static Item bloody_earring;
	public static Item blood_essence;
	public static BaseItemMetaData pontus_world_gate_parts;
	public static BaseItemMetaData pontus_world_gate_spawner;
	public static Item light_emitted_seed;
	public static Item light_emitted_essence;
	public static BaseItemMetaData blood_collector;
	public static BaseItemMetaData ritual_crystal;
	public static BaseItemMetaData ritual_stick;
	public static BaseItemMetaData props;
	public static BaseItemMetaData glass_container;
	public static BaseItemMetaData item_liquid;
	public static Item soul_infused_ingot;
	public static Item feather_earring;
	public static Item fearring;
	public static Item one_ring;
	public static Item criminal_pendant;
	public static Item telering;
	public static Item blood_infused_ender_eye;
	public static Item elemental_pendant;
	public static Item powder_of_heretism;
	public static Item zombie_eye;
	public static Item zombi_pendant;
	public static Item bloody_apple;
	public static Item pontus_cube;
	public static Item minering;
	public static Item bloody_pontus_cube;
	public static Item punchy_ring;
	public static Item looting_earring;
	public static Item ender_pendant;
	public static Item soul_shield;
	public static Item water_earring;
	public static Item xray_pendant;
	public static Item iron_bow;
	public static Item enion_bow;
	public static Item soul_ripper_bow;
	public static Item fiery_ring;
	public static Item broken_arrow;
	public static Item valor_badge;
	public static Item iron_scythe;
	public static Item raptor_scythe;
	public static Item elytra_pendant;
	public static Item mystic_feather;
	public static Item lightning_staff;
	public static Item solidifying_paste; 
	public static Item combat_pendant;
	public static Item ender_bow;
	public static Item diamond_shard;
	
	public static void preInit()
	{
		harshen_book = new GuidanceOfHarshenCastle();
		harshen_soul_fragment = new HarshenSoulFragment();
		soul_harsher_sword = new SoulHarsherSword();
		empowered_soul_harsher_sword = new EmpoweredSoulHarsherSword();
		soul_harsher_pickaxe = new SoulHarsherPickaxe();
		item_harshen_dimensional_door = new HarshenDimensionalDoor();
		itium = new Itium();
		harshen_crystal = new HarshenCrystal();
		harshen_soul_ingot = new HarshenSoulIngot();
		pontus_ring = new PontusRing();
		bloody_earring = new BloodyEarring();
		blood_essence = new BloodEssence();
		pontus_world_gate_parts = new PontusWorldGatePart();
		pontus_world_gate_spawner = new PontusWorldGateSpawner();
		light_emitted_seed = new LightEmittedSeed();
		light_emitted_essence = new LightEmittedEssence();
		blood_collector = new BloodCollector();
		ritual_crystal = new RitualCrystal();
		ritual_stick = new RitualStick();
		props = new HarshenProps();
		glass_container = new GlassContainer();
		soul_infused_ingot = new SoulInfusedIngot();
		feather_earring = new FeatherEarring();
		fearring = new Fearring();
		one_ring = new OneRing();
		criminal_pendant = new CriminalPendant();
		telering = new Telering();
		blood_infused_ender_eye = new BloodInfusedEnderEye();
		elemental_pendant = new ElementalPendant();
		powder_of_heretism = new PowderOfHeretism();
		zombie_eye = new ZombieEye();
		zombi_pendant = new ZombiPendant();
		bloody_apple = new BloodyApple();
		pontus_cube = new PontusCube();
		bloody_pontus_cube = new BloodyPontusCube();
		minering = new MineRing();
		punchy_ring = new PunchyRing();
		looting_earring = new LootingEarring();
		ender_pendant = new EnderPendant();
		soul_shield = new SoulShield();
		water_earring = new WaterEarring();
		xray_pendant = new XrayPendant();
		iron_bow = new IronBow();
		enion_bow = new EnionBow();
		fiery_ring = new FieryRing();
		broken_arrow = new BrokenArrow();
		soul_ripper_bow = new SoulRipperBow();
		valor_badge = new ValorBadge();
		iron_scythe = new IronScythe();
		raptor_scythe = new RaptorScythe();
		elytra_pendant = new ElytraPendant();
		mystic_feather = new MysticFeather();
		lightning_staff = new LightningStaff();
		solidifying_paste = new SolidifyingPaste();
		item_liquid = new ItemLiquid();
		combat_pendant = new CombatPendant();
		ender_bow = new EnderBow();
		diamond_shard = new DiamondShard();
	}
	
	public static void reg()
	{
		regItem(harshen_book);
		regItem(harshen_soul_fragment, 8);
		regItem(soul_harsher_sword);
		regItem(empowered_soul_harsher_sword);
		regItem(soul_harsher_pickaxe);
		regItem(item_harshen_dimensional_door,8);
		regItem(itium,8);
		regItem(harshen_crystal, 18);
		regItem(harshen_soul_ingot, 8);
		regItem(blood_essence, 8);
		regItem(light_emitted_essence,8);
		regItem(light_emitted_seed,16);
		regItem(soul_infused_ingot, 2);
		regItem(blood_infused_ender_eye);
		regItem(elemental_pendant);
		regItem(powder_of_heretism, 8);
		regItem(zombie_eye, 64);
		regItem(bloody_apple, 13);
		regItem(pontus_cube, 7);
		regItem(bloody_pontus_cube, 6);
		regItem(iron_bow);
		regItem(enion_bow);
		regItem(soul_ripper_bow);
		regItem(broken_arrow, 64);
		regItem(valor_badge, 31);
		regItem(iron_scythe);
		regItem(raptor_scythe);
		regItem(mystic_feather, 12);
		regItem(lightning_staff);
		regItem(solidifying_paste, 64);
		regItem(ender_bow);
		regItem(diamond_shard, 64);
		
		regItem(zombi_pendant);
		regItem(punchy_ring);
		regItem(looting_earring);
		regItem(ender_pendant);
		regItem(feather_earring);
		regItem(fearring);
		regItem(one_ring);
		regItem(criminal_pendant);
		regItem(telering);
		regItem(minering);
		regItem(pontus_ring);
		regItem(bloody_earring);
		regItem(soul_shield);
		regItem(water_earring);
		regItem(xray_pendant);
		regItem(fiery_ring);
		regItem(elytra_pendant);
		regItem(combat_pendant);
		
		regMetaItem(item_liquid, 64, emptyList(ItemLiquidTypeset.length()), "item_liquid");
		regMetaItem(ritual_stick, emptyList(EnumRitualStick.values().length), "ritual_stick");
		regMetaItem(pontus_world_gate_spawner, EnumPontusGateSpawner.getNames(), "pontus_world_gate_spawner_");
		regMetaItem(pontus_world_gate_parts, EnumPontusGateSpawnerParts.getNames(), "pontus_world_gate_part_");
		regMetaItem(props, EnumProp.getNames(), "prop_");
		regMetaItem(blood_collector, EnumBloodCollector.getNames(), "blood_collector_");
		regMetaItem(ritual_crystal, 12, EnumRitualCrystal.getNames(), "ritual_crystal_");
		regMetaItem(glass_container, emptyList(EnumGlassContainer.values().length), "glass_container", new exceptionName(0, "_empty"));
	}
	
	public static ArrayList<Item> items = new ArrayList<Item>();
	
	public static void regRenders()
	{
		for(Item item : items)
			regRender(item);
		regRenderMeta();
	}
	
	private static String[] emptyList(int size)
	{
		String[] s = new String[size];
		for(int i = 0; i < size; i++)
			s[i] = "";
		return s;
	}
	
	public static void regItem(Item item)
	{
		regItem(item, 1);
	}
	
	public static void regItem(Item item, int stackSize)
	{
		items.add(item);
		item.setMaxStackSize(stackSize);
		HarshenConfigs.ITEMS.allComponants.add(item);
	}
	
	public static void regMetaItem(BaseItemMetaData item, int stackSize, String[] names, String prefix, exceptionName...exceptionNames)
	{
		item.setMaxStackSize(stackSize);
		for(exceptionName exc : exceptionNames)
			names[exc.position] = exc.name;
		HarshenConfigs.ITEMS.allComponants.add(item);
		allMetaItems.add(item);
		allMetaNames.add(names);
		allMetaPrefix.add(prefix);
	}
	
	private static ArrayList<Item> allMetaItems = new ArrayList<Item>();
	private static ArrayList<String[]> allMetaNames = new ArrayList<String[]>();
	private static ArrayList<String> allMetaPrefix = new ArrayList<String>();
	
	public static void regMetaItem(BaseItemMetaData item, String[] names, String prefix, exceptionName...exceptionNames)
	{
		regMetaItem(item, 1, names, prefix, exceptionNames);
	}
	
	
	public static void regRender(Item item)
	{
		item.setCreativeTab(HarshenCastle.harshenTab);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void regRenderMeta()
	{
		for(int i = 0; i < allMetaItems.size(); i++)
			for(int j = 0; j < allMetaNames.get(i).length; j++)
				regRender(allMetaItems.get(i), j, allMetaPrefix.get(i) + allMetaNames.get(i)[j]);
			
	}
	
	public static void regRender(Item item, int meta, String fileName)
	{
		new ItemStack(item, 1, meta).getItem().setCreativeTab(HarshenCastle.harshenTab);
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(HarshenCastle.MODID, fileName), "inventory"));
	}

	public static void register() 
	{
		for(Item item : HarshenConfigs.ITEMS.allComponants)
			if(HarshenConfigs.ITEMS.isEnabled(item))
				ForgeRegistries.ITEMS.register(item);
	}
}

class exceptionName
{
	public final int position;
	public final String name;
	public exceptionName(int position, String name) {
		this.position = position;
		this.name = name;
	}
}
