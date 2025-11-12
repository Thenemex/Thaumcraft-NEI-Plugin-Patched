package thaumcraftneipluginpatched;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.api.util.Logger;
import nemexlib.config.AConfig;
import thaumcraftneipluginpatched.config.Config;

import static thaumcraftneipluginpatched.TCNEIPluginPatched.dependencies;
import static thaumcraftneipluginpatched.TCNEIPluginPatched.modID;

@SuppressWarnings({"unused", "EmptyMethod"})
@Mod(modid = modID, useMetadata = true, version = "1.0.0.3", dependencies = dependencies)
public class TCNEIPluginPatched {

    public static final String modID = "thaumcraftneipluginpatched", modName = "TCNEIPluginPatched";
    public static final Logger logger = new Logger(modName);
    public static AConfig config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Config(event).init(); // Init config
    }

    public static final String dependencies = "required-after:CodeChickenCore@[1.0.7.48,);required-after:NotEnoughItems@[1.0.5.120,);required-after:thaumcraftneiplugin;required-after:NemexLib@[1.3.1.1,)";
}
