package io.wispforest.owoui.mixin.ui;

import io.wispforest.owoui.ui.core.PositionedRectangle;
import io.wispforest.owoui.util.pond.OwoSlotExtension;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public class SlotMixin implements OwoSlotExtension {

    @Unique
    private boolean owoui$disabledOverride = false;

    @Unique
    private @Nullable PositionedRectangle owoui$scissorArea = null;

    @Override
    public void owoui$setDisabledOverride(boolean disabled) {
        this.owoui$disabledOverride = disabled;
    }

    @Override
    public boolean owoui$getDisabledOverride() {
        return this.owoui$disabledOverride;
    }

    @Override
    public void owoui$setScissorArea(@Nullable PositionedRectangle scissor) {
        this.owoui$scissorArea = scissor;
    }

    @Override
    public @Nullable PositionedRectangle owoui$getScissorArea() {
        return this.owoui$scissorArea;
    }

    @Inject(method = "isEnabled", at = @At("TAIL"), cancellable = true)
    private void injectOverride(CallbackInfoReturnable<Boolean> cir) {
        if (!this.owoui$disabledOverride) return;
        cir.setReturnValue(false);
    }
}
