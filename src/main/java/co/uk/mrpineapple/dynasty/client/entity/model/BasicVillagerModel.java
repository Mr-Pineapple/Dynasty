package co.uk.mrpineapple.dynasty.client.entity.model;

import co.uk.mrpineapple.dynasty.common.entity.passive.BasicVillagerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BasicVillagerModel<T extends BasicVillagerEntity> extends AbstractBasicVillagerModel<T> {
    public BasicVillagerModel(float p_i1168_1_, boolean p_i1168_2_) {
        this(p_i1168_1_, 0.0F, 64, p_i1168_2_ ? 32 : 64);
    }

    protected BasicVillagerModel(float p_i48914_1_, float p_i48914_2_, int p_i48914_3_, int p_i48914_4_) {
        super(p_i48914_1_, p_i48914_2_, p_i48914_3_, p_i48914_4_);
    }

    @Override
    public boolean isAggressive(T p_212850_1_) {
        return p_212850_1_.isAggressive();
    }
}