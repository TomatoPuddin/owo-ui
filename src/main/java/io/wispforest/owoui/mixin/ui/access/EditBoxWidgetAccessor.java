package io.wispforest.owoui.mixin.ui.access;

import net.minecraft.client.gui.EditBox;
import net.minecraft.client.gui.widget.EditBoxWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EditBoxWidget.class)
public interface EditBoxWidgetAccessor {

    @Accessor("editBox")
    EditBox owoui$getEditBox();

}
