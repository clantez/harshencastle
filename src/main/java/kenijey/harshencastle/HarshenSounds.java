package kenijey.harshencastle;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenSounds {
	
	public static SoundEvent swordFireWork;
	
	public static void preInit()
	{
		swordFireWork = reg("sword.firework");
	}
	
	private static ArrayList<SoundEvent> reg(String... name)
	{
		ArrayList<SoundEvent> fin = new ArrayList<SoundEvent>();
		for(String s : name)
		{
			ResourceLocation loc = new ResourceLocation(HarshenCastle.MODID, s);
			ForgeRegistries.SOUND_EVENTS.register(new SoundEvent(loc).setRegistryName(loc));
			fin.add(ForgeRegistries.SOUND_EVENTS.getValue(loc));
		}
		
		return fin;
		
	}
	
	private static SoundEvent reg(String name)
	{
		ResourceLocation loc = new ResourceLocation(HarshenCastle.MODID, name);
		ForgeRegistries.SOUND_EVENTS.register(new SoundEvent(loc).setRegistryName(loc));
		return ForgeRegistries.SOUND_EVENTS.getValue(loc);
		
	}

}
