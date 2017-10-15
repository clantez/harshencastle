package kenijey.harshencastle.enums.items;

import kenijey.harshencastle.api.IIDSet;
import net.minecraft.util.IStringSerializable;

public enum EnumBloodCollector implements IStringSerializable, IIDSet
{
	ZERO(0),
	TEN(10),
	TWENTY(20),
	THIRTY(30),
	FOURTY(40),
	FIFTY(50);
	
	private int id;
	private int changeAmount;
	
	private EnumBloodCollector(int changeAmount)
	{
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
		for(EnumBloodCollector l : EnumBloodCollector.values())
			s += l.getName() + " ";
		return s.split(" ");
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
}
