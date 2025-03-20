package io.wispforest.owoui.mixin.ui.access;

import io.wispforest.owoui.ui.base.BaseOwoHandledScreen;
import io.wispforest.owoui.ui.core.OwoUIAdapter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = BaseOwoHandledScreen.class, remap = false)
public interface BaseOwoHandledScreenAccessor {
    @Accessor("uiAdapter")
    OwoUIAdapter<?> owoui$getUIAdapter();
}
