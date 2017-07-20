package kenijey.harshencastle.entity.AI;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class AIEntityAIWanderAvoidWaterFlying extends AIEntityAIWanderNoWater
{
    public AIEntityAIWanderAvoidWaterFlying(EntityCreature p_i47413_1_, double p_i47413_2_)
    {
        super(p_i47413_1_, p_i47413_2_);
    }

    @Nullable
    protected Vec3d getPosition()
    {
        Vec3d vec3d = null;

        if (this.entity.isInWater() || this.entity.isOverWater())
        {
            vec3d = RandomPositionGenerator.getLandPos(this.entity, 15, 15);
        }

        vec3d = new Vec3d(this.entity.getPosition().add(this.entity.getRNG().nextInt(10) - 5, this.entity.getRNG().nextInt(10) - 5, (this.entity.getRNG().nextInt(10) - 5)));

        return vec3d == null ? super.getPosition() : vec3d;
    }

}
