package io.wispforest.owoui.ui.component;

import io.wispforest.owoui.mixin.ui.access.TextFieldWidgetAccessor;
import io.wispforest.owoui.ui.core.CursorStyle;
import io.wispforest.owoui.ui.core.OwoUIDrawContext;
import io.wispforest.owoui.ui.core.Sizing;
import io.wispforest.owoui.ui.parsing.UIModel;
import io.wispforest.owoui.ui.parsing.UIParsing;
import io.wispforest.owoui.util.EventSource;
import io.wispforest.owoui.util.EventStream;
import io.wispforest.owoui.util.Observable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.w3c.dom.Element;

import java.util.Map;
import java.util.function.Consumer;

public class TextBoxComponent extends TextFieldWidget {

    protected final Observable<Boolean> showsBackground = Observable.of(((TextFieldWidgetAccessor) this).owoui$drawsBackground());

    protected final Observable<String> textValue = Observable.of("");
    protected final EventStream<OnChanged> changedEvents = OnChanged.newStream();

    protected TextBoxComponent(Sizing horizontalSizing) {
        super(MinecraftClient.getInstance().textRenderer, 0, 0, 0, 0, Text.empty());

        this.textValue.observe(this.changedEvents.sink()::onChanged);
        this.sizing(horizontalSizing, Sizing.content());

        this.showsBackground.observe(a -> this.widgetWrapper().notifyParentIfMounted());
    }

    /**
     * @deprecated Subscribe to {@link #onChanged()} instead
     */
    @Override
    @Deprecated(forRemoval = true)
    public void setChangedListener(Consumer<String> changedListener) {
        super.setChangedListener(changedListener);
    }

    @Override
    public void drawFocusHighlight(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        // noop, since TextFieldWidget already does this
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean result = super.keyPressed(keyCode, scanCode, modifiers);

        if (keyCode == GLFW.GLFW_KEY_TAB) {
            this.write("    ");
            return true;
        } else {
            return result;
        }
    }

    @Override
    public void setDrawsBackground(boolean drawsBackground) {
        super.setDrawsBackground(drawsBackground);
        this.showsBackground.set(drawsBackground);
    }

    public EventSource<OnChanged> onChanged() {
        return changedEvents.source();
    }

    public TextBoxComponent text(String text) {
        this.setText(text);
        this.setCursorToStart();
        return this;
    }

    @Override
    public void parseProperties(UIModel spec, Element element, Map<String, Element> children) {
        super.parseProperties(spec, element, children);
        UIParsing.apply(children, "show-background", UIParsing::parseBool, this::setDrawsBackground);
        UIParsing.apply(children, "max-length", UIParsing::parseUnsignedInt, this::setMaxLength);
        UIParsing.apply(children, "text", e -> e.getTextContent().strip(), this::text);
    }

    protected CursorStyle owoui$preferredCursorStyle() {
        return CursorStyle.TEXT;
    }

    public interface OnChanged {
        void onChanged(String value);

        static EventStream<OnChanged> newStream() {
            return new EventStream<>(subscribers -> value -> {
                for (var subscriber : subscribers) {
                    subscriber.onChanged(value);
                }
            });
        }
    }
}
