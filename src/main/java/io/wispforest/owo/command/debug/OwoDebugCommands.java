package io.wispforest.owo.command.debug;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import io.wispforest.owo.ui.hud.HudInspectorScreen;
import io.wispforest.owo.ui.parsing.ConfigureHotReloadScreen;
import io.wispforest.owo.ui.parsing.UIModelLoader;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

@ApiStatus.Internal
public class OwoDebugCommands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
        });
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        private static final SuggestionProvider<FabricClientCommandSource> LOADED_UI_MODELS =
                (context, builder) -> CommandSource.suggestIdentifiers(UIModelLoader.allLoadedModels(), builder);

        private static final SimpleCommandExceptionType NO_SUCH_UI_MODEL = new SimpleCommandExceptionType(Text.literal("No such UI model is loaded"));

        public static void register() {
            ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
                dispatcher.register(ClientCommandManager.literal("owo-hud-inspect")
                        .executes(context -> {
                            MinecraftClient.getInstance().setScreen(new HudInspectorScreen());
                            return 0;
                        }));

                dispatcher.register(ClientCommandManager.literal("owo-ui-set-reload-path")
                        .then(ClientCommandManager.argument("model-id", IdentifierArgumentType.identifier()).suggests(LOADED_UI_MODELS).executes(context -> {
                            var modelId = context.getArgument("model-id", Identifier.class);
                            if (UIModelLoader.getPreloaded(modelId) == null) throw NO_SUCH_UI_MODEL.create();

                            MinecraftClient.getInstance().setScreen(new ConfigureHotReloadScreen(modelId, null));
                            return 0;
                        })));
            });
        }
    }
}
