package co.uk.mrpineapple.dynasty.common.ai.guard;

import co.uk.mrpineapple.dynasty.common.entity.neutral.GuardEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.player.PlayerEntity;

public class LookAtTalkingToGuard extends LookAtGoal {
    private final GuardEntity guard;
    public LookAtTalkingToGuard(GuardEntity entity) {
        super(entity, PlayerEntity.class, 8.0F);
        this.guard = entity;
    }

    @Override
    public boolean canUse() {
        if (this.guard.nameActive == true) {
            this.lookAt = this.guard.level.getNearestPlayer(this.lookAtContext, this.guard, this.guard.getX(), this.guard.getEyeY(), this.guard.getZ());
            return true;
        } else {
            return false;
        }
    }
}