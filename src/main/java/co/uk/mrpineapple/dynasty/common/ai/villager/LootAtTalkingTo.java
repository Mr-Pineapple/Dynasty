package co.uk.mrpineapple.dynasty.common.ai.villager;

import co.uk.mrpineapple.dynasty.common.entity.passive.BasicVillagerEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.player.PlayerEntity;

public class LootAtTalkingTo extends LookAtGoal {
    private final BasicVillagerEntity villager;
    public LootAtTalkingTo(BasicVillagerEntity entity) {
        super(entity, PlayerEntity.class, 8.0F);
        this.villager = entity;
    }

    @Override
    public boolean canUse() {
        if (this.villager.nameActive == true) {
            this.lookAt = this.villager.level.getNearestPlayer(this.lookAtContext, this.villager, this.villager.getX(), this.villager.getEyeY(), this.villager.getZ());
            return true;
        } else {
            return false;
        }
    }
}