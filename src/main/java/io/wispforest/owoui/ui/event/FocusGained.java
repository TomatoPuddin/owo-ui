package io.wispforest.owoui.ui.event;

import io.wispforest.owoui.ui.core.Component;
import io.wispforest.owoui.util.EventStream;

public interface FocusGained {
    void onFocusGained(Component.FocusSource source);

    static EventStream<FocusGained> newStream() {
        return new EventStream<>(subscribers -> source -> {
            for (var subscriber : subscribers) {
                subscriber.onFocusGained(source);
            }
        });
    }
}
