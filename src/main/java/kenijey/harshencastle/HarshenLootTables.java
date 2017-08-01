package kenijey.harshencastle;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class HarshenLootTables 
{
	public static ResourceLocation shrine;
	public static ResourceLocation harshen_castle;
	
	public static void preInit()
	{
		shrine = LootTableList.register(new ResourceLocation(HarshenCastle.MODID, "chests/shrine"));
		harshen_castle = LootTableList.register(new ResourceLocation(HarshenCastle.MODID, "chests/castle"));
	}
}
