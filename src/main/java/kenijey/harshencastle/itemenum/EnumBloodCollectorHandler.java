package kenijey.harshencastle.itemenum;

import net.minecraft.util.IStringSerializable;

public class EnumBloodCollectorHandler 
{
	public static enum BloodLevels implements IStringSerializable
	{
		ZERO(0, 0),
		TEN(1, 10),
		TWENTY(2, 20),
		THIRTY(3, 30),
		FOURTY(4, 40),
		FIFTY(5, 50);
		
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
