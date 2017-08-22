package kenijey.harshencastle.damagesource;

import net.minecraft.util.DamageSource;

public class DamageSourceHarshed extends DamageSource
{

	public DamageSourceHarshed() {
		super("harshed");
	}
	
	public static DamageSourceHarshed getSource()
	{
		return new DamageSourceHarshed();
	}

}
