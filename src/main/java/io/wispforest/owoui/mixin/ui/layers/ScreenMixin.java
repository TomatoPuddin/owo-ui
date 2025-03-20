package io.wispforest.owoui.mixin.ui.layers;

import io.wispforest.owoui.ui.core.ParentComponent;
import io.wispforest.owoui.ui.layers.Layer;
import io.wispforest.owoui.ui.layers.Layers;
import io.wispforest.owoui.util.pond.OwoScreenExtension;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.*;

@Mixin(value = Screen.class, priority = 1100)
public abstract class ScreenMixin extends AbstractParentElement implements OwoScreenExtension {

    @Shadow public int width;
    @Shadow public int height;

    private final List<Layer<?, ?>.Instance> owoui$instances = new ArrayList<>();
    private final List<Layer<?, ?>.Instance> owoui$instancesView = Collections.unmodifiableList(this.owoui$instances);
    private final Map<Layer<?, ?>, Layer<?, ?>.Instance> owoui$layersToInstances = new HashMap<>();

    private boolean owoui$layersInitialized = false;

    @SuppressWarnings("ConstantConditions")
    private Screen owoui$this() {
        return (Screen) (Object) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void owoui$updateLayers() {
        if (this.owoui$layersInitialized) {
            for (var instance : this.owoui$instances) {
                instance.resize(this.width, this.height);
            }
        } else {
            for (var layer : Layers.getLayers((Class<Screen>) this.owoui$this().getClass())) {
                var instance = layer.instantiate(this.owoui$this());
                this.owoui$instances.add(instance);
                this.owoui$layersToInstances.put(layer, instance);

                instance.adapter.inflateAndMount();
            }

            this.owoui$layersInitialized = true;
        }

        this.owoui$instances.forEach(Layer.Instance::dispatchLayoutUpdates);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S extends Screen, R extends ParentComponent> Layer<S, R>.Instance owoui$getInstance(Layer<S, R> layer) {
        return (Layer<S, R>.Instance) this.owoui$layersToInstances.get(layer);
    }

    @Override
    public List<Layer<?, ?>.Instance> owoui$getInstancesView() {
        return this.owoui$instancesView;
    }
}
