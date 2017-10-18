package kenijey.harshencastle.entity;

import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.config.IdConfig;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenEntities 
{
	
	private static final HarshenEntities instance = new HarshenEntities();
	
	
	public static void init()
	{
		registerEntity(EntitySoullessKnight.class, IdConfig.EntitySoullessKnight ,"soulless_knight", 0x19232C, 295051);
		registerEntity(EntitySoulPart.class, IdConfig.EntitySoulPart, "soul_part", 0xa62323, 0xaed1515);
		registerEntity(EntityThrown.class, IdConfig.EntityThrown, "entity_thrown");
		registerEntity(EntityHarshenSoul.class, IdConfig.EntityHarshenSoul, "harshen_soul",  0xa62323, 0xaed1515);
		
		registerSpawn(EntityHarshenSoul.class, 95, 4, 4, EnumCreatureType.MONSTER, ForgeRegistries.BIOMES.getValues());
	}
	
	public static void registerSpawn(Class <? extends EntityLiving > entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, List<Biome> biomes)
	{
		EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes.toArray(new Biome[biomes.size()]));
	}
	
	public static void registerEntity(Class entityClass, int id, String entityName, int solidColor, int spotColor)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(HarshenCastle.MODID, entityName), entityClass, entityName,
				id, HarshenCastle.instance, 64, 3, true, solidColor, spotColor);
	}
	
	public static void registerEntity(Class entityClass, int id, String entityName)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(HarshenCastle.MODID, entityName), entityClass, entityName,
				id, HarshenCastle.instance, 64, 3, true);
	}
	
	public static HarshenEntities instance()
	{
		return instance;
	}
	
	
}
