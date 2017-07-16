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
	private BitSet avalibleIndecies;
	
	
	public static void init()
	{
		registerEntity(EntitySoullessKnight.class, "soulless_knight", 0xa62323, 0xaed1515);
	}
	
	public static void registerEntity(Class entityClass, String entityName, int solidColor, int spotColor)
	{
		int randomId = findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(new ResourceLocation(HarshenCastle.MODID, entityName), entityClass, entityName, randomId, HarshenCastle.instance, 64, 1, true, solidColor, spotColor);
	}
	
	public static HarshenEntities instance()
	{
		return instance;
	}
	
	private HarshenEntities()
	{
		avalibleIndecies = new BitSet(256);
		avalibleIndecies.set(1, 255);
		for(int i = 0; i < 256; i ++)
		{
			if(GameData.getEntityRegistry().getValue(i) != null)
				avalibleIndecies.clear(i);
		}
	}
	
	public static int findGlobalUniqueEntityId()
	{
		int res = instance().avalibleIndecies.nextSetBit(0);
		if(res < 0)
		{
			throw new RuntimeException("No more entity indicies left");
		}
		return res;
	}
}
