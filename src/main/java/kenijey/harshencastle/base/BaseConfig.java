package kenijey.harshencastle.base;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
	
	public static HashMap<Class, Class> switchClasses = new HashMap<>();

	
	public void preInit()
	{
		if(getName() == null)
			throw new IllegalArgumentException("name of config file cant be null");
		File configFile = new File(Loader.instance().getConfigDir(), HarshenCastle.MODID + "/" + getName() + ".cfg");
		config = new Configuration(configFile);
		
		switchClasses.put(Boolean.class, boolean.class);
		switchClasses.put(Boolean[].class, boolean[].class);
		
		switchClasses.put(Integer.class, int.class);
		switchClasses.put(Integer[].class, int[].class);
		
		switchClasses.put(Double.class, double.class);
		switchClasses.put(Double[].class, double[].class);	
		
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
		
	protected <T> T get(String name, T normal)
	{
		try
		{
			Object returned = getMethodForType(normal.getClass()).invoke(config, getName(), name, normal);
			if(normal.getClass().isArray())
				for(Method method : config.getClass().getMethods())
					if(method.getParameterTypes().length == 3 && method.getParameterTypes()[0] == String.class && method.getParameterTypes()[1] == String.class
					&& method.getParameterTypes()[2] == normal.getClass() && method.getParameterTypes()[2].isArray())
						returned = method.invoke(config, getName(), name, normal);
			if(!(returned instanceof Property))
				throw new IllegalArgumentException("Returned Type was not a property. This is practically impossible");
			Property property = (Property) returned;
			property.setComment(new TextComponentTranslation("config." + name).getUnformattedText());
			propertyMap.put(getName() + "*" + name, property);
			String className = normal.getClass().getSimpleName();
			if(normal.getClass() == Integer.class || normal.getClass() == Integer[].class)
				className = "Int";
			Object returnObj = property.getClass().getMethod("get" + className.replace("[]", "List"))
					.invoke(property);
			return (T) returnObj;
		}
		catch (NullPointerException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
			HarshenCastle.logger.error("Forge Config has no such getter for " + normal.getClass() + ". ErrorClass:" + e.getClass());
			e.printStackTrace();
		}
		return normal;
	}
	
	protected <T> void set(String name, T set)
	{
		try {
			propertyMap.get(getName() + "*" + name).getClass().getMethod("set", getClass(set.getClass())).invoke(propertyMap.get(getName() + "*" + name), set);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			HarshenCastle.logger.error("Forge Config has no such setter for " + set.getClass());
		}
		catch (NullPointerException e) {
			HarshenCastle.logger.error("Property List does not contain " + name + " please report this");
		}
	}
	
	
	private Method getMethodForType(Class clazz)
	{
		Class claz = getClass(clazz);
		Class clazToTest = claz;
		if(claz.isArray())
			clazToTest = claz.getComponentType();
		for(Method method : config.getClass().getMethods())
		{
			if(!(method.getName().equals("get") && method.getParameterCount() == 3))
				continue;
			Class methodClaz = method.getParameterTypes()[2];
			if(methodClaz.isArray())
				methodClaz = methodClaz.getComponentType();
			if(method.getParameterTypes()[0] == String.class && method.getParameterTypes()[1] == String.class && methodClaz == clazToTest
					&& method.getParameterTypes()[2].isArray() == claz.isArray())
				return method;
		}
		throw new NullPointerException();
	}
	
	private Class getClass(Class claz)
	{
		return switchClasses.containsKey(claz) ? switchClasses.get(claz) : claz;
	}
}
