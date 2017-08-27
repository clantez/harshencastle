package kenijey.harshencastle.enums.particle;

public enum EnumHarshenParticle 
{
	BLOOD(0),
	CAULDRON(1);
	
	
	private int id;
	private EnumHarshenParticle(int id)
	{
		this.id = id;
	}
	
	public static EnumHarshenParticle getFromId(int id)
	{
		for(EnumHarshenParticle particle : EnumHarshenParticle.values())
			if(particle.id == id)
				return particle;
		return null;
	}
}
