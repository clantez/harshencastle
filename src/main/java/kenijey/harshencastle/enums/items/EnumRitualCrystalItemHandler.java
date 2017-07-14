package kenijey.harshencastle.enums.items;

import kenijey.harshencastle.enums.items.EnumBloodCollectorHandler.BloodLevels;
import net.minecraft.util.IStringSerializable;

public class EnumRitualCrystalItemHandler 
{
	public static enum CrystalAcive implements IStringSerializable
	{
		ACTIVE("active", true),
		PASSIVE("passive", false);
		
		private boolean active;
		private String name;
		
		private CrystalAcive(String name, boolean active)
		{
			this.name = name;
			this.active = active;
		}

		@Override
		public String getName() {
			return name;
		}
		
		public boolean isActive()
		{
			return this.active;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		public static String[] getNames()
		{
			String s = "";
			for(CrystalAcive l : CrystalAcive.values())
				s += l.getName() + " ";
			return s.split(" ");
		}
	}
}
