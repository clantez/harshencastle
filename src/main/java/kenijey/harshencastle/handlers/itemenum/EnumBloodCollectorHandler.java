package kenijey.harshencastle.handlers.itemenum;

import net.minecraft.util.IStringSerializable;

public class EnumBloodCollectorHandler 
{
	public static enum BloodLevels implements IStringSerializable
	{
		ZERO("0",0),
		TWELVE("12",1),
		TWENTYFOUR("24",2),
		THIRTYSIX("36",3),
		FOURTYEIGHT("48",4),
		SIXTY("60",5);
		
		private int id;
		private String name;
		
		private BloodLevels(String name, int id)
		{
			this.id = id;
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
		
		public int getId()
		{
			return id;
		}
		
		@Override
		public String toString() {
			return getName();
		}
	}
}
