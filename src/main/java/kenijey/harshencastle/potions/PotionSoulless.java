package kenijey.harshencastle.potions;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionSoulless extends Potion
{

	protected PotionSoulless() {
		super(false, 0xa6afbf);
		setRegistryName("soulless");
		setPotionName("Soulless");
		setIconIndex(0, 0);
		setBeneficial();
	}
	
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(HarshenCastle.MODID, "textures/gui/inventory.png"));
		return super.getStatusIconIndex();
	}
}
