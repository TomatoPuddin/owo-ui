package io.wispforest.owoui.mixin.ui.access;

import net.minecraft.client.gui.widget.CheckboxWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CheckboxWidget.class)
public interface CheckboxWidgetAccessor {
    @Accessor("checked")
    void owoui$setChecked(boolean checked);
}
