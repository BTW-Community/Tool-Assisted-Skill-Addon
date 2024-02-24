package net.fabricmc.TAS.mixin;

import net.minecraft.src.FontRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiIngame;

@Mixin(GuiIngame.class)
public class GuiInGameMixin {
    @Final
    @Shadow
    private Minecraft mc;

    @Inject(method = "renderBossHealth", at = @At("TAIL"))
    private void drawTPS(CallbackInfo ci) {
        if (!mc.thePlayer.isDead) {
            FontRenderer fontRenderer = this.mc.fontRenderer;
            EntityPlayerAccessors playerAccessors = (EntityPlayerAccessors) mc.thePlayer;
            String textToShow = String.format("TPS: %.2f", playerAccessors.getTimerSpeedModifier() * 20.0F);
            int stringWidth = fontRenderer.getStringWidth(textToShow);
            fontRenderer.drawStringWithShadow(textToShow, 3, 3, 0XFFFFFF);
            // render the hotbar correctly
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.renderEngine.bindTexture("/gui/icons.png");
        }
    }
}