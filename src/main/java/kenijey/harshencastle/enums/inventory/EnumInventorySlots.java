package kenijey.harshencastle.enums.inventory;

import java.awt.Dimension;

public enum EnumInventorySlots 
{
	LEFT_EAR(0, 56, 14),
	RIGHT_EAR(1, 104, 14),
	NECK(2, 80, 23),
	RING1(3, 68, 50),
	RING2(4, 92, 50);
	
	private final int id;
	private final Dimension dimension;
	
	private EnumInventorySlots(int id, int x, int y)
	{
		this.id = id;
		this.dimension = new Dimension(x, y);
	}
	
	public int getId() {
		return id;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
}
