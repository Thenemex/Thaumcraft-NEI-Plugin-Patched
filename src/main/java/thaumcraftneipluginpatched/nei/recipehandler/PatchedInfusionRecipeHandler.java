package thaumcraftneipluginpatched.nei.recipehandler;

import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.InfusionRecipeHandler;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraftneipluginpatched.api.util.Utils;

import static thaumcraftneipluginpatched.api.util.Utils.*;

@SuppressWarnings({"unused"})
public class PatchedInfusionRecipeHandler extends InfusionRecipeHandler {

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(this.getOverlayIdentifier())) {
            for (Object o : ThaumcraftApi.getCraftingRecipes())
                if (o instanceof InfusionRecipe) {
                    InfusionRecipe tcRecipe = (InfusionRecipe) o;
                    if (isRecipeValid(tcRecipe)) {
                        CachedRecipe recipe = Utils.getInfusionCachedRecipeInstance(this, tcRecipe);
                        if (ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.getResearch())) {
                            Utils.runInfusionCachedRecipe_compileVisuals(recipe);
                            this.arecipes.add(recipe);
                            this.aspectsAmount.add(runInfusionCachedRecipe_getAspectList(recipe));
                        }
                    }
                }
        } else if (outputId.equals("item"))
            this.loadCraftingRecipes((ItemStack)results[0]);
    }

    public void loadCraftingRecipes(ItemStack result) {
        InfusionRecipe tcRecipe = ThaumcraftApi.getInfusionRecipe(result);
        if (isRecipeValid(tcRecipe) && ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.getResearch())) {
            CachedRecipe recipe = Utils.getInfusionCachedRecipeInstance(this, tcRecipe);
            Utils.runInfusionCachedRecipe_compileVisuals(recipe);
            recipe.setIngredientPermutation(getInfusionCachedRecipe_ingredients(recipe), result);
            this.arecipes.add(recipe);
            this.aspectsAmount.add(runInfusionCachedRecipe_getAspectList(recipe));
        }
    }
}
