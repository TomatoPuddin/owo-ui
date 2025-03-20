package io.wispforest.owoui.ui.event;

import io.wispforest.owoui.util.EventStream;

public interface CharTyped {
    boolean onCharTyped(char chr, int modifiers);

    static EventStream<CharTyped> newStream() {
        return new EventStream<>(subscribers -> (chr, modifiers) -> {
            var anyTriggered = false;
            for (var subscriber : subscribers) {
                anyTriggered |= subscriber.onCharTyped(chr, modifiers);
            }
            return anyTriggered;
        });
    }
}
