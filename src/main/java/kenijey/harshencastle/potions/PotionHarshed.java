package kenijey.harshencastle.potions;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionHarshed extends Potion
{

	protected PotionHarshed() {
		super(true, 295051);
		setRegistryName("harshed");
		setPotionName("Harshed");
		setIconIndex(1, 0);
		registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2);
	}
	
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(HarshenCastle.MODID, "textures/gui/icons.png"));
		return super.getStatusIconIndex();
	}
}
