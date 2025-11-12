package thaumcraftneipluginpatched.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.config.AConfig;

import static thaumcraftneipluginpatched.TCNEIPluginPatched.modName;

public class Config extends AConfig {

    public static boolean debugOn, debugArcaneShapeless, debugInfusion;
    protected static final String version = "1.1";

    public Config(FMLPreInitializationEvent event) {
        super(event, modName, modName, version);
    }

    @Override
    protected void loadConfig() {
        String debug = "Debug-Tools";
        config.addCustomCategoryComment(debug, "WARNING ! Turn on this at your risks, will likely crash the game when looking for usages if bugged recipes are present");
        debugOn = newEntry(debug, "Debug", false);
        debugArcaneShapeless = newEntry(debug, "Debug-ArcaneShapeless", false);
        debugInfusion = newEntry(debug, "Debug-Infusion", false);
    }
}
