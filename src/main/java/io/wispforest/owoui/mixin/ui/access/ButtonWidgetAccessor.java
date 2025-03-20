package io.wispforest.owoui.mixin.ui.access;

import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ButtonWidget.class)
public interface ButtonWidgetAccessor {

    @Mutable
    @Accessor("onPress")
    void owoui$setOnPress(ButtonWidget.PressAction onPress);

}
