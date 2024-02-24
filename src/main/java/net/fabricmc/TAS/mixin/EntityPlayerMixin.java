package net.fabricmc.TAS.mixin;

import btw.community.TAS.TASAddon;
import net.minecraft.src.EntityPlayer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin {
	@Shadow public abstract void resetTimerSpeedModifier();

	@Shadow public void setTimerSpeedModifier(float timerSpeedModifier) {
		this.timerSpeedModifier = timerSpeedModifier;
	}

	@Shadow public float timerSpeedModifier;

	private boolean resetKeyPressed = false;
	private boolean increaseKeyPressed = false;
	private boolean decreaseKeyPressed = false;

	@Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
	private void onKeyPressed(CallbackInfo ci) {
		EntityPlayer thisObject = (EntityPlayer)(Object)this;
		if (!thisObject.worldObj.isRemote) {
			int keyCode = Keyboard.getEventKey();

			if (TASAddon.reset_tps_key.pressed && !resetKeyPressed) {
				this.resetTimerSpeedModifier();
				//System.out.println("Timer Speed Reset");
			}
			resetKeyPressed = TASAddon.reset_tps_key.pressed;

			if (TASAddon.increase_tps_key.pressed && !increaseKeyPressed) {
				this.setTimerSpeedModifier(this.timerSpeedModifier * 2.0F);
				//System.out.println("Timer Speed Increased to " + this.timerSpeedModifier);
			}
			increaseKeyPressed = TASAddon.increase_tps_key.pressed;

			if (TASAddon.decrease_tps_key.pressed && !decreaseKeyPressed) {
				this.setTimerSpeedModifier(this.timerSpeedModifier * 0.5F);
				//System.out.println("Timer Speed Decreased to " + this.timerSpeedModifier);
			}
			decreaseKeyPressed = TASAddon.decrease_tps_key.pressed;
		}
	}
}

