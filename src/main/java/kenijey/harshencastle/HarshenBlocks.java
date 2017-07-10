package kenijey.harshencastle;

import java.util.ArrayList;

import kenijey.harshencastle.blocks.BloodBlock;
import kenijey.harshencastle.blocks.CropOfGleam;
import kenijey.harshencastle.blocks.HarshenDestroyedPlant;
import kenijey.harshencastle.blocks.HarshenDimensionalDirt;
import kenijey.harshencastle.blocks.HarshenDimensionalDoor;
import kenijey.harshencastle.blocks.HarshenDimensionalFlatPlate;
import kenijey.harshencastle.blocks.HarshenDimensionalGate;
import kenijey.harshencastle.blocks.HarshenDimensionalGlass;
import kenijey.harshencastle.blocks.HarshenDimensionalLadder;
import kenijey.harshencastle.blocks.HarshenDimensionalPedestal;
import kenijey.harshencastle.blocks.HarshenDimensionalPlate;
import kenijey.harshencastle.blocks.HarshenDimensionalRock;
import kenijey.harshencastle.blocks.HarshenDimensionalStairs;
import kenijey.harshencastle.blocks.HarshenDimensionalStone;
import kenijey.harshencastle.blocks.HarshenDimensionalWoodCrate;
import kenijey.harshencastle.blocks.HarshenHiddenPlate;
import kenijey.harshencastle.blocks.HarshenHiddenPlateActive;
import kenijey.harshencastle.blocks.HarshenSoulFlower;
import kenijey.harshencastle.blocks.HarshenSoulOre;
import kenijey.harshencastle.blocks.HereticCauldron;
import kenijey.harshencastle.blocks.ItiumOre;
import kenijey.harshencastle.blocks.PlantOfGleam;
import kenijey.harshencastle.blocks.PontusDeadLeaves;
import kenijey.harshencastle.blocks.PontusDeadWood;
import kenijey.harshencastle.blocks.SoulReminder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenBlocks {
	private static ArrayList<Block> blocksWithItems = new ArrayList<Block>();
	
	public static Block harshen_soul_ore;
	public static Block harshen_dimensional_stone;
	public static Block harshen_dimensional_door;
	public static Block harshen_hidden_plate;
	public static Block harshen_hidden_plate_active;
	public static Block harshen_dimensional_stairs;
	public static Block harshen_dimensional_glass;
	public static Block harshen_dimensional_ladder;
	public static Block harshen_dimensional_pressure_plate;
	public static Block harshen_dimensional_flat_plate;
	public static Block soul_reminder;
	public static Block harshen_dimensional_wood_crate;
	public static Block harshen_dimensional_dirt;
	public static Block itium_ore;
	public static Block harshen_destroyed_plant;
	public static Block harshen_dimensional_rock;
	public static Block harshen_dimensional_pedestal;
	public static Block harshen_dimensional_gate;
	public static Block heretic_cauldron;
	public static Block blood_block;
	public static Block pontus_dead_leaves;
	
	public static BlockCrops crop_of_gleam;

	public static BlockFlower harshen_soul_flower;
	public static BlockFlower plant_of_gleam;
	
	public static BlockLog pontus_dead_wood;

	public static void preInit() {
		harshen_soul_ore = new HarshenSoulOre();
		harshen_dimensional_stone = new HarshenDimensionalStone();
		harshen_dimensional_door = new HarshenDimensionalDoor();
		harshen_hidden_plate = new HarshenHiddenPlate();
		harshen_hidden_plate_active = new HarshenHiddenPlateActive();
		harshen_dimensional_stairs = new HarshenDimensionalStairs();
		harshen_dimensional_glass = new HarshenDimensionalGlass();
		harshen_dimensional_ladder = new HarshenDimensionalLadder();
		harshen_dimensional_pressure_plate = new HarshenDimensionalPlate();
		harshen_dimensional_flat_plate = new HarshenDimensionalFlatPlate();
		soul_reminder = new SoulReminder();
		harshen_dimensional_wood_crate = new HarshenDimensionalWoodCrate();
		harshen_dimensional_dirt = new HarshenDimensionalDirt();
		itium_ore = new ItiumOre();
		harshen_destroyed_plant = new HarshenDestroyedPlant();
		harshen_soul_flower = new HarshenSoulFlower();
		harshen_dimensional_rock = new HarshenDimensionalRock();
		harshen_dimensional_pedestal = new HarshenDimensionalPedestal();
		plant_of_gleam = new PlantOfGleam();
		harshen_dimensional_gate = new HarshenDimensionalGate();
		crop_of_gleam = new CropOfGleam();
		heretic_cauldron = new HereticCauldron();
		blood_block = new BloodBlock();
		pontus_dead_wood = new PontusDeadWood();
		pontus_dead_leaves = new PontusDeadLeaves();
	}

	public static void reg() {
		regBlock(harshen_dimensional_stone, 64);
		regBlock(harshen_hidden_plate,64);
		regBlock(harshen_dimensional_pressure_plate, 64);
		regBlock(harshen_dimensional_flat_plate, 64);
		regBlock(harshen_dimensional_stairs,64);
		regBlock(harshen_dimensional_glass,64);
		regBlock(harshen_dimensional_ladder,64);
		regBlock(harshen_dimensional_wood_crate, 64);
		regBlock(harshen_dimensional_dirt, 64);
		regBlock(harshen_dimensional_gate, 1);
		regBlock(pontus_dead_wood, 64);
		regBlock(pontus_dead_leaves, 64);
		regBlock(heretic_cauldron, 64);
		regBlock(harshen_soul_ore, 64);
		regBlock(itium_ore, 64);
		regBlock(harshen_destroyed_plant,64);
		regBlock(harshen_soul_flower, 64);
		regBlock(soul_reminder,1);
		regBlock(harshen_dimensional_rock, 64);
		regBlock(harshen_dimensional_pedestal, 8);
		regBlock(plant_of_gleam, 64);
		
		regSingleBlock(blood_block);
		regSingleBlock(crop_of_gleam);
		regSingleBlock(harshen_dimensional_door);
		regSingleBlock(harshen_hidden_plate_active);
	}

	public static void regRenders() {
		for(Block b : blocksWithItems)
			regRender(b);
	}

	public static void regBlock(Block block, int stackSize) {
		blocksWithItems.add(block);
		ForgeRegistries.BLOCKS.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		item.setMaxStackSize(stackSize);
		ForgeRegistries.ITEMS.register(item);
	}
	
	public static void regSingleBlock(Block block)
	{
		ForgeRegistries.BLOCKS.register(block);
	}

	public static void regRender(Block block) {
		block.setCreativeTab(HarshenCastle.harshenTab);
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}
