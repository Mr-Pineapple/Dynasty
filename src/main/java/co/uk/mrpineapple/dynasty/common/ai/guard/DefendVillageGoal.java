package co.uk.mrpineapple.dynasty.common.ai.guard;

import java.util.EnumSet;
import java.util.List;

import co.uk.mrpineapple.dynasty.common.entity.neutral.GuardEntity;
import co.uk.mrpineapple.dynasty.common.entity.passive.BasicVillagerEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class DefendVillageGoal extends TargetGoal {
    private final GuardEntity guard;
    private LivingEntity potentialTarget;
    private final EntityPredicate attackTargeting = (new EntityPredicate()).range(64.0D);

    public DefendVillageGoal(GuardEntity p_i1659_1_) {
        super(p_i1659_1_, false, true);
        this.guard = p_i1659_1_;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        AxisAlignedBB axisalignedbb = this.guard.getBoundingBox().inflate(10.0D, 8.0D, 10.0D);
        List<LivingEntity> list = this.guard.level.getNearbyEntities(BasicVillagerEntity.class, this.attackTargeting, this.guard, axisalignedbb);
        List<PlayerEntity> list1 = this.guard.level.getNearbyPlayers(this.attackTargeting, this.guard, axisalignedbb);

        for(LivingEntity livingentity : list) {
            VillagerEntity villagerentity = (VillagerEntity)livingentity;

            for(PlayerEntity playerentity : list1) {
                int i = villagerentity.getPlayerReputation(playerentity);
                if (i <= -100) {
                    this.potentialTarget = playerentity;
                }
            }
        }

        if (this.potentialTarget == null) {
            return false;
        } else {
            return !(this.potentialTarget instanceof PlayerEntity) || !this.potentialTarget.isSpectator() && !((PlayerEntity)this.potentialTarget).isCreative();
        }
    }

    @Override
    public void start() {
        this.guard.setTarget(this.potentialTarget);
        super.start();
    }
}