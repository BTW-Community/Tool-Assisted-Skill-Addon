package net.fabricmc.TAS.mixin;

import net.minecraft.src.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityPlayer.class)
public interface EntityPlayerAccessors {
	@Accessor
	public abstract float getTimerSpeedModifier();

	@Accessor
	public abstract void setTimerSpeedModifier(float value);
}
