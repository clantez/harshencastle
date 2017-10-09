package kenijey.harshencastle.items;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.HarshenEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class LootingEarring extends BaseItemInventory
{

	public LootingEarring() {
		setRegistryName("looting_earring");
		setUnlocalizedName("looting_earring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new HandlerLootingEarring();
	}
	
	public class HandlerLootingEarring
	{
		@HarshenEvent
		public void onLivingDrops(LivingDropsEvent event)
		{
			if(event.getEntity() instanceof EntityPlayer)
				return;
			ArrayList<EntityItem> drops = new ArrayList<EntityItem>();
			for(EntityItem e : event.getDrops())
				if(event.getEntityLiving().getRNG().nextBoolean())
					drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, e.getItem()));
			event.getDrops().addAll(drops);
		}
	}

}
