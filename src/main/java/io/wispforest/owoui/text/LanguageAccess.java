package io.wispforest.owoui.text;

import net.minecraft.text.Text;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiConsumer;

@ApiStatus.Internal
public class LanguageAccess {
    public static BiConsumer<String, Text> textConsumer;
}
