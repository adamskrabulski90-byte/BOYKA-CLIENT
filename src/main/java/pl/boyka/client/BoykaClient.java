package pl.boyka.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

/** Entry point for the safe, client-side visual shell. */
public final class BoykaClient implements ClientModInitializer {
    public static final String MOD_ID = "boyka_client";
    private static KeyBinding openMenuKey;
    private static final BoykaConfig CONFIG = BoykaConfig.load();

    public static BoykaConfig config() { return CONFIG; }

    @Override
    public void onInitializeClient() {
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.boyka_client.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.boyka_client"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                client.setScreen(new BoykaMenuScreen());
            }
        });

        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (!CONFIG.showBranding || client.options.hudHidden) return;

            context.fill(8, 8, 122, 28, 0xC916171C);
            context.fill(8, 8, 11, 28, 0xFF8D52FF);
            context.drawTextWithShadow(client.textRenderer, Text.literal("BOYKA CLIENT"), 17, 14, 0xFFFFFFFF);
        });
    }
}
