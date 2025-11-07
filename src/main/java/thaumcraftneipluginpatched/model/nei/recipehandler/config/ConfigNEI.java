package thaumcraftneipluginpatched.model.nei.recipehandler.config;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.ArcaneShapedRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.ArcaneShapelessRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.CrucibleRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.InfusionRecipeHandler;
import net.minecraft.client.Minecraft;
import thaumcraftneipluginpatched.model.nei.recipehandler.PatchedArcaneShapedRecipeHandler;

public class ConfigNEI implements IConfigureNEI {

    @Override
    public void loadConfig() {
        removeHandlersFromPlugin();
        registerPatchedHandlers();
    }

    protected void registerPatchedHandlers() {
        PatchedArcaneShapedRecipeHandler arcaneShaped = new PatchedArcaneShapedRecipeHandler();
        API.registerRecipeHandler(arcaneShaped);
        API.registerUsageHandler(arcaneShaped);
    }

    protected void removeHandlersFromPlugin() {
        GuiCraftingRecipe.craftinghandlers.removeIf(hd -> hd instanceof ArcaneShapedRecipeHandler
                || hd instanceof ArcaneShapelessRecipeHandler
                || hd instanceof CrucibleRecipeHandler
                || hd instanceof InfusionRecipeHandler);
        GuiUsageRecipe.usagehandlers.removeIf(ud -> ud instanceof ArcaneShapedRecipeHandler
                || ud instanceof ArcaneShapelessRecipeHandler
                || ud instanceof CrucibleRecipeHandler
                || ud instanceof InfusionRecipeHandler);
    }

    public static String getUsername() {
        return Minecraft.getMinecraft().thePlayer.getDisplayName();
    }

    public String getName() {
        return "Thaumcraft NEI Plugin";
    }

    public String getVersion() {
        return "@VERSION@";
    }
}
