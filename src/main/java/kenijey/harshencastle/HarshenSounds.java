package kenijey.harshencastle;

import java.util.ArrayList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenSounds {
	
	public static SoundEvent bloodCollectorUse;
	public static SoundEvent customArrowHit;
	public static SoundEvent ripperShoot;
	public static SoundEvent lightningRitual;
	public static SoundEvent hereticRitual;
	
	public static void preInit()
	{
		bloodCollectorUse = reg("blood.collector");
		customArrowHit = reg("lightning.hit");
		ripperShoot = reg("ripper.shoot");
		lightningRitual = reg("lightning.ritual");
		hereticRitual = reg("heretic.ritual");
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
