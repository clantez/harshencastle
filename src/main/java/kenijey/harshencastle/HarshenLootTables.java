package kenijey.harshencastle;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class HarshenLootTables 
{
	public static ResourceLocation harshen_castle_bottom;
	public static ResourceLocation harshen_castle_other;
	
	public static void preInit()
	{
		harshen_castle_bottom = LootTableList.register(new ResourceLocation(HarshenCastle.MODID, "chests/castle_layers/bottom"));
		harshen_castle_other = LootTableList.register(new ResourceLocation(HarshenCastle.MODID, "chests/castle_layers/other"));
	}
}
