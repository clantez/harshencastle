package kenijey.harshencastle.config;

import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.base.BaseConfig;
import net.minecraft.block.Block;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.config.Property;

public class BlocksEnabled extends BaseConfig
{
	
	public static ArrayList<Block> allBlocks= new ArrayList<>();
	private static HashMap<Block, Property> propertyMap = new HashMap<>();
	private static HashMap<Block, Boolean> enabledMap = new HashMap<>();

	
	private static final String CATEGORY_ITEM = "Blocks enables";

	@Override
	public String getName() {
		return "Blocks Enabled";
	}

	@Override
	public void read() {
		for(Block block : allBlocks)
		{
			String itemPath = block.getRegistryName().getResourcePath();
			Property property = config.get(CATEGORY_ITEM, itemPath, true);
			property.setComment(new TextComponentTranslation("config.isEnabled", new TextComponentTranslation(block.getUnlocalizedName() + ".name").getUnformattedText()).getUnformattedText());
			propertyMap.put(block, property);
			enabledMap.put(block, property.getBoolean());
		}
	}

	@Override
	public void save() {
		for(Block block : allBlocks)
			propertyMap.get(block).set(enabledMap.get(block));
	}
	
	public static boolean isBlockEnabled(Block block)
	{
		if(!enabledMap.containsKey(block))
			return true;
		return enabledMap.get(block);
	}

}
