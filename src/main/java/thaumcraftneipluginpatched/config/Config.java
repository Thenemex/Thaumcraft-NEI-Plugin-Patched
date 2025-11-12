package thaumcraftneipluginpatched.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.config.AConfig;

import static thaumcraftneipluginpatched.TCNEIPluginPatched.modName;

public class Config extends AConfig {

    public static boolean debugOn, debugArcaneShaped, debugArcaneShapeless, debugCrucible, debugInfusion;
    protected static final String version = "1.0";

    public Config(FMLPreInitializationEvent event) {
        super(event, modName, modName, version);
    }

    @Override
    protected void loadConfig() {
        String debug = "Debug-Tools";
        config.addCustomCategoryComment(debug, "WARNING ! Turn on this at your risks, will likely crash the game when looking for usages if bugged recipes are present");
        debugOn = newEntry(debug, "Debug", false);
        debugArcaneShaped = newEntry(debug, "Debug-ArcaneShaped", false);
        debugArcaneShapeless = newEntry(debug, "Debug-ArcaneShapeless", false);
        debugCrucible = newEntry(debug, "Debug-Crucible", false);
        debugInfusion = newEntry(debug, "Debug-Infusion", false);
    }
}
