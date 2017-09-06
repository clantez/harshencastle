package kenijey.harshencastle.base;

import java.io.File;
import java.util.HashMap;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

public abstract class BaseConfig
{
	protected Configuration config = null;
		
	public Configuration getConfig()
	{
		return config;
	}
	public void preInit()
	{
		if(getName() == null)
			throw new IllegalArgumentException("name of config file cant be null");
		File configFile = new File(Loader.instance().getConfigDir(), HarshenCastle.MODID + "/" + getName() + ".cfg");
		config = new Configuration(configFile);
		syncConfig();
	}
	
	private void syncConfig()
	{
		config.load();
		read();
		save();
		if(config.hasChanged())
			config.save();
	}
	public abstract String getName();
	
	public abstract void read();
	
	public abstract void save();
	
	public static HashMap<String, Property> propertyMap = new HashMap<>();
	
	protected boolean getBoolean(String name, boolean normal)
	{
		Property property = config.get(getName(), name, normal);
		property.setComment(new TextComponentTranslation("config." + name).getUnformattedText());
		propertyMap.put(getName() + "*" + name, property);
		return property.getBoolean();
	}
	
	protected int getInt(String name, int normal)
	{
		Property property = config.get(getName(), name, normal);
		property.setComment(new TextComponentTranslation("config." + name).getUnformattedText());
		propertyMap.put(getName() + "*" + name, property);
		return property.getInt();
	}
	
	protected double getDouble(String name, double normal)
	{
		Property property = config.get(getName(), name, normal);
		property.setComment(new TextComponentTranslation("config." + name).getUnformattedText());
		propertyMap.put(getName() + "*" + name, property);
		return property.getDouble();
	}
	
	protected void setBoolean(String name, boolean set)
	{
		propertyMap.get(getName() + "*" + name).set(set);
	}
	
	protected void setInt(String name, int set)
	{
		propertyMap.get(getName() + "*" + name).set(set);
	}
	
	protected void setDouble(String name, double set)
	{
		propertyMap.get(getName() + "*" + name).set(set);
	}
}
