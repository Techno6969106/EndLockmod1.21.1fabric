package io.github.endlock.mixin;

import io.github.endlock.EndLockState;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndPortalBlock.class)
public class EndPortalBlockMixin {
    
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void endlock$onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (world.isClient) return;
        if (!(world instanceof ServerWorld serverWorld)) return;
        
        if (world.getRegistryKey().equals(World.END)) return;
        
        EndLockState lockState = EndLockState.load(serverWorld.getServer());
        
        if (lockState.isLocked()) {
            if (entity.isPlayer()) {
                entity.sendMessage(Text.literal("§cThe End is currently locked!"));
            }
            ci.cancel();
        }
    }
}
