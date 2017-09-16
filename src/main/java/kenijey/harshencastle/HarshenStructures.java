package kenijey.harshencastle;

import kenijey.harshencastle.base.HarshenStructure;
import kenijey.harshencastle.config.HarshenConfigs;
import kenijey.harshencastle.structures.MainCastle;
import kenijey.harshencastle.structures.Shrine;
import kenijey.harshencastle.structures.TreeDome;

public class HarshenStructures 
{
	public static HarshenStructure castle;
	public static HarshenStructure shrine;
	public static HarshenStructure tree_dome;

	
	public static void preInit()
	{
		castle = new MainCastle();
		shrine = new Shrine();
		tree_dome = new TreeDome();
	}
	
	public static void reg()
	{
		regStructure(castle);
		regStructure(shrine);
		regStructure(tree_dome);
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
