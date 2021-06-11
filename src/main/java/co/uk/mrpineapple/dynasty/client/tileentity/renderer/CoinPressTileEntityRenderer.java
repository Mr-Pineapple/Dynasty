package co.uk.mrpineapple.dynasty.client.tileentity.renderer;

import co.uk.mrpineapple.dynasty.common.tileentity.CoinPressTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoinPressTileEntityRenderer extends TileEntityRenderer<CoinPressTileEntity> {

    public CoinPressTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    @Override
    public void render(CoinPressTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        ItemStack itemStack = tileEntity.getItem(0);
        if(!itemStack.isEmpty()) {
            matrixStack.pushPose();
            matrixStack.translate(0.45, 0.75, 0.5);
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(90F));
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90F));
            matrixStack.scale(0.5F, 0.5F, 0.5F);

            Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrixStack, buffer);
            matrixStack.popPose();
        }
    }
}
