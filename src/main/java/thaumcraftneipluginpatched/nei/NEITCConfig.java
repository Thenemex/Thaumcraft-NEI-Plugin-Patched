package thaumcraftneipluginpatched.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.ArcaneShapedRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.ArcaneShapelessRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.CrucibleRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.InfusionRecipeHandler;
import thaumcraftneipluginpatched.config.Config;
import thaumcraftneipluginpatched.nei.recipehandler.avoiding.PatchedInfusionRecipeHandlerAvoiding;
import thaumcraftneipluginpatched.nei.recipehandler.patched.PatchedArcaneShapedRecipeHandler;
import thaumcraftneipluginpatched.nei.recipehandler.patched.PatchedArcaneShapelessRecipeHandler;
import thaumcraftneipluginpatched.nei.recipehandler.patched.PatchedCrucibleRecipeHandler;
import thaumcraftneipluginpatched.nei.recipehandler.patched.PatchedInfusionRecipeHandler;

import static thaumcraftneipluginpatched.TCNEIPluginPatched.logger;

@SuppressWarnings("unused")
public class NEITCConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        removeHandlersFromPlugin();
        registerPatchedHandlers();
    }

    protected void registerPatchedHandlers() {
        PatchedArcaneShapedRecipeHandler arcaneShaped = new PatchedArcaneShapedRecipeHandler();
        API.registerRecipeHandler(arcaneShaped);
        API.registerUsageHandler(arcaneShaped);
        PatchedArcaneShapelessRecipeHandler arcaneShapeless = new PatchedArcaneShapelessRecipeHandler();
        API.registerRecipeHandler(arcaneShapeless);
        API.registerUsageHandler(arcaneShapeless);
        PatchedCrucibleRecipeHandler crucible = new PatchedCrucibleRecipeHandler();
        API.registerRecipeHandler(crucible);
        API.registerUsageHandler(crucible);
        PatchedInfusionRecipeHandler infusion = (Config.debugOn) ? new PatchedInfusionRecipeHandler() : new PatchedInfusionRecipeHandlerAvoiding();
        API.registerRecipeHandler(infusion);
        API.registerUsageHandler(infusion);
        logger.info("Successfully loaded new patches");
    }

    protected void removeHandlersFromPlugin() {
        int cpt = 0;
        if (GuiCraftingRecipe.craftinghandlers.removeIf(hd -> hd instanceof ArcaneShapedRecipeHandler)) cpt++;
        if (GuiCraftingRecipe.craftinghandlers.removeIf(hd -> hd instanceof ArcaneShapelessRecipeHandler)) cpt++;
        if (GuiCraftingRecipe.craftinghandlers.removeIf(hd -> hd instanceof CrucibleRecipeHandler)) cpt++;
        if (GuiCraftingRecipe.craftinghandlers.removeIf(hd -> hd instanceof InfusionRecipeHandler)) cpt++;
        if (GuiUsageRecipe.usagehandlers.removeIf(ud -> ud instanceof ArcaneShapedRecipeHandler)) cpt++;
        if (GuiUsageRecipe.usagehandlers.removeIf(ud -> ud instanceof ArcaneShapelessRecipeHandler)) cpt++;
        if (GuiUsageRecipe.usagehandlers.removeIf(ud -> ud instanceof CrucibleRecipeHandler)) cpt++;
        if (GuiUsageRecipe.usagehandlers.removeIf(ud -> ud instanceof InfusionRecipeHandler)) cpt++;
        logger.info("Handlers removed from original mod : " + cpt + "/8");
    }

    @Override
    public String getName() {
        return "TC NEI Plugin Patched";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}
