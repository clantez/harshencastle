package kenijey.harshencastle.intergration.jei;

import kenijey.harshencastle.HarshenCastle;

public class JEICategoryUIDs 
{
	public static final String RITUAL = getString("ritual");
	public static final String CAULDRON = getString("cauldron");
	public static final String PENDESTAL_SLAB = getString("pedestalslab");
	public static final String HERETIC_RITUAL = getString("hereticritual");
	public static final String MAGIC_TABLE = getString("magictable");
	
	private static String getString(String string)
	{
		return HarshenCastle.MODID + "." + string;
	}

}
