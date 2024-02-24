package net.fabricmc.TAS.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MinecraftServer.class, remap = false)
public abstract class MinecraftServerMixin {

    // deletes the line "speedModifier = Math.max(speedModifier, 1.0F);"
    @Redirect(method = "sendTimerSpeedUpdate(F)V", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"))
    private float redirectMax(float a, float b) {
        return a;
    }

    // account for speedModifier values under 1
    @ModifyConstant(method = "run", constant = @Constant(floatValue = 1.0F))
    private float takeAChillPill(float value) { return Float.MIN_VALUE; }

}
