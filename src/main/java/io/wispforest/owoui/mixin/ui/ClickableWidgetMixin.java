package io.wispforest.owoui.mixin.ui;

import io.wispforest.owoui.ui.component.Components;
import io.wispforest.owoui.ui.component.VanillaWidgetComponent;
import io.wispforest.owoui.ui.core.*;
import io.wispforest.owoui.ui.event.*;
import io.wispforest.owoui.ui.inject.ComponentStub;
import io.wispforest.owoui.ui.parsing.UIModel;
import io.wispforest.owoui.ui.parsing.UIParsing;
import io.wispforest.owoui.ui.util.FocusHandler;
import io.wispforest.owoui.util.EventSource;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("ConstantConditions")
@Mixin(ClickableWidget.class)
public abstract class ClickableWidgetMixin implements ComponentStub, net.minecraft.client.gui.Element {

    @Shadow public boolean active;

    @Unique
    protected VanillaWidgetComponent owoui$wrapper = null;

    @Override
    public void inflate(Size space) {
        this.owoui$getWrapper().inflate(space);
    }

    @Override
    public void mount(ParentComponent parent, int x, int y) {
        this.owoui$getWrapper().mount(parent, x, y);
    }

    @Override
    public void dismount(DismountReason reason) {
        this.owoui$getWrapper().dismount(reason);
    }

    @Nullable
    @Override
    public ParentComponent parent() {
        return this.owoui$getWrapper().parent();
    }

    @Override
    public @Nullable FocusHandler focusHandler() {
        return this.owoui$getWrapper().focusHandler();
    }

    @Override
    public Component positioning(Positioning positioning) {
        this.owoui$getWrapper().positioning(positioning);
        return this;
    }

    @Override
    public AnimatableProperty<Positioning> positioning() {
        return this.owoui$getWrapper().positioning();
    }

    @Override
    public Component margins(Insets margins) {
        this.owoui$getWrapper().margins(margins);
        return this;
    }

    @Override
    public AnimatableProperty<Insets> margins() {
        return this.owoui$getWrapper().margins();
    }

    @Override
    public Component horizontalSizing(Sizing horizontalSizing) {
        this.owoui$getWrapper().horizontalSizing(horizontalSizing);
        return this;
    }

    @Override
    public Component verticalSizing(Sizing verticalSizing) {
        this.owoui$getWrapper().verticalSizing(verticalSizing);
        return this;
    }

    @Override
    public AnimatableProperty<Sizing> horizontalSizing() {
        return this.owoui$getWrapper().horizontalSizing();
    }

    @Override
    public AnimatableProperty<Sizing> verticalSizing() {
        return this.owoui$getWrapper().verticalSizing();
    }

    @Override
    public EventSource<MouseDown> mouseDown() {
        return this.owoui$getWrapper().mouseDown();
    }

    @Override
    public int x() {
        return this.owoui$getWrapper().x();
    }

    @Override
    public int y() {
        return this.owoui$getWrapper().y();
    }

    @Override
    public int width() {
        return this.owoui$getWrapper().width();
    }

    @Override
    public int height() {
        return this.owoui$getWrapper().height();
    }

    @Override
    public void draw(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        this.owoui$getWrapper().draw(context, mouseX, mouseY, partialTicks, delta);
    }

    @Override
    public boolean shouldDrawTooltip(double mouseX, double mouseY) {
        return this.owoui$getWrapper().shouldDrawTooltip(mouseX, mouseY);
    }

    @Override
    public void update(float delta, int mouseX, int mouseY) {
        this.owoui$getWrapper().update(delta, mouseX, mouseY);
        this.cursorStyle(this.active ? this.owoui$preferredCursorStyle() : CursorStyle.POINTER);
    }

    @Override
    public boolean onMouseDown(double mouseX, double mouseY, int button) {
        return this.owoui$getWrapper().onMouseDown(mouseX, mouseY, button);
    }

    @Override
    public boolean onMouseUp(double mouseX, double mouseY, int button) {
        return this.owoui$getWrapper().onMouseUp(mouseX, mouseY, button);
    }

    @Override
    public EventSource<MouseUp> mouseUp() {
        return this.owoui$getWrapper().mouseUp();
    }

    @Override
    public EventSource<MouseScroll> mouseScroll() {
        return this.owoui$getWrapper().mouseScroll();
    }

    @Override
    public EventSource<MouseDrag> mouseDrag() {
        return this.owoui$getWrapper().mouseDrag();
    }

    @Override
    public EventSource<KeyPress> keyPress() {
        return this.owoui$getWrapper().keyPress();
    }

    @Override
    public EventSource<CharTyped> charTyped() {
        return this.owoui$getWrapper().charTyped();
    }

    @Override
    public EventSource<FocusGained> focusGained() {
        return this.owoui$getWrapper().focusGained();
    }

    @Override
    public EventSource<FocusLost> focusLost() {
        return this.owoui$getWrapper().focusLost();
    }

