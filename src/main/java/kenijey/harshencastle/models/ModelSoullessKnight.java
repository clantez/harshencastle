package kenijey.harshencastle.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;

public class ModelSoullessKnight extends ModelBiped
{	
	 public ModelSoullessKnight()
	 {
	     this(0.0F, false);
	 }

	 public ModelSoullessKnight(float modelSize, boolean p_i1168_2_)
	 {
		 super(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
	 }     
}
