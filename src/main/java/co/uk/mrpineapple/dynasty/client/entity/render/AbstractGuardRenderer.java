package co.uk.mrpineapple.dynasty.client.entity.render;

import co.uk.mrpineapple.dynasty.client.entity.model.GuardModel;
import co.uk.mrpineapple.dynasty.common.entity.neutral.GuardEntity;
import co.uk.mrpineapple.dynasty.core.Dynasty;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractGuardRenderer<T extends GuardEntity, M extends GuardModel<T>> extends BipedRenderer<T, M> {

    private static final ResourceLocation GUARD_LOCATION = new ResourceLocation(Dynasty.ID, "/textures/entity/guard/guard.png");


    protected AbstractGuardRenderer(EntityRendererManager p_i50974_1_, M p_i50974_2_, M p_i50974_3_, M p_i50974_4_) {
        super(p_i50974_1_, p_i50974_2_, 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, p_i50974_3_, p_i50974_4_));
    }

    @Override
    public ResourceLocation getTextureLocation(GuardEntity p_110775_1_) {
        return GUARD_LOCATION;
    }

    @Override
    protected boolean isShaking(T p_230495_1_) {
        return p_230495_1_.isUnderWaterConverting();
    }
}