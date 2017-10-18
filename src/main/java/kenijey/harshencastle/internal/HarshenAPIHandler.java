package kenijey.harshencastle.internal;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.HarshenPlugin;
import kenijey.harshencastle.api.IHarshenPlugin;
import kenijey.harshencastle.objecthandlers.HarshenAPIError;
import kenijey.harshencastle.recipies.CauldronRecipes;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import kenijey.harshencastle.recipies.MagicTableRecipe;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import kenijey.harshencastle.recipies.RitualRecipes;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

public class HarshenAPIHandler
{
	
	public final static ArrayList<IHarshenPlugin> ALL_PLUGINS = new ArrayList<>();

	
	public static ArrayList<RitualRecipes> allRitualRecipes = new ArrayList<>();
	public static ArrayList<CauldronRecipes> allCauldronRecipes = new ArrayList<>();
	public static ArrayList<HereticRitualRecipes> allHereticCauldronRecipes = new ArrayList<>();
	public static ArrayList<PedestalSlabRecipes> allPedestalRecipes = new ArrayList<>();
	public static ArrayList<MagicTableRecipe> allMagicTableRecipes = new ArrayList<>();
		
	public static void loadPlugins(ASMDataTable asmData) {
		ALL_PLUGINS.addAll(HarshenUtils.getInstancesOfAnnotation(asmData, HarshenPlugin.class, IHarshenPlugin.class));
	}
	
	public static void register()
	{
		allRitualRecipes.clear();
		allCauldronRecipes.clear();
		allHereticCauldronRecipes.clear();
		allPedestalRecipes.clear();
		allMagicTableRecipes.clear();
		String allModIds = "";	
		for(int i = 0; i < ALL_PLUGINS.size(); i++)
			if(i == 0) allModIds += ALL_PLUGINS.get(i).getModID();
			else if(i == ALL_PLUGINS.size() - 1) allModIds += " and " + ALL_PLUGINS.get(i).getModID();
			else allModIds += ", " + ALL_PLUGINS.get(i).getModID();
		HarshenCastle.LOGGER.info("Found plugins for the mods: " + allModIds);
		for(IHarshenPlugin plugin : ALL_PLUGINS)
			try 
			{
				plugin.register(new HarshenRegistry(plugin.getModID()));
			}
			catch (Throwable t) {
				throw new HarshenAPIError("An error occured from HarshenCastles API handler. Guilty mod -> " + plugin.getModID(), t);
			}
	}
}
