package thaumcraftneipluginpatched;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.api.util.Logger;

import static thaumcraftneipluginpatched.TCNEIPluginPatched.dependencies;
import static thaumcraftneipluginpatched.TCNEIPluginPatched.modID;

@SuppressWarnings({"unused", "EmptyMethod"})
@Mod(modid = modID, useMetadata = true, version = "1.0", dependencies = dependencies)
public class TCNEIPluginPatched {

    public static final String modID = "thaumcraftneipluginpatched", modName = "TCNEIPluginPatched";
    public static final Logger logger = new Logger(modName);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ignoredEvent) {}

    @Mod.EventHandler
    public void init(FMLInitializationEvent ignoredEvent) {}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent ignoredEvent) {}

    public static final String dependencies = "required-after:thaumcraftneiplugin;required-after:NemexLib@[1.3.1.0.1,)";
}
