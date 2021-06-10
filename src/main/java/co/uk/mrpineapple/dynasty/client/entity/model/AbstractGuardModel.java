package co.uk.mrpineapple.dynasty.client.entity.model;

import co.uk.mrpineapple.dynasty.common.entity.neutral.GuardEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractGuardModel<T extends GuardEntity> extends BipedModel<T> {
    protected AbstractGuardModel(float p_i51070_1_, float p_i51070_2_, int p_i51070_3_, int p_i51070_4_) {
        super(p_i51070_1_, p_i51070_2_, p_i51070_3_, p_i51070_4_);
    }

    @Override
    public void setupAnim(T entity, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        super.setupAnim(entity, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
            ModelHelper.bobArms(this.leftArm, this.rightArm, 100);
        }

    public abstract boolean isAggressive(T p_212850_1_);
}