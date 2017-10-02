package kenijey.harshencastle.entity;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.config.IdConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class HarshenEntities 
{
	
	private static final HarshenEntities instance = new HarshenEntities();
	
	
	public static void init()
	{
		registerEntity(EntitySoullessKnight.class, IdConfig.EntitySoullessKnight ,"soulless_knight", 0x19232C, 295051);
		registerEntity(EntitySoulPart.class, IdConfig.EntitySoulPart, "soul_part", 0xa62323, 0xaed1515);
		registerEntity(EntityThrown.class, IdConfig.EntityThrown, "entity_thrown", 0xFF0083, 0xFFA100);
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
