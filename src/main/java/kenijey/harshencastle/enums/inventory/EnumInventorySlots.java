package kenijey.harshencastle.enums.inventory;

import java.awt.Dimension;
import java.util.ArrayList;

import kenijey.harshencastle.interfaces.IIDSet;

public enum EnumInventorySlots implements IIDSet
{
	LEFT_EAR("earring", 56, 14, 1),
	RIGHT_EAR("earring", 104, 14, 0),
	NECK("pendant", 80, 23),
	RING1("ring", 68, 50, 4),
	RING2("ring", 92, 50, 3);
	
	private int id;
	private final ArrayList<Integer> alowedIds;
	private final Dimension dimension;
	private final String name;
	
	private EnumInventorySlots(String name, int x, int y, int... relatedTypes)
	{
		this.dimension = new Dimension(x, y);
		alowedIds = new ArrayList<>();
		for(int i : relatedTypes)
			alowedIds.add(i);
		this.name = name;
	}
	
	public String getName() {
		return name;
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

	@Override
	public void setId(int id) {
		this.id = id;
		alowedIds.add(id);

	}
}
