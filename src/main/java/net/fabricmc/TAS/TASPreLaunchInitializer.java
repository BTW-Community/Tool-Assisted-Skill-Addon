package net.fabricmc.TAS;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import btw.community.TAS.TASAddon;

public class TASPreLaunchInitializer implements PreLaunchEntrypoint {
    /**
     * Runs the PreLaunch entrypoint to register BTW-Addon.
     * Don't initialize anything else here, use
     * the method Initialize() in the Addon.
     */
    @Override
    public void onPreLaunch() {
        TASAddon.getInstance();
    }
}
