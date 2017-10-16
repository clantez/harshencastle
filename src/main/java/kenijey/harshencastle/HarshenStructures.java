package kenijey.harshencastle;

import kenijey.harshencastle.base.HarshenStructure;
import kenijey.harshencastle.config.HarshenConfigs;
import kenijey.harshencastle.structures.MainCastle;
import kenijey.harshencastle.structures.PontusRitual;
import kenijey.harshencastle.structures.Shrine;
import kenijey.harshencastle.structures.TreeDome;

public class HarshenStructures 
{
	public static final HarshenStructure CASTLE = new MainCastle();
	public static final HarshenStructure SHRINE = new Shrine();
	public static final HarshenStructure PONTUS_TREE_DOME = new TreeDome();
	public static final HarshenStructure PONTUS_RITUAL = new PontusRitual();
	
	public static void preInit()
	{
		regStructure(CASTLE);
		regStructure(SHRINE);
		regStructure(PONTUS_TREE_DOME);
		regStructure(PONTUS_RITUAL);
	}
	
	public static void register()
	{
		for(HarshenStructure structure : HarshenConfigs.STRUCTURES.allComponants)
			if(HarshenConfigs.STRUCTURES.isEnabled(structure))
				HarshenStructure.allStructures.add(structure);
	}
	
	private static void regStructure(HarshenStructure structure)
	{
		HarshenConfigs.STRUCTURES.allComponants.add(structure);
	}
	
}
