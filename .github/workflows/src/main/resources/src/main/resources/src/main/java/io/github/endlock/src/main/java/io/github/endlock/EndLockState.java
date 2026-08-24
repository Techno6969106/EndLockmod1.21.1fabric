package io.github.endlock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class EndLockState {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "endlock.json";
    
    private boolean locked = false;
    
    public boolean isLocked() {
        return locked;
    }
    
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    public static EndLockState load(MinecraftServer server) {
        Path path = server.getSavePath(WorldSavePath.ROOT).resolve(FILE_NAME);
        if (Files.exists(path)) {
            try (Reader reader = Files.newBufferedReader(path)) {
                return GSON.fromJson(reader, EndLockState.class);
            } catch (IOException e) {
                EndLockMod.LOGGER.error("Failed to load EndLock state", e);
            }
        }
        return new EndLockState();
    }
    
    public static void save(MinecraftServer server, EndLockState state) {
        Path path = server.getSavePath(WorldSavePath.ROOT).resolve(FILE_NAME);
        try {
            Files.createDirectories(path.getParent());
            try (Writer writer = Files.newBufferedWriter(path)) {
                GSON.toJson(state, writer);
            }
        } catch (IOException e) {
            EndLockMod.LOGGER.error("Failed to save EndLock state", e);
        }
    }
}
