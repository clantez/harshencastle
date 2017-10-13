package kenijey.harshencastle;

import java.util.ArrayList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenSounds {
	
	public final static SoundEvent BLOOD_COLLECTOR_USE = reg("blood.collector");
	public final static SoundEvent CUSTOM_ARROW_HIT = reg("lightning.hit");
	public final static SoundEvent RIPPER_SHOOT = reg("ripper.shoot");
	public final static SoundEvent LIGHTNING_RITUAL = reg("lightning.ritual");
	public final static SoundEvent HERETIC_RITUAL = reg("heretic.ritual");
	
	
	public static void register()
	{
		loadSound(BLOOD_COLLECTOR_USE);
		loadSound(CUSTOM_ARROW_HIT);
		loadSound(RIPPER_SHOOT);
		loadSound(LIGHTNING_RITUAL);
		loadSound(HERETIC_RITUAL);
	}
	
	private static void loadSound(SoundEvent event)
	{
		event.toString();
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
