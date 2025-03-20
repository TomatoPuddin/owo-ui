package io.wispforest.owoui.util.pond;

import io.wispforest.owoui.ui.core.ParentComponent;
import io.wispforest.owoui.ui.layers.Layer;
import net.minecraft.client.gui.screen.Screen;

import java.util.List;

public interface OwoScreenExtension {
    List<Layer<?, ?>.Instance> owoui$getInstancesView();
    <S extends Screen, R extends ParentComponent> Layer<S, R>.Instance owoui$getInstance(Layer<S, R> layer);

    void owoui$updateLayers();
}
