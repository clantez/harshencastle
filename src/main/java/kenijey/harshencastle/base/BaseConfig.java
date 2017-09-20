package kenijey.harshencastle.base;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
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
		
	protected <T> T get(String name, String category, T normal)
	{
		try
		{
			Object returned = HarshenUtils.getMethod("get", config.getClass(), String.class, String.class, normal.getClass()).invoke(config, category, name, normal);
			if(normal.getClass().isArray())
				for(Method method : config.getClass().getMethods())
					if(method.getParameterTypes().length == 3 && method.getParameterTypes()[0] == String.class && method.getParameterTypes()[1] == String.class
					&& method.getParameterTypes()[2] == normal.getClass() && method.getParameterTypes()[2].isArray())
						returned = method.invoke(config, category, name, normal);
			if(!(returned instanceof Property))	throw new IllegalArgumentException("Returned Type was not a property. This is practically impossible");
			Property property = (Property) returned;
			property.setComment(new TextComponentTranslation("config." + name).getUnformattedText());
			propertyMap.put(category + "*" + name, property);
			return (T) property.getClass().getMethod("get" + normal.getClass().getSimpleName().replace("Integer", "Int").replace("[]", "List")).invoke(property);
		}
		catch (NullPointerException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			HarshenCastle.logger.error("Forge Config has no such getter for " + normal.getClass() + ". ErrorClass: " + e.getClass().getSimpleName());
			e.printStackTrace();
		}
		return normal;
	}
	
	protected <T> T get(String name, T normal)
	{
		return this.get(name, getName(), normal);
	}
	
	protected <T> void set(String name, String category, T set)
	{
		try {
			HarshenUtils.getMethod("set", propertyMap.get(category + "*" + name).getClass(), set.getClass()).invoke(propertyMap.get(category + "*" + name), set);
		} catch (IllegalAccessException | InvocationTargetException | SecurityException e) {
			HarshenCastle.logger.error("Forge Config has no such setter for " + set.getClass());
		}
	}
	
	protected <T> void set(String name, T set)
	{
		set(name, getName(), set);
	}
}
