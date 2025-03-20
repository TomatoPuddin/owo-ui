package io.wispforest.owoui.ui.hud;

import io.wispforest.owoui.ui.container.FlowLayout;
import io.wispforest.owoui.ui.core.Component;
import io.wispforest.owoui.ui.core.Positioning;
import io.wispforest.owoui.ui.core.Sizing;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Very simple extension of {@link io.wispforest.owoui.ui.container.FlowLayout} that
 * does not allow children to be layout-positioned, used by {@link Hud}
 */
public class HudContainer extends FlowLayout {

    protected HudContainer(Sizing horizontalSizing, Sizing verticalSizing) {
        super(horizontalSizing, verticalSizing, Algorithm.VERTICAL);
    }

    @Override
    protected void mountChild(@Nullable Component child, Consumer<Component> layoutFunc) {
        if (child == null) return;

        if (child.positioning().get().type == Positioning.Type.LAYOUT) {
            throw new IllegalStateException("owoui HUD components must be explicitly positioned");
        } else {
            super.mountChild(child, layoutFunc);
        }
    }
}
