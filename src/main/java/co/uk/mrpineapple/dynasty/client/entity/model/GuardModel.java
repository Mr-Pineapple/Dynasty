package co.uk.mrpineapple.dynasty.client.entity.model;

import co.uk.mrpineapple.dynasty.common.entity.neutral.GuardEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuardModel<T extends GuardEntity> extends AbstractGuardModel<T> {
    public GuardModel(float p_i1168_1_, boolean p_i1168_2_) {
        this(p_i1168_1_, 0.0F, p_i1168_2_);
    }

    protected GuardModel(float p_i48914_1_, float p_i48914_2_, boolean isMirrored) {
        super(p_i48914_1_, p_i48914_2_, isMirrored);
    }

    @Override
    public boolean isAggressive(T p_212850_1_) {
        return p_212850_1_.isAggressive();
    }
}