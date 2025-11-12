package thaumcraftneipluginpatched.nei.recipehandler.avoiding;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraftneipluginpatched.api.util.Utils;
import thaumcraftneipluginpatched.nei.recipehandler.patched.PatchedArcaneShapelessRecipeHandler;

import static thaumcraftneipluginpatched.api.util.Utils.getUsername;
import static thaumcraftneipluginpatched.api.util.Utils.isRecipeValid;

public class PatchedArcaneShapelessRecipeHandlerAvoiding extends PatchedArcaneShapelessRecipeHandler {

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for(Object o : ThaumcraftApi.getCraftingRecipes()) {
            if (o instanceof ShapelessArcaneRecipe) {
                ShapelessArcaneRecipe tcRecipe = (ShapelessArcaneRecipe)o;
                CachedShapelessRecipe recipe = Utils.getArcaneShapelessCachedRecipeAvoidingInstance(this, tcRecipe);
                if (recipe != null)
                    if (isRecipeValid(tcRecipe) && recipe.contains(recipe.ingredients, ingredient) && ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.getResearch())) {
                        recipe.setIngredientPermutation(recipe.ingredients, ingredient);
                        this.arecipes.add(recipe);
                        this.aspectsAmount.add(getAmounts(tcRecipe));
                    }
            }
        }

    }
}
