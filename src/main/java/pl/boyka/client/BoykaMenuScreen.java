package pl.boyka.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

/** Original Boyka settings panel; it does not reproduce another client UI. */
public final class BoykaMenuScreen extends Screen {
    private static final int PANEL_W = 420;
    private static final int PANEL_H = 244;
    private int left;
    private int top;

    public BoykaMenuScreen() {
        super(Text.literal("BOYKA CLIENT"));
    }

    @Override
    protected void init() {
        left = (width - PANEL_W) / 2;
        top = (height - PANEL_H) / 2;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, 0x80000000);
        context.fill(left, top, left + PANEL_W, top + PANEL_H, 0xF016171C);
        context.fill(left, top, left + PANEL_W, top + 4, 0xFF8D52FF);

        context.drawTextWithShadow(textRenderer, Text.literal("BOYKA CLIENT"), left + 18, top + 17, 0xFFFFFFFF);
        context.drawTextWithShadow(textRenderer, Text.literal("VISUALS"), left + 18, top + 47, 0xFFBFA6FF);

        drawToggle(context, left + 18, top + 72, "Branding HUD", BoykaClient.config().showBranding);
        drawToggle(context, left + 18, top + 105, "Change Sky (local image)", BoykaClient.config().customSkyEnabled);

        context.drawTextWithShadow(textRenderer, Text.literal("SKY IMAGE PATH"), left + 18, top + 150, 0xFF8F949F);
        String path = BoykaClient.config().localSkyImage.isBlank() ? "Ustaw w config/boyka-client.json" : BoykaClient.config().localSkyImage;
        context.fill(left + 18, top + 166, left + PANEL_W - 18, top + 191, 0xFF262833);
        context.drawTextWithShadow(textRenderer, Text.literal(trim(path, 52)), left + 26, top + 174, 0xFFE7E7EE);

        context.drawTextWithShadow(textRenderer, Text.literal("FRIENDS"), left + 18, top + 208, 0xFFBFA6FF);
        context.drawTextWithShadow(textRenderer, Text.literal("Lista jest przechowywana lokalnie w configu."), left + 104, top + 208, 0xFFBFC1CC);
        context.drawTextWithShadow(textRenderer, Text.literal("Right Shift — zamknij"), left + 18, top + 224, 0xFF8F949F);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && in(mouseX, mouseY, left + 18, top + 72, 270, 24)) {
            BoykaClient.config().showBranding = !BoykaClient.config().showBranding;
            BoykaClient.config().save();
            return true;
        }
        if (button == 0 && in(mouseX, mouseY, left + 18, top + 105, 270, 24)) {
            BoykaClient.config().customSkyEnabled = !BoykaClient.config().customSkyEnabled;
            BoykaClient.config().save();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 344 || keyCode == 345 || keyCode == 256) {
            close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void drawToggle(DrawContext c, int x, int y, String label, boolean enabled) {
        c.fill(x, y, x + 270, y + 24, 0xFF252731);
        c.drawTextWithShadow(textRenderer, Text.literal(label), x + 10, y + 8, 0xFFFFFFFF);
        int color = enabled ? 0xFF61D47D : 0xFF6A6E79;
        c.fill(x + 235, y + 6, x + 258, y + 18, color);
    }

    private boolean in(double mx, double my, int x, int y, int w, int h) { return mx >= x && mx <= x + w && my >= y && my <= y + h; }
    private String trim(String value, int max) { return value.length() <= max ? value : value.substring(0, max - 3) + "..."; }
}
