package kenijey.harshencastle.api;

/**
 * The main class to implement to make a plugin with HarshenCastle. HarshenCastle only communicates with other mods through this class.
 * The class must have a constructor with no arguments and have to have the annotation {@link HarshenPlugin} to be loaded up.
 * 
 * @author Wyn Price
 */
public interface IHarshenPlugin 
{
	
	/**
	 * Where all of the registering is done. HarshenCastle will only call this funcion while registering things.
	 * Can be reloaded with F3 + T.
	 * @param registry The Registry Used to register things.
	 */
	void register(IHarshenRegistry registry);
	
	/**
	 * The ModID of your mod. Used to help identify problems and used to separate things registered with the same name.
	 * @return Your mod's modID.
	 */
	String getModID();
}
