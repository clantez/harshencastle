package kenijey.harshencastle.handlers;

import java.util.List;

import kenijey.harshencastle.HarshenLootTables;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerZombieEyeDrop 
{
	@SubscribeEvent
	public void livingDrop(LivingDropsEvent event)
	{
		Vec3d vec = new Vec3d(event.getEntityLiving().getPosition());
		if(event.getEntity() instanceof EntityZombie)
		{
			List<ItemStack> stackList = HarshenUtils.getItemsFromLootTable(event.getEntity().world, HarshenLootTables.zombieDrops);
			if(stackList.size() == 1)
				event.getDrops().add(new EntityItem(event.getEntity().world, vec.x, vec.y, vec.z, stackList.get(0)));
		}
	}
}
