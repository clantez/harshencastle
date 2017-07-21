package kenijey.harshencastle.entity;

import java.util.BitSet;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.registries.GameData;

public class HarshenEntities 
{
	
	private static final HarshenEntities instance = new HarshenEntities();
	
	
	public static void init()
	{
		registerEntity(EntitySoullessKnight.class, EntitySoullessKnight.id() ,"soulless_knight", 0x19232C, 295051);
		registerEntity(EntitySoulPart.class, EntitySoulPart.id(), "soul_part", 0xa62323, 0xaed1515);
	}
	
	public static void registerEntity(Class entityClass, int id, String entityName, int solidColor, int spotColor)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(HarshenCastle.MODID, entityName), entityClass, entityName,
				id, HarshenCastle.instance, 64, 3, true, solidColor, spotColor);
	}
	
	public static HarshenEntities instance()
	{
		return instance;
	}
	
	
}
