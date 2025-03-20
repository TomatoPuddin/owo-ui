package io.wispforest.owoui.client;

import io.wispforest.owoui.Owo;
import io.wispforest.owoui.command.debug.OwoDebugCommands;
import io.wispforest.owoui.shader.BlurProgram;
import io.wispforest.owoui.shader.GlProgram;
import io.wispforest.owoui.ui.parsing.UIModelLoader;
import io.wispforest.owoui.ui.util.NinePatchTexture;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class OwoClient implements ClientModInitializer {
    public static final GlProgram HSV_PROGRAM = new GlProgram(new Identifier("owoui", "spectrum"), VertexFormats.POSITION_COLOR);
    public static final BlurProgram BLUR_PROGRAM = new BlurProgram();

    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new UIModelLoader());
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new NinePatchTexture.MetadataLoader());

        if (Owo.DEBUG) {
            OwoDebugCommands.Client.register();
        }
    }
}
