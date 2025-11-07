package thaumcraftneipluginpatched;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import thaumcraftneipluginpatched.model.nei.recipehandler.config.ConfigNEI;

import static thaumcraftneipluginpatched.ThaumcraftNEIPluginPatched.dependencies;
import static thaumcraftneipluginpatched.ThaumcraftNEIPluginPatched.modID;

@SuppressWarnings({"unused", "EmptyMethod"})
@Mod(modid = modID, useMetadata = true, version = "1.0", dependencies = dependencies)
public class ThaumcraftNEIPluginPatched {

    public static final String modID = "thaumcraftneipluginpatched", modName = "ThaumcraftNEIPluginPatched";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ignoredEvent) {}

    @Mod.EventHandler
    public void init(FMLInitializationEvent ignoredEvent) {}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent ignoredEvent) {
        new ConfigNEI();
    }

    public static final String dependencies = "required-after:thaumcraftneiplugin;required-after:NotEnoughItems@[1.0.5.120,)";
}
