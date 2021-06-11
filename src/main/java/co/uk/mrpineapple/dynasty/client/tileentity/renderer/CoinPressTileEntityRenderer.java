package co.uk.mrpineapple.dynasty.client.tileentity.renderer;

import co.uk.mrpineapple.dynasty.common.tileentity.CoinPressTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class CoinPressTileEntityRenderer extends TileEntityRenderer<CoinPressTileEntity> {

    public CoinPressTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    @Override
    public void render(CoinPressTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {

    }
}