    @Override
    public EventSource<MouseEnter> mouseEnter() {
        return this.owoui$getWrapper().mouseEnter();
    }

    @Override
    public EventSource<MouseLeave> mouseLeave() {
        return this.owoui$getWrapper().mouseLeave();
    }

    @Override
    public boolean onMouseScroll(double mouseX, double mouseY, double amount) {
        return this.owoui$getWrapper().onMouseScroll(mouseX, mouseY, amount);
    }

    @Override
    public boolean onMouseDrag(double mouseX, double mouseY, double deltaX, double deltaY, int button) {
        return this.owoui$getWrapper().onMouseDrag(mouseX, mouseY, deltaX, deltaY, button);
    }

    @Override
    public boolean onKeyPress(int keyCode, int scanCode, int modifiers) {
        return this.owoui$getWrapper().onKeyPress(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean onCharTyped(char chr, int modifiers) {
        return this.owoui$getWrapper().onCharTyped(chr, modifiers);
    }

    @Override
    public boolean canFocus(FocusSource source) {
        return true;
    }

    @Override
    public void onFocusGained(FocusSource source) {
        this.setFocused(source == FocusSource.KEYBOARD_CYCLE);
        this.owoui$getWrapper().onFocusGained(source);
    }

    @Override
    public void onFocusLost() {
        this.setFocused(false);
        this.owoui$getWrapper().onFocusLost();
    }

    @Override
    public <C extends Component> C configure(Consumer<C> closure) {
        return this.owoui$getWrapper().configure(closure);
    }

    @Override
    public void parseProperties(UIModel spec, Element element, Map<String, Element> children) {
        // --- copied from Component, because you can't invoke interface super methods in mixins - very cool ---

        if (!element.getAttribute("id").isBlank()) {
            this.id(element.getAttribute("id").strip());
        }

        UIParsing.apply(children, "margins", Insets::parse, this::margins);
        UIParsing.apply(children, "positioning", Positioning::parse, this::positioning);
        UIParsing.apply(children, "z-index", UIParsing::parseSignedInt, this::zIndex);
        UIParsing.apply(children, "cursor-style", UIParsing.parseEnum(CursorStyle.class), this::cursorStyle);
        UIParsing.apply(children, "tooltip-text", UIParsing::parseText, this::tooltip);

        if (children.containsKey("sizing")) {
            var sizingValues = UIParsing.childElements(children.get("sizing"));
            UIParsing.apply(sizingValues, "vertical", Sizing::parse, this::verticalSizing);
            UIParsing.apply(sizingValues, "horizontal", Sizing::parse, this::horizontalSizing);
        }

        // --- end ---

        UIParsing.apply(children, "active", UIParsing::parseBool, active -> this.active = active);
    }

    @Override
    public CursorStyle cursorStyle() {
        return this.owoui$getWrapper().cursorStyle();
    }

    @Override
    public Component cursorStyle(CursorStyle style) {
        return this.owoui$getWrapper().cursorStyle(style);
    }

    @Override
    public Component tooltip(List<TooltipComponent> tooltip) {
        return this.owoui$getWrapper().tooltip(tooltip);
    }

    @Override
    public List<TooltipComponent> tooltip() {
        return this.owoui$getWrapper().tooltip();
    }

    @Override
    public Component zIndex(int zIndex) {
        return this.owoui$getWrapper().zIndex(zIndex);
    }

    @Override
    public int zIndex() {
        return this.owoui$getWrapper().zIndex();
    }

    @Override
    public Component id(@Nullable String id) {
        this.owoui$getWrapper().id(id);
        return this;
    }

    @Override
    public @Nullable String id() {
        return this.owoui$getWrapper().id();
    }

    @Unique
    protected VanillaWidgetComponent owoui$getWrapper() {
        if (this.owoui$wrapper == null) {
            this.owoui$wrapper = Components.wrapVanillaWidget((ClickableWidget) (Object) this);
        }

        return this.owoui$wrapper;
    }

    @Override
    public @Nullable VanillaWidgetComponent widgetWrapper() {
        return this.owoui$wrapper;
    }

    @Override
    public int xOffset() {
        return 0;
    }

    @Override
    public int yOffset() {
        return 0;
    }

    @Override
    public int widthOffset() {
        return 0;
    }

    @Override
    public int heightOffset() {
        return 0;
    }

    @Inject(method = "setWidth", at = @At("HEAD"), cancellable = true)
    private void applyWidthToWrapper(int width, CallbackInfo ci) {
        var wrapper = this.owoui$wrapper;
        if (wrapper != null) {
            wrapper.horizontalSizing(Sizing.fixed(width));
            ci.cancel();
        }
    }

    @Override
    public void updateX(int x) {
        this.owoui$getWrapper().updateX(x);
    }

    @Override
    public void updateY(int y) {
        this.owoui$getWrapper().updateY(y);
    }

    protected CursorStyle owoui$preferredCursorStyle() {
        return CursorStyle.POINTER;
    }
}
