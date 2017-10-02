package kenijey.harshencastle.objecthandlers;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.util.ResourceLocation;

public class EntityThrowLocation extends ResourceLocation
{
	private final int id;
	public EntityThrowLocation(int id) {
		super(HarshenCastle.MODID, "textures/particles/throw_particles.png");
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
