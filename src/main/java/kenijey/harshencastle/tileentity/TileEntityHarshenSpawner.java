package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TileEntityHarshenSpawner extends BaseTileEntityHarshenSingleItemInventory
{
	private EntityLivingBase entity;
	
	public EntityLivingBase getEntity(ItemStack stack)
	{
		System.out.println(stack);
		if(stack.getItem() == Item.getItemFromBlock(Blocks.AIR))
		{
			this.entity = null;
			return null;
		}
		try
		{
			this.entity = (EntityLivingBase) ForgeRegistries.ENTITIES.getValue((ItemMonsterPlacer.getNamedIdFrom(stack))).newInstance(world);

		}
		catch (NullPointerException e) {
		}
		return this.entity;
	}

	@Override
	public boolean setItem(ItemStack item) {
		if(item.getItem() instanceof ItemMonsterPlacer && getEntity(item) != null)
		{
			return super.setItem(item);
		}
		return false;
			
	}
}
