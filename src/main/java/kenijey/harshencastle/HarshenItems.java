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
import kenijey.harshencastle.items.EmeraldShard;
import kenijey.harshencastle.items.EmpoweredSoulHarsherSword;
import kenijey.harshencastle.items.EnderBow;
import kenijey.harshencastle.items.EnderPendant;
import kenijey.harshencastle.items.EnionBow;
import kenijey.harshencastle.items.Fearring;
import kenijey.harshencastle.items.FeatherEarring;
import kenijey.harshencastle.items.FeedingEarring;
import kenijey.harshencastle.items.FieryRing;
import kenijey.harshencastle.items.GlassContainer;
import kenijey.harshencastle.items.GuidanceOfHarshenCastle;
import kenijey.harshencastle.items.HarshenCrystal;
import kenijey.harshencastle.items.HarshenDimensionalDoor;
import kenijey.harshencastle.items.RingOfFlight;
import kenijey.harshencastle.items.HarshenProps;
import kenijey.harshencastle.items.HarshenSoulFragment;
import kenijey.harshencastle.items.HarshenSoulIngot;
import kenijey.harshencastle.items.IronBow;
import kenijey.harshencastle.items.IronHeart;
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
import kenijey.harshencastle.items.ReachPendant;
import kenijey.harshencastle.items.RingOfBlood;
import kenijey.harshencastle.items.RitualCrystal;
import kenijey.harshencastle.items.RitualStick;
import kenijey.harshencastle.items.SolidifyingPaste;
import kenijey.harshencastle.items.SoulBindingPendant;
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
	public static final Item HARSHEN_BOOK = new GuidanceOfHarshenCastle();
	public static final Item HARSHEN_SOUL_FRAGMENT = new HarshenSoulFragment();
	public static final Item SOUL_HARSHER_SWORD = new SoulHarsherSword();
	public static final Item EMPOWERED_SOUL_HARSHER_SWORD = new EmpoweredSoulHarsherSword();
	public static final Item SOUL_HARSHER_PICKAXE = new SoulHarsherPickaxe();
	public static final Item ITEM_HARSHEN_DIMENSIONAL_DOOR = new HarshenDimensionalDoor();
	public static final Item ITIUM = new Itium();
	public static final Item HARSHEN_CRYSTAL = new HarshenCrystal();
	public static final Item HARSHEN_SOUL_INGOT = new HarshenSoulIngot();
	public static final Item PONTUS_RING = new PontusRing();
	public static final Item BLOODY_EARRING = new BloodyEarring();
	public static final Item BLOOD_ESSENCE = new BloodEssence();
	public static final BaseItemMetaData PONTUS_WORLD_GATE_PARTS = new PontusWorldGatePart();
	public static final BaseItemMetaData PONTUS_WORLD_GATE_SPAWNER = new PontusWorldGateSpawner();
	public static final Item LIGHT_EMITTED_SEED = new LightEmittedSeed();
	public static final Item LIGHT_EMITTED_ESSENCE = new LightEmittedEssence();
	public static final BaseItemMetaData BLOOD_COLLECTOR = new BloodCollector();
	public static final BaseItemMetaData RITUAL_CRYSTAL = new RitualCrystal();
	public static final BaseItemMetaData RITUAL_STICK = new RitualStick();
	public static final BaseItemMetaData PROPS = new HarshenProps();
	public static final BaseItemMetaData GLASS_CONTAINER = new GlassContainer();
	public static final BaseItemMetaData ITEM_LIQUID = new ItemLiquid();
	public static final Item SOUL_INFUSED_INGOT = new SoulInfusedIngot();
	public static final Item FEATHER_EARRING = new FeatherEarring();
	public static final Item FEARRING = new Fearring();
	public static final Item ONE_RING = new OneRing();
	public static final Item CRIMINAL_PENDANT = new CriminalPendant();
	public static final Item TELERING = new Telering();
	public static final Item BLOOD_INFUSED_ENDER_EYE = new BloodInfusedEnderEye();
	public static final Item ELEMENTAL_PENDANT = new ElementalPendant();
	public static final Item POWDER_OF_HERETISM = new PowderOfHeretism();
	public static final Item ZOMBIE_EYE = new ZombieEye();
	public static final Item ZOMBI_PENDANT = new ZombiPendant();
	public static final Item BLOODY_APPLE = new BloodyApple();
	public static final Item PONTUS_CUBE = new PontusCube();
	public static final Item MINERING = new MineRing();
	public static final Item BLOODY_PONTUS_CUBE = new BloodyPontusCube();
	public static final Item PUNCHY_RING = new PunchyRing();
	public static final Item LOOTING_EARRING = new LootingEarring();
	public static final Item ENDER_PENDANT = new EnderPendant();
	public static final Item SOUL_SHIELD = new SoulShield();
	public static final Item WATER_EARRING = new WaterEarring();
	public static final Item XRAY_PENDANT = new XrayPendant();
	public static final Item IRON_BOW = new IronBow();
	public static final Item ENION_BOW = new EnionBow();
	public static final Item SOUL_RIPPER_BOW = new SoulRipperBow();
	public static final Item FIERY_RING = new FieryRing();
	public static final Item BROKEN_ARROW = new BrokenArrow();
	public static final Item VALOR_BADGE = new ValorBadge();
	public static final Item IRON_SCYTHE = new IronScythe();
	public static final Item RAPTOR_SCYTHE = new RaptorScythe();
	public static final Item ELYTRA_PENDANT = new ElytraPendant();
	public static final Item MYSTIC_FEATHER = new MysticFeather();
	public static final Item LIGHTNING_STAFF = new LightningStaff();
	public static final Item SOLIDIFYING_PASTE = new SolidifyingPaste(); 
	public static final Item COMBAT_PENDANT = new CombatPendant();
	public static final Item ENDER_BOW = new EnderBow();
	public static final Item DIAMOND_SHARD = new DiamondShard();
	public static final Item EMERALD_SHARD = new EmeraldShard();
	public static final Item FEEDING_EARRING = new FeedingEarring();
	public static final Item IRON_HEART = new IronHeart();
	public static final Item RING_OF_BLOOD = new RingOfBlood();
	public static final Item SOUL_BINDING_PENDANT = new SoulBindingPendant();
	public static final Item REACH_PENDANT = new ReachPendant();
	public static final Item RING_OF_FLIGHT = new RingOfFlight();
	
	public static void preInit()
	{
		regItem(HARSHEN_BOOK);
		regItem(HARSHEN_SOUL_FRAGMENT, 8);
		regItem(SOUL_HARSHER_SWORD);
		regItem(EMPOWERED_SOUL_HARSHER_SWORD);
		regItem(SOUL_HARSHER_PICKAXE);
		regItem(ITEM_HARSHEN_DIMENSIONAL_DOOR,8);
		regItem(ITIUM,8);
		regItem(HARSHEN_CRYSTAL, 18);
		regItem(HARSHEN_SOUL_INGOT, 8);
		regItem(BLOOD_ESSENCE, 8);
		regItem(LIGHT_EMITTED_ESSENCE,8);
		regItem(LIGHT_EMITTED_SEED,16);
		regItem(SOUL_INFUSED_INGOT, 2);
		regItem(BLOOD_INFUSED_ENDER_EYE);
		regItem(ELEMENTAL_PENDANT);
		regItem(POWDER_OF_HERETISM, 8);
		regItem(ZOMBIE_EYE, 64);
		regItem(BLOODY_APPLE, 13);
		regItem(PONTUS_CUBE, 7);
		regItem(BLOODY_PONTUS_CUBE, 6);
		regItem(IRON_BOW);
		regItem(ENION_BOW);
		regItem(SOUL_RIPPER_BOW);
		regItem(BROKEN_ARROW, 64);
		regItem(VALOR_BADGE, 31);
		regItem(IRON_SCYTHE);
		regItem(RAPTOR_SCYTHE);
		regItem(MYSTIC_FEATHER, 12);
		regItem(LIGHTNING_STAFF);
		regItem(SOLIDIFYING_PASTE, 64);
		regItem(ENDER_BOW);
		regItem(DIAMOND_SHARD, 64);
		regItem(EMERALD_SHARD, 64);
		regItem(IRON_HEART, 64);
		
		regItem(ZOMBI_PENDANT);
		regItem(PUNCHY_RING);
		regItem(LOOTING_EARRING);
		regItem(ENDER_PENDANT);
		regItem(FEATHER_EARRING);
		regItem(FEARRING);
		regItem(ONE_RING);
		regItem(CRIMINAL_PENDANT);
		regItem(TELERING);
		regItem(MINERING);
		regItem(PONTUS_RING);
		regItem(BLOODY_EARRING);
		regItem(SOUL_SHIELD);
		regItem(WATER_EARRING);
		regItem(XRAY_PENDANT);
		regItem(FIERY_RING);
		regItem(ELYTRA_PENDANT);
		regItem(COMBAT_PENDANT);
		regItem(FEEDING_EARRING);
		regItem(RING_OF_BLOOD);
		regItem(SOUL_BINDING_PENDANT);
		regItem(REACH_PENDANT);
		regItem(RING_OF_FLIGHT);
		
		regMetaItem(ITEM_LIQUID, 64, emptyList(ItemLiquidTypeset.length()), "item_liquid");
		regMetaItem(RITUAL_STICK, emptyList(EnumRitualStick.values().length), "ritual_stick");
		regMetaItem(PONTUS_WORLD_GATE_SPAWNER, EnumPontusGateSpawner.getNames(), "pontus_world_gate_spawner_");
		regMetaItem(PONTUS_WORLD_GATE_PARTS, EnumPontusGateSpawnerParts.getNames(), "pontus_world_gate_part_");
		regMetaItem(PROPS, EnumProp.getNames(), "prop_");
		regMetaItem(BLOOD_COLLECTOR, EnumBloodCollector.getNames(), "blood_collector_");
		regMetaItem(RITUAL_CRYSTAL, 12, EnumRitualCrystal.getNames(), "ritual_crystal_");
		regMetaItem(GLASS_CONTAINER, emptyList(EnumGlassContainer.values().length), "glass_container", new ExceptionName(0, "_empty"), new ExceptionName(EnumGlassContainer.MAGIC.getMeta(), "_magic"));
	}
	
	public final static ArrayList<Item> ALL_ITEMS= new ArrayList<Item>();
	
	public static void regRenders()
	{
		for(Item item : ALL_ITEMS)
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
	
	private static Item getItem(Item item)
	{
		return item;
	}
	
	public static void regItem(Item item)
	{
		regItem(item, 1);
	}
	
	public static void regItem(Item item, int stackSize)
	{
		ALL_ITEMS.add(item);
		item.setMaxStackSize(stackSize);
		HarshenConfigs.ITEMS.allComponants.add(item);
	}
	
	public static void regMetaItem(BaseItemMetaData item, int stackSize, String[] names, String prefix, ExceptionName...exceptionNames)
	{
		item.setMaxStackSize(stackSize);
		for(ExceptionName exc : exceptionNames)
			names[exc.position] = exc.name;
		HarshenConfigs.ITEMS.allComponants.add(item);
		allMetaItems.add(item);
		allMetaNames.add(names);
		allMetaPrefix.add(prefix);
	}
	
	private static ArrayList<Item> allMetaItems = new ArrayList<Item>();
	private static ArrayList<String[]> allMetaNames = new ArrayList<String[]>();
	private static ArrayList<String> allMetaPrefix = new ArrayList<String>();
	
	public static void regMetaItem(BaseItemMetaData item, String[] names, String prefix, ExceptionName...exceptionNames)
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

class ExceptionName
{
	public final int position;
	public final String name;
	public ExceptionName(int position, String name) {
		this.position = position;
		this.name = name;
	}
}
