package kenijey.harshencastle;

import kenijey.harshencastle.base.HarshenStructure;
import kenijey.harshencastle.structures.MainCastle;
import kenijey.harshencastle.structures.Shrine;
import kenijey.harshencastle.structures.TreeDome;

public class HarshenStructureRegistry 
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
}
