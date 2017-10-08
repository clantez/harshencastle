package kenijey.harshencastle.objecthandlers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PlayerPunchedEvent extends LivingHurtEvent {

	public final EntityPlayer player;
	public PlayerPunchedEvent(EntityPlayer attacker, EntityLivingBase entity, DamageSource source, float amount) {
		super(entity, source, amount);
		this.player = attacker;
	}

}
