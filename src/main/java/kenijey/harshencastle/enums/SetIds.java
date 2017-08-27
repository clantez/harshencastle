package kenijey.harshencastle.enums;

import kenijey.harshencastle.interfaces.IIDSet;

public class SetIds 
{
	public static void setup(Enum[] parts)
	{
		for(int i = 0; i < parts.length; i++)
			if(parts[i] instanceof IIDSet)
				((IIDSet)parts[i]).setId(i);
	}
}
