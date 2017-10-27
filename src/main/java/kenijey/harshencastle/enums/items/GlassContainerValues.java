package kenijey.harshencastle.enums.items;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class GlassContainerValues
{
	public static GlassContainerValue EMPTY = GlassContainerValue.getContainerFromMeta(0);
	public static GlassContainerValue VOID = GlassContainerValue.getContainerFromMeta(1);
	public static GlassContainerValue REGEN = GlassContainerValue.getContainerFromMeta(2);
	public static GlassContainerValue CURE = GlassContainerValue.getContainerFromMeta(3);
	
	public static GlassContainerValue HARSHING_WATER = GlassContainerValue.getContainerFromMeta(4);
	public static GlassContainerValue HARSHEN_DIMENSIONAL_FLUID = GlassContainerValue.getContainerFromMeta(5);
	public static GlassContainerValue BLOOD = GlassContainerValue.getContainerFromMeta(6);
	public static GlassContainerValue LAVA = GlassContainerValue.getContainerFromMeta(7);
	public static GlassContainerValue MILK = GlassContainerValue.getContainerFromMeta(8);
	public static GlassContainerValue WATER = GlassContainerValue.getContainerFromMeta(9);
	public static GlassContainerValue EARTH = GlassContainerValue.getContainerFromMeta(10);
	public static GlassContainerValue SAND = GlassContainerValue.getContainerFromMeta(11);
	public static GlassContainerValue COAL = GlassContainerValue.getContainerFromMeta(12);
	public static GlassContainerValue DIAMOND = GlassContainerValue.getContainerFromMeta(13);
	public static GlassContainerValue MAGIC = GlassContainerValue.getContainerFromMeta(14);
	
	public final static void reloadAll()
	{
		ArrayList<Field> fields = new ArrayList<>();
		for(Field field : GlassContainerValues.class.getDeclaredFields())
			if(GlassContainerValue.class.isAssignableFrom(field.getType()))
				fields.add(field);
		for(int i = 0; i < fields.size(); i++)
		{
			try {
				fields.get(i).set(null, GlassContainerValue.getContainerFromMeta(i));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
