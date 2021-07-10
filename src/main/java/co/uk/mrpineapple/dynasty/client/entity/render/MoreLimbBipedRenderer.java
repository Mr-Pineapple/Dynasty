package co.uk.mrpineapple.dynasty.client.entity.render;

import co.uk.mrpineapple.dynasty.client.entity.model.MoreLimbBipedModel;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

import static co.uk.mrpineapple.dynasty.core.Dynasty.ID;

/**
 * @author aemogie.
 */
public class MoreLimbBipedRenderer<T extends MobEntity, M extends MoreLimbBipedModel<T>> extends BipedRenderer<T, M> {
	
	private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(ID, "textures/entity/template.png");
	
	public MoreLimbBipedRenderer(EntityRendererManager manager, M model, float shadowRadius) {
		super(manager, model, shadowRadius);
	}
	
	@Override
	public ResourceLocation getTextureLocation(T pEntity) {
		return DEFAULT_TEXTURE;
	}
}