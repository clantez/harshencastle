package kenijey.harshencastle.potions;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionPure extends Potion
{

	protected PotionPure() {
		super(false, 0xa6afbf);
		setRegistryName("cure");
		setPotionName("Cured");
		setIconIndex(2, 0);
		setBeneficial();
	}
	
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(HarshenCastle.MODID, "textures/gui/inventory.png"));
		return super.getStatusIconIndex();
	}

}
