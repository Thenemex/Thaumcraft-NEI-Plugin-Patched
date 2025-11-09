package thaumcraftneipluginpatched.nei.recipehandler;

import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.ArcaneShapelessRecipeHandler;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraftneipluginpatched.api.util.Utils;

import static thaumcraftneipluginpatched.api.util.Utils.*;


public class PatchedArcaneShapelessRecipeHandler extends ArcaneShapelessRecipeHandler {

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(this.getOverlayIdentifier())) { // Click on whole "Shapeless Arcane" section -> loads all recipes
            for (Object o : ThaumcraftApi.getCraftingRecipes())
                if (o instanceof ShapelessArcaneRecipe) {
                    ShapelessArcaneRecipe tcRecipe = (ShapelessArcaneRecipe) o;
                    CachedShapelessRecipe recipe = Utils.getArcaneShapelessCachedRecipeInstance(this, tcRecipe);
                    if (isRecipeValid(tcRecipe) && ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.getResearch())) {
                        this.arecipes.add(recipe);
                        this.aspectsAmount.add(getAmounts(tcRecipe));
                    }
                }
        } else if (outputId.equals("item") && results.length == 1) { // Click on item
            this.loadCraftingRecipes((ItemStack) results[0]);
        } else if (outputId.equals("item"))
            super.loadCraftingRecipes(outputId, results);
   }

    public void loadCraftingRecipes(ItemStack result) {
        for (Object o : ThaumcraftApi.getCraftingRecipes())
            if (o instanceof ShapelessArcaneRecipe) {
                ShapelessArcaneRecipe tcRecipe = (ShapelessArcaneRecipe) o;
                boolean condition = compareItemMeta(result, tcRecipe.getRecipeOutput(), false);
                if (condition) {
                    CachedShapelessRecipe recipe = Utils.getArcaneShapelessCachedRecipeInstance(this, tcRecipe);
                    if (isRecipeValid(tcRecipe) && ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.getResearch())) {
                        this.arecipes.add(recipe);
                        this.aspectsAmount.add(getAmounts(tcRecipe));
                    }
                }
            }
    }
}
