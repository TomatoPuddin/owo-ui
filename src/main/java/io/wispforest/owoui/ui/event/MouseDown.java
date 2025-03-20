package io.wispforest.owoui.ui.event;

import io.wispforest.owoui.util.EventStream;

public interface MouseDown {
    boolean onMouseDown(double mouseX, double mouseY, int button);

    static EventStream<MouseDown> newStream() {
        return new EventStream<>(subscribers -> (mouseX, mouseY, button) -> {
            var anyTriggered = false;
            for (var subscriber : subscribers) {
                anyTriggered |= subscriber.onMouseDown(mouseX, mouseY, button);
            }
            return anyTriggered;
        });
    }
}
