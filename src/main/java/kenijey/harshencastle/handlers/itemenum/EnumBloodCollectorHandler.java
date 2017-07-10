package kenijey.harshencastle.handlers.itemenum;

import net.minecraft.util.IStringSerializable;

public class EnumBloodCollectorHandler 
{
	public static enum BloodLevels implements IStringSerializable
	{
		ZERO(0),
		TWELVE(1),
		TWENTYFOUR(2),
		THIRTYSIX(3),
		FOURTYEIGHT(4),
		SIXTY(5);
		
		private int id;
		
		private BloodLevels( int id)
		{
			this.id = id;
		}

		@Override
		public String getName() {
			return String.valueOf(id);
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
