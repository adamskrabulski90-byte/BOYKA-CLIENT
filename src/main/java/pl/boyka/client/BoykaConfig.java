package pl.boyka.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/** Stores only cosmetic client preferences. */
public final class BoykaConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("boyka-client.json");

    public boolean showBranding = true;
    public boolean customSkyEnabled = false;
    public String localSkyImage = "";
    public String[] friends = new String[0];

    public static BoykaConfig load() {
        try {
            if (Files.exists(FILE)) {
                BoykaConfig loaded = GSON.fromJson(Files.readString(FILE, StandardCharsets.UTF_8), BoykaConfig.class);
                if (loaded != null) return loaded;
            }
        } catch (IOException | RuntimeException ignored) { }
        return new BoykaConfig();
    }

    public void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(this), StandardCharsets.UTF_8);
        } catch (IOException ignored) { }
    }
}
