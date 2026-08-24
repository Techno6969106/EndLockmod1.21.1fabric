package io.github.endlock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndLockMod implements ModInitializer {
    public static final String MOD_ID = "endlock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LockEndCommand.register(dispatcher);
        });
        LOGGER.info("EndLock mod initialized");
    }
}
