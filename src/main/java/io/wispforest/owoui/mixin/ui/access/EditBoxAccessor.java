package io.wispforest.owoui.mixin.ui.access;

import net.minecraft.client.gui.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EditBox.class)
public interface EditBoxAccessor {

    @Mutable
    @Accessor("width")
    void owoui$setWidth(int width);

    @Accessor("selectionEnd")
    void owoui$setSelectionEnd(int width);

    @Accessor("selectionEnd")
    int owoui$getSelectionEnd();

}
