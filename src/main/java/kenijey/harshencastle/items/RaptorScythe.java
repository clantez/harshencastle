package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseHarshenScythe;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class RaptorScythe extends BaseHarshenScythe
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("iron_scythe", 3, 1010, 13.5f, 12f, 30);

	public RaptorScythe() 
	{
		super(toolMaterial);
		setUnlocalizedName("raptor_scythe");
		setRegistryName("raptor_scythe");
	}

	@Override
	protected float getSpeed() {
		return 1.5f;
	}

	@Override
	protected Item getRepairItem() {
		return HarshenItems.SOUL_INFUSED_INGOT;
	}

	@Override
	public double getReach() {
		return 7.5;
	}
}
