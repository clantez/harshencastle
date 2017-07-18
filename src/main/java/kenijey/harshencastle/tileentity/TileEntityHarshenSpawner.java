package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TileEntityHarshenSpawner extends BaseTileEntityHarshenSingleItemInventory
{
	private EntityLivingBase entity;
	
	public EntityLivingBase getEntity(ItemStack stack)
	{
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
	protected void tick() 
	{
		EntityPlayer player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false);
		if(player != null && !player.capabilities.isCreativeMode)
			activate(player);
	}
	
	private void activate(EntityPlayer player)
	{
		if(this.entity == null)
			getEntity(getItem());
		this.entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
		this.entity.setRotationYawHead(player.getPosition().subtract(pos).getY());
		world.spawnEntity(this.entity);
		world.setBlockToAir(pos);
	}
}
