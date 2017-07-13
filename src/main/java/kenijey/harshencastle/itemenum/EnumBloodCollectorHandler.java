package kenijey.harshencastle.itemenum;

import net.minecraft.util.IStringSerializable;

public class EnumBloodCollectorHandler 
{
	public static enum BloodLevels implements IStringSerializable
	{
		ZERO(0, 0),
		TWELVE(1, 12),
		TWENTYFOUR(2, 24),
		THIRTYSIX(3, 36),
		FOURTYEIGHT(4, 48),
		SIXTY(5, 60);
		
		private int id;
		private int changeAmount;
		
		private BloodLevels(int id, int changeAmount)
		{
			this.id = id;
			this.changeAmount = changeAmount;
		}

		@Override
		public String getName() {
			return String.valueOf(id);
		}
		
		public int getId()
		{
			return id;
		}
		
		public int getAmount()
		{
			return changeAmount;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		public static String[] getNames()
		{
			String s = "";
			for(BloodLevels l : BloodLevels.values())
				s += l.getName() + " ";
			return s.split(" ");
		}
	}
}
