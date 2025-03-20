package io.wispforest.owoui.util.pond;

import io.wispforest.owoui.ui.core.PositionedRectangle;
import org.jetbrains.annotations.Nullable;

public interface OwoSlotExtension {

    void owoui$setDisabledOverride(boolean disabled);

    boolean owoui$getDisabledOverride();

    void owoui$setScissorArea(@Nullable PositionedRectangle scissor);

    @Nullable PositionedRectangle owoui$getScissorArea();
}
