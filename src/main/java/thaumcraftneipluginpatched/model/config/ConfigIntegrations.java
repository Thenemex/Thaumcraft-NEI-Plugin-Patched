package thaumcraftneipluginpatched.model.config;

import cpw.mods.fml.common.Loader;
import thaumcraftneipluginpatched.api.util.exceptions.ModIsMissing;
import thaumcraftneipluginpatched.model.integrations.TC4PatchedCompat;

public class ConfigIntegrations {

    protected static final String tx = "ThaumicExploration", tc4p = "TC4Patched";

    public static void init() {
        if (modIsLoaded(tx) && !modIsLoaded(tc4p)) throw new ModIsMissing("Thaumcraft 4 : Patched");
        else if (modIsLoaded(tx) && modIsLoaded(tc4p)) new TC4PatchedCompat(tc4p);
    }

    public static boolean modIsLoaded(String mod) {
        return Loader.isModLoaded(mod);
    }
}
