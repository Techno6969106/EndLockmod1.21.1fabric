package io.github.endlock;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class LockEndCommand {
    
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("lockend")
            .requires(source -> source.hasPermissionLevel(2))
            .executes(context -> {
                EndLockState state = EndLockState.load(context.getSource().getServer());
                state.setLocked(true);
                EndLockState.save(context.getSource().getServer(), state);
                context.getSource().sendFeedback(() -> Text.literal("§cThe End has been locked!"), true);
                return 1;
            }));
            
        dispatcher.register(literal("unlockend")
            .requires(source -> source.hasPermissionLevel(2))
            .executes(context -> {
                EndLockState state = EndLockState.load(context.getSource().getServer());
                state.setLocked(false);
                EndLockState.save(context.getSource().getServer(), state);
                context.getSource().sendFeedback(() -> Text.literal("§aThe End has been unlocked!"), true);
                return 1;
            }));
    }
}
