package io.wispforest.uwu;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class Uwu implements ModInitializer {

    public static final boolean WE_TESTEN_HANDSHAKE = false;

    public static final TagKey<Item> TAB_2_CONTENT = TagKey.of(RegistryKeys.ITEM, new Identifier("uwu", "tab_2_content"));
    public static final Identifier GROUP_TEXTURE = new Identifier("uwu", "textures/gui/group.png");
    public static final Identifier OWO_ICON_TEXTURE = new Identifier("uwu", "textures/gui/icon.png");
    public static final Identifier ANIMATED_BUTTON_TEXTURE = new Identifier("uwu", "textures/gui/animated_icon_test.png");


    public static final ItemGroup VANILLA_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier("uwu", "vanilla_group"), FabricItemGroup.builder()
            .displayName(Text.literal("who did this"))
            .icon(Items.ACACIA_BOAT::getDefaultStack)
            .entries((context, entries) -> entries.add(Items.MANGROVE_CHEST_BOAT))
            .build());


    @Override
    public void onInitialize() {
    }

    public record OtherTestMessage(BlockPos pos, String message) {}
}