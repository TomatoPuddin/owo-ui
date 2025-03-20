package io.wispforest.owoui.mixin.ui.access;

import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClickableWidget.class)
public interface ClickableWidgetAccessor {

    @Accessor("height")
    void owoui$setHeight(int height);

    @Accessor("width")
    void owoui$setWidth(int width);

    @Accessor("x")
    void owoui$setX(int x);

    @Accessor("y")
    void owoui$setY(int y);

    @Accessor("tooltip")
    Tooltip owoui$getTooltip();
}
