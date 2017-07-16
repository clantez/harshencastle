package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;

public class TileEntityHarshenDisplayBlock extends BaseTileEntityHarshenSingleItemInventory
{

	private int timer = 0;
	
	@Override
	public void update() 
	{
		timer++;
	}
	
	public int getTimer()
	{
		return timer;
	}
}
