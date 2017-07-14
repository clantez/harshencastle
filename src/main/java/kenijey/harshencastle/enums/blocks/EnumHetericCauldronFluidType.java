package kenijey.harshencastle.enums.blocks;

import net.minecraft.util.IStringSerializable;

public class EnumHetericCauldronFluidType {
	
	public static enum EnumLiquid implements IStringSerializable
	{
		NONE("none", 0),
		HARSHING_WATER("harshing_water", 1),
		HARSHEN_DIMENSIONAL_FLUID("harshen_dimensional_fluid", 2),
		BLOOD("blood", 3);
		
		private final String name;
		private final int id;
		
		EnumLiquid(String name, int id)
		{
			this.name = name;
			this.id = id;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int getId(){
			return this.id;
		}
		
		public static EnumLiquid getMatch(int id)
		{
			for(EnumLiquid liquid : EnumLiquid.values())
				if(liquid.getId() == id)
					return liquid;
			return NONE;
		}
		
		public static EnumLiquid getMatch(String name)
		{
			for(EnumLiquid liquid : EnumLiquid.values())
				if(liquid.getName().equals(name))
					return liquid;
			return NONE;
		}	
	}

}
