package io.wispforest.owoui.ui.event;

import io.wispforest.owoui.util.EventStream;

public interface MouseEnter {
    void onMouseEnter();

    static EventStream<MouseEnter> newStream() {
        return new EventStream<>(subscribers -> () -> {
            for (var subscriber : subscribers) {
                subscriber.onMouseEnter();
            }
        });
    }
}
