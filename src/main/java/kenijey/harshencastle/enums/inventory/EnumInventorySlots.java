package kenijey.harshencastle.enums.inventory;

import java.awt.Dimension;
import java.util.ArrayList;

public enum EnumInventorySlots 
{
	LEFT_EAR(0, 56, 14, 1),
	RIGHT_EAR(1, 104, 14, 0),
	NECK(2, 80, 23),
	RING1(3, 68, 50, 4),
	RING2(4, 92, 50, 3);
	
	private final int id;
	private final ArrayList<Integer> alowedIds;
	private final Dimension dimension;
	
	private EnumInventorySlots(int id, int x, int y, int... relatedTypes)
	{
		this.id = id;
		this.dimension = new Dimension(x, y);
		alowedIds = new ArrayList<>();
		alowedIds.add(id);
		for(int i : relatedTypes)
			alowedIds.add(i);
	}
	
	public int getId() {
		return id;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public boolean isAllowed(EnumInventorySlots slotType)
	{
		return this.alowedIds.contains(slotType.getId());
	}
}
