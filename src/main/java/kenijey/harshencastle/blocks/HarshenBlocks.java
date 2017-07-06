package kenijey.harshencastle.blocks;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
	}

	public static void reg() {
		regBlock(harshen_dimensional_stone, 64);
		regBlock(harshen_hidden_plate,64);
		regSingleBlock(harshen_hidden_plate_active);
		regBlock(harshen_dimensional_pressure_plate, 64);
		regBlock(harshen_dimensional_flat_plate, 64);
		regBlock(harshen_dimensional_stairs,64);
		regBlock(harshen_dimensional_glass,64);
		regBlock(harshen_dimensional_ladder,64);
		regSingleBlock(harshen_dimensional_door);
	
		regBlock(soul_reminder,1);
		regBlock(harshen_soul_ore, 64);
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
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}
