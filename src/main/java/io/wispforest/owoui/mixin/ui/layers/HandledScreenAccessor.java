package io.wispforest.owoui.mixin.ui.layers;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HandledScreen.class)
public interface HandledScreenAccessor {

    @Accessor("x")
    int owoui$getRootX();

    @Accessor("y")
    int owoui$getRootY();

}
