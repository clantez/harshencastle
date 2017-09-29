package kenijey.harshencastle.handlers;

import kenijey.harshencastle.containers.ContainerMagicTable;
import kenijey.harshencastle.gui.GuiMagicTable;
import kenijey.harshencastle.inventory.ContainerPlayerInventory;
import kenijey.harshencastle.inventory.GuiPlayerInventoryExtended;
import kenijey.harshencastle.tileentity.TileEntityHarshenMagicTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	
	public static final int CUSTOMINVENTORY = 0;
	public static final int MAGICTABLE = 1;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == CUSTOMINVENTORY)
			return new ContainerPlayerInventory(player);
		if (ID == MAGICTABLE)
			return new ContainerMagicTable((TileEntityHarshenMagicTable) world.getTileEntity(new BlockPos(x, y, z)), player);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == CUSTOMINVENTORY)
			return new GuiPlayerInventoryExtended(player);
		if(ID == MAGICTABLE)
			return new GuiMagicTable((TileEntityHarshenMagicTable) world.getTileEntity(new BlockPos(x, y, z)), player);
		return null;
	}

}