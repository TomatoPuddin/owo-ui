package io.wispforest.owoui.mixin.ui.access;

import io.wispforest.owoui.ui.component.TextBoxComponent;
import io.wispforest.owoui.util.Observable;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

// now you might think that simply AW'ing onChanged in TextFieldWidget
// would be the way to go about this. but you see, tiny remapper (or more specifically how
// loom uses it) begs to differ and simply does not remap your override, causing
// that approach to break in prod. thus we need to mix into TextFieldWidget
// and use this accessor to update it instead
@ApiStatus.Internal
@Mixin(value = TextBoxComponent.class, remap = false)
public interface TextBoxComponentAccessor {

    @Accessor("textValue")
    Observable<String> owoui$textValue();

}
