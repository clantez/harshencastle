package kenijey.harshencastle.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;

public abstract class BaseItemRendererActive<T extends BaseTileEntityHarshenSingleItemInventoryActive>  extends BaseItemRenderer<T>
{
	@Override
	protected float getMovementSpeed(T te) {
		return te.isActive() ? te.getActiveTimer() / 10f: 1f;
	}

}
