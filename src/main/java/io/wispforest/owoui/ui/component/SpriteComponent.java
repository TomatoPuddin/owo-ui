package io.wispforest.owoui.ui.component;

import io.wispforest.owoui.ui.base.BaseComponent;
import io.wispforest.owoui.ui.core.OwoUIDrawContext;
import io.wispforest.owoui.ui.core.Sizing;
import io.wispforest.owoui.ui.parsing.UIParsing;
import io.wispforest.owoui.ui.util.SpriteUtilInvoker;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import org.w3c.dom.Element;

public class SpriteComponent extends BaseComponent {

    protected final Sprite sprite;

    protected SpriteComponent(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    protected int determineHorizontalContentSize(Sizing sizing) {
        return this.sprite.getContents().getWidth();
    }

    @Override
    protected int determineVerticalContentSize(Sizing sizing) {
        return this.sprite.getContents().getHeight();
    }

    @Override
    public void draw(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        SpriteUtilInvoker.markSpriteActive(this.sprite);
        context.drawSprite(this.x, this.y, 0, this.width, this.height, this.sprite);
    }

    public static SpriteComponent parse(Element element) {
        UIParsing.expectAttributes(element, "atlas", "sprite");

        var atlas = UIParsing.parseIdentifier(element.getAttributeNode("atlas"));
        var sprite = UIParsing.parseIdentifier(element.getAttributeNode("sprite"));

        return Components.sprite(new SpriteIdentifier(atlas, sprite));
    }
}
