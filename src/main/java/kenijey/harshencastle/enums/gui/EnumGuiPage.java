package kenijey.harshencastle.enums.gui;

import java.util.Arrays;

public enum EnumGuiPage {
	MOBS("Mobs", 0),
	POTIONS("Potions", 1),
	STRUCTURE("Structure", 2),
	DIMENSION("Dimension", 3),
	RITUAL("Ritual", 4);
	
	
	private final String name;
	private int id;
	
	private EnumGuiPage(String name, int id)
	{
		this.name = name;
		this.id = id;
	}
	

	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return this.name;
	}
}
