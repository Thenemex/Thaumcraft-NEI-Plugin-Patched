package thaumcraftneipluginpatched;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import nemexlib.api.util.Logger;
import thaumcraftneipluginpatched.model.config.ConfigIntegrations;

import static thaumcraftneipluginpatched.TCNEIPluginPatched.dependencies;
import static thaumcraftneipluginpatched.TCNEIPluginPatched.modID;

@SuppressWarnings({"unused", "EmptyMethod"})
@Mod(modid = modID, useMetadata = true, version = "1.0.0.1", dependencies = dependencies)
public class TCNEIPluginPatched {

    public static final String modID = "thaumcraftneipluginpatched", modName = "TCNEIPluginPatched";
    public static final Logger logger = new Logger(modName);

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent ignoredEvent) {
        ConfigIntegrations.init();
    }

    public static final String dependencies = "required-after:CodeChickenCore@[1.0.7.48,);required-after:NotEnoughItems@[1.0.5.120,);required-after:thaumcraftneiplugin;required-after:NemexLib@[1.3.1.1,);after:TC4Patched@[1.4,)";
}
