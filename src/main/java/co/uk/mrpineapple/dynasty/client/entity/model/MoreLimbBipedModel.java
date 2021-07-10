package co.uk.mrpineapple.dynasty.client.entity.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.function.Function;

/**
 * @author aemogie.
 */
public class MoreLimbBipedModel<T extends LivingEntity> extends BipedModel<T> {
	public ModelRenderer rightForearm;
	public ModelRenderer leftForearm;
	public ModelRenderer rightLeg2;
	public ModelRenderer leftLeg2;
	
	public MoreLimbBipedModel(float scale) {
		this(RenderType::entityCutoutNoCull, scale, 0.0F, true);
	}
	
	protected MoreLimbBipedModel(float scale, float yOffset, boolean isMirrored) {
		this(RenderType::entityTranslucent, scale, yOffset, isMirrored);
	}
	
	public MoreLimbBipedModel(Function<ResourceLocation, RenderType> renderType, float scale, float yOffset, boolean isMirrored) {
		super(renderType, scale, yOffset, 64, isMirrored ? 32 : 64);
		
		body.texOffs(20, 16);
		
		this.head = new ModelRenderer(this, 32, 16);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, scale);
		this.head.setPos(0.0F, 0.0F + yOffset, 0.0F);
		
		this.hat = new ModelRenderer(this, 32, 32);
		this.hat.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, scale + 0.5F);
		this.hat.setPos(0.0F, 0.0F + yOffset, 0.0F);
		
		this.body = new ModelRenderer(this, 36, 0);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, scale);
		this.body.setPos(0.0F, 0.0F + yOffset, 0.0F);
		
		this.rightArm = new ModelRenderer(this, 16, 0);
		this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, scale);
		this.rightArm.setPos(-5.0F, 2.0F + yOffset, 0.0F);
		
		rightForearm = new ModelRenderer(this, 16, 10);
		rightForearm.setPos(-1.0F, 4.0F + yOffset, 0.0F);
		rightArm.addChild(rightForearm);
		rightForearm.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, scale);
		
		this.leftArm = isMirrored ? new ModelRenderer(this, 16, 0) : new ModelRenderer(this, 16, 44);
		this.leftArm.mirror = isMirrored;
		this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, scale);
		this.leftArm.setPos(5.0F, 2.0F + yOffset, 0.0F);
		
		leftForearm = isMirrored ? new ModelRenderer(this, 16, 10) : new ModelRenderer(this, 16, 54);
		leftForearm.mirror = isMirrored;
		leftForearm.setPos(1.0F, 4.0F + yOffset, 0.0F);
		leftArm.addChild(leftForearm);
		leftForearm.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, scale);
		
		this.rightLeg = new ModelRenderer(this, 0, 0);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, scale);
		this.rightLeg.setPos(-1.9F, 12.0F + yOffset, 0.0F);
		
		rightLeg2 = new ModelRenderer(this, 0, 10);
		rightLeg2.setPos(0.0F, 6.0F + yOffset, 0.0F);
		rightLeg.addChild(rightLeg2);
		rightLeg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, scale);
		
		this.leftLeg = isMirrored ? new ModelRenderer(this, 0, 0) : new ModelRenderer(this, 0, 44);
		this.leftLeg.mirror = isMirrored;
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, scale);
		this.leftLeg.setPos(1.9F, 12.0F + yOffset, 0.0F);
		
		leftLeg2 = isMirrored ? new ModelRenderer(this, 0, 10) : new ModelRenderer(this, 0, 54);
		leftLeg2.mirror = isMirrored;
		leftLeg2.setPos(0.0F, 6.0F + yOffset, 0.0F);
		leftLeg.addChild(leftLeg2);
		leftLeg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, scale);
	}
	
	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		
		//region Copied from vanilla. I don't understand what it does.
		float f = 1.0F;
		if (pEntity.getFallFlyingTicks() > 4) {
			f = (float) pEntity.getDeltaMovement().lengthSqr();
			f = f / 0.2F;
			f = f * f * f;
		}
		
		if (f < 1.0F) {
			f = 1.0F;
		}
		float rightArmRot = MathHelper.cos(pLimbSwing * 0.6662F + (float) Math.PI) * 2.0F * pLimbSwingAmount * 0.5F / f;
		float leftArmRot = MathHelper.cos(pLimbSwing * 0.6662F) * 2.0F * pLimbSwingAmount * 0.5F / f;
		float rightLegRot = MathHelper.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount / f;
		float leftLegRot = MathHelper.cos(pLimbSwing * 0.6662F + (float) Math.PI) * 1.4F * pLimbSwingAmount / f;
		//endregion
		
		this.rightForearm.xRot = rightArmRot >= 0 ? 0 : rightArmRot;
		this.leftForearm.xRot = leftArmRot >= 0 ? 0 : leftArmRot;
		this.rightLeg2.xRot = rightLegRot > 0 ? 0 : rightLegRot * -1;
		this.leftLeg2.xRot = leftLegRot > 0 ? 0 : leftLegRot * -1;
		
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
	}
}