package kenijey.harshencastle;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class HarshenLootTables 
{
	public final static ResourceLocation shrine = reg("chests/shrine");
	public final static ResourceLocation harshen_castle = reg("chests/castle");
	public final static ResourceLocation zombieDrops = reg("entities/zombie_eye");
	
	public static ResourceLocation reg(String name)
	{
		return LootTableList.register(new ResourceLocation(HarshenCastle.MODID, name));
	}
}
