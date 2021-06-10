package co.uk.mrpineapple.dynasty.common.ai.guard;

import co.uk.mrpineapple.dynasty.common.entity.neutral.GuardEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.ZombieEntity;

public class GuardAttackGoal extends MeleeAttackGoal {
    private final GuardEntity guard;
    private int raiseArmTicks;

    public GuardAttackGoal(GuardEntity guardEntity, double p_i46803_2_, boolean p_i46803_4_) {
        super(guardEntity, p_i46803_2_, p_i46803_4_);
        this.guard = guardEntity;
    }

    @Override
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.guard.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.guard.setAggressive(true);
        } else {
            this.guard.setAggressive(false);
        }

    }
}