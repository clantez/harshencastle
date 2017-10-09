package kenijey.harshencastle.objecthandlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;

public class PlayerPunchedEvent extends LivingEvent {

	public final EntityPlayer attacker;
	public final DamageSource source;
	private float amount;
	private final LivingHurtEvent event;
    public PlayerPunchedEvent(LivingHurtEvent event, EntityPlayer attacker)
    {
        super(event.getEntityLiving());
        this.source = event.getSource();
        this.amount = event.getAmount();
        this.attacker = attacker;
        this.event = event;
    }
    
    public void setAmount(float amount) {
		this.amount = amount;
		this.event.setAmount(amount);
	}
    
    @Override
    public void setCanceled(boolean cancel) {
    	this.event.setCanceled(cancel);
    }
    
    @Override
    public void setPhase(EventPriority value) {
    	this.event.setPhase(value);
    }
    
    @Override
    public void setResult(Result value) {
    	this.event.setResult(value);
    }
    
    public float getAmount() {
		return amount;
	}
}
