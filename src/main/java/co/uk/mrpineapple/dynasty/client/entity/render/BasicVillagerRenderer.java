package co.uk.mrpineapple.dynasty.client.entity.render;

import co.uk.mrpineapple.dynasty.client.entity.model.BasicVillagerModel;
import co.uk.mrpineapple.dynasty.common.entity.passive.BasicVillagerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BasicVillagerRenderer extends AbstractBasicVillagerRenderer<BasicVillagerEntity, BasicVillagerModel<BasicVillagerEntity>> {
    public BasicVillagerRenderer(EntityRendererManager p_i46127_1_) {
        super(p_i46127_1_, new BasicVillagerModel(0.0F, false), new BasicVillagerModel(0.5F, true), new BasicVillagerModel(1.0F, true));
    }
}
