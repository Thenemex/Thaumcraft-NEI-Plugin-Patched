package thaumcraftneipluginpatched.nei.recipehandler;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraftneipluginpatched.api.util.Utils;

import static thaumcraftneipluginpatched.api.util.Utils.*;

public class PatchedInfusionRecipeHandlerAvoiding extends PatchedInfusionRecipeHandler {

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for (Object o : ThaumcraftApi.getCraftingRecipes())
            if (o instanceof InfusionRecipe) {
                InfusionRecipe tcRecipe = (InfusionRecipe) o;
                if (isRecipeValid(tcRecipe)) {
                    CachedRecipe recipe = Utils.getInfusionCachedRecipeAvoidingInstance(this, tcRecipe);
                    if (recipe != null)
                        if (compareItemMeta(ingredient, tcRecipe.getRecipeInput(), false) || isItemStackIn(ingredient, true, tcRecipe.getComponents()))
                            if (ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.getResearch())) {
                                runInfusionCachedRecipe_compileVisuals(recipe);
                                recipe.setIngredientPermutation(getInfusionCachedRecipe_ingredients(recipe), ingredient);
                                this.arecipes.add(recipe);
                                this.aspectsAmount.add(runInfusionCachedRecipe_getAspectList(recipe));
                            }
                }
            }
    }
}
