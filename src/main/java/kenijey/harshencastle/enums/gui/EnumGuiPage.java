package kenijey.harshencastle.enums.gui;

public enum EnumGuiPage {
	MOBS("mobs", 0),
	POTIONS("potions", 1),
	STRUCTURE("structure", 2),
	DIMENSION("dimension", 3),
	RITUAL("ritual", 4);
	
	
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
