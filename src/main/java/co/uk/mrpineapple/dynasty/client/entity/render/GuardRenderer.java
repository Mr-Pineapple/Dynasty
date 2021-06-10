package co.uk.mrpineapple.dynasty.client.entity.render;

import co.uk.mrpineapple.dynasty.client.entity.model.BasicVillagerModel;
import co.uk.mrpineapple.dynasty.client.entity.model.GuardModel;
import co.uk.mrpineapple.dynasty.common.entity.neutral.GuardEntity;
import co.uk.mrpineapple.dynasty.common.entity.passive.BasicVillagerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuardRenderer extends AbstractGuardRenderer<GuardEntity, GuardModel<GuardEntity>> {
    public GuardRenderer(EntityRendererManager p_i46127_1_) {
        super(p_i46127_1_, new GuardModel(0.0F, false), new GuardModel(0.5F, true), new GuardModel(1.0F, true));
    }
}
