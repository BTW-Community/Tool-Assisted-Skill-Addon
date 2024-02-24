package btw.community.TAS;

import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GameSettings;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.StatCollector;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;

public class TASAddon extends BTWAddon {
    private static TASAddon instance;
    public static KeyBinding reset_tps_key;
    public static KeyBinding increase_tps_key;
    public static KeyBinding decrease_tps_key;

    private TASAddon() {
        super("Tool Assisted Skill", "1.0.0", "");
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }

    public static TASAddon getInstance() {
        if (instance == null)
            instance = new TASAddon();
        return instance;
    }

    public void initKeybind(){
        reset_tps_key = new KeyBinding(StatCollector.translateToLocal("key.TAS.reset"), Keyboard.KEY_R);
        increase_tps_key = new KeyBinding(StatCollector.translateToLocal("key.TAS.increase"), Keyboard.KEY_PERIOD);
        decrease_tps_key = new KeyBinding(StatCollector.translateToLocal("key.TAS.decrease"), Keyboard.KEY_COMMA);

        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        KeyBinding[] keyBindings = settings.keyBindings;
        keyBindings = Arrays.copyOf(keyBindings, keyBindings.length + 3);
        keyBindings[keyBindings.length - 3] = reset_tps_key;
        keyBindings[keyBindings.length - 2] = increase_tps_key;
        keyBindings[keyBindings.length - 1] = decrease_tps_key;
        settings.keyBindings = keyBindings;
    }
}
