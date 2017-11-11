package kenijey.harshencastle.internal;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.HarshenPlugin;
import kenijey.harshencastle.api.IHarshenPlugin;
import kenijey.harshencastle.enums.items.GlassContainerValue;
import kenijey.harshencastle.objecthandlers.HarshenAPIError;
import kenijey.harshencastle.recipies.CauldronRecipes;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import kenijey.harshencastle.recipies.LargeCauldronRecipe;
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
	public static ArrayList<LargeCauldronRecipe> allLargeCauldronRecipes = new ArrayList<>();
	public static ArrayList<PedestalSlabRecipes> allPedestalRecipes = new ArrayList<>();
	public static ArrayList<MagicTableRecipe> allMagicTableRecipes = new ArrayList<>();
		
	public static void loadPlugins(ASMDataTable asmData) {
		for(IHarshenPlugin plugin : HarshenUtils.getInstancesOfAnnotation(asmData, HarshenPlugin.class, IHarshenPlugin.class))
			if(plugin.getModID() != HarshenCastle.MODID) ALL_PLUGINS.add(plugin);
			else ALL_PLUGINS.add(0, plugin);
	}
	
	private static String getPluginNames()
	{
		String allModIds = "";	
		for(int i = 0; i < ALL_PLUGINS.size(); i++)
			if(i == 0) allModIds += ALL_PLUGINS.get(i).getModID();
			else if(i == ALL_PLUGINS.size() - 1) allModIds += " and " + ALL_PLUGINS.get(i).getModID();
			else allModIds += ", " + ALL_PLUGINS.get(i).getModID();
		return allModIds;
	}
	
	public static void register()
	{
		allRitualRecipes.clear();
		allCauldronRecipes.clear();
		allHereticCauldronRecipes.clear();
		allPedestalRecipes.clear();
		allMagicTableRecipes.clear();
		GlassContainerValue.reset();
		
		HarshenCastle.LOGGER.info("Sending registry events to the following mods: " + getPluginNames());
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
