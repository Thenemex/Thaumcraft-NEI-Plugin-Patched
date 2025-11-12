package thaumcraftneipluginpatched.nei.recipehandler.patched;

import com.djgiannuzz.thaumcraftneiplugin.nei.NEIHelper;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.ArcaneShapedRecipeHandler;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftneipluginpatched.api.util.Utils;

import static thaumcraft.api.ThaumcraftApiHelper.isResearchComplete;
import static thaumcraftneipluginpatched.api.util.Utils.*;

public class PatchedArcaneShapedRecipeHandler extends ArcaneShapedRecipeHandler {

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(this.getOverlayIdentifier())) {
            for (Object o : ThaumcraftApi.getCraftingRecipes())
                if (o instanceof ShapedArcaneRecipe) {
                    ShapedArcaneRecipe tcRecipe = (ShapedArcaneRecipe) o;
                    CachedShapedRecipe recipe = Utils.getArcaneShapedCachedRecipeInstance(this, tcRecipe);
                    if (isRecipeValid(tcRecipe) && isResearchComplete(getUsername(), tcRecipe.getResearch())) {
                        recipe.computeVisuals();
                        this.arecipes.add(recipe);
                        this.aspectsAmount.add(getAmounts(tcRecipe));
                    }
                }
        } else if (outputId.equals("item") && results.length == 1)
            this.loadCraftingRecipes((ItemStack) results[0]);
        else
            super.loadCraftingRecipes(outputId, results);
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        if (result == null) return;
        if (result.getItem() instanceof ItemWandCasting) {
            ItemWandCasting wand = (ItemWandCasting) result.getItem();
            WandRod rod = wand.getRod(result);
            WandCap cap = wand.getCap(result);
            if (!wand.isSceptre(result) || wand.isSceptre(result) && isResearchComplete(getUsername(), "SCEPTRE"))
                if (isResearchComplete(getUsername(), cap.getResearch()) && isResearchComplete(getUsername(), rod.getResearch())) {
                    CachedShapedRecipe recipe = Utils.getArcaneWandChachedRecipeInstance(this, rod, cap, result, wand.isSceptre(result));
                    recipe.computeVisuals();
                    this.arecipes.add(recipe);
                    this.aspectsAmount.add(NEIHelper.getWandAspectsWandCost(result));
                }
        } else
            for (Object o : ThaumcraftApi.getCraftingRecipes())
                if (o instanceof ShapedArcaneRecipe) {
                    ShapedArcaneRecipe tcRecipe = (ShapedArcaneRecipe) o;
                    boolean condition = compareItemMeta(result, tcRecipe.getRecipeOutput(), false);
                    if (condition) {
                        CachedShapedRecipe recipe = Utils.getArcaneShapedCachedRecipeInstance(this, tcRecipe);
                        if (isRecipeValid(tcRecipe) && isResearchComplete(getUsername(), tcRecipe.getResearch())) {
                            recipe.computeVisuals();
                            this.arecipes.add(recipe);
                            this.aspectsAmount.add(getAmounts(tcRecipe));
                        }
                    }
                }
   }
}
