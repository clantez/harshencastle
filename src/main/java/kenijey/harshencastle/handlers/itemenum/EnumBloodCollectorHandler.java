package kenijey.harshencastle.handlers.itemenum;

import net.minecraft.util.IStringSerializable;

public class EnumBloodCollectorHandler 
{
	public static enum BloodLevels implements IStringSerializable
	{
		PERCENT_0("0",0),
		PERCENT_20("20",1),
		PERCENT_40("40",2),
		PERCENT_60("60",3),
		PERCENT_80("80",4),
		PERCENT_100("100",5);
		
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
