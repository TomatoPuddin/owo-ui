package io.wispforest.owoui.mixin.ui;

import io.wispforest.owoui.util.pond.OwoEntityRenderDispatcherExtension;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin implements OwoEntityRenderDispatcherExtension {

    private boolean owoui$showNametag = true;
    private boolean owoui$counterRotate = false;

    @Override
    public void owoui$setShowNametag(boolean showNametag) {
        this.owoui$showNametag = showNametag;
    }

    @Override
    public boolean owoui$showNametag() {
        return this.owoui$showNametag;
    }

    @Override
    public void owoui$setCounterRotate(boolean counterRotate) {
        this.owoui$counterRotate = counterRotate;
    }

    @Override
    public boolean owoui$counterRotate() {
        return this.owoui$counterRotate;
    }

    @Shadow public Camera camera;

    @Inject(method = "renderFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionf;)V", shift = At.Shift.AFTER))
    private void cancelFireRotation(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity, CallbackInfo ci) {
        if (!this.owoui$counterRotate) return;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(this.camera.getYaw() + 170));
        matrices.translate(0, 0, .1);
    }
}
