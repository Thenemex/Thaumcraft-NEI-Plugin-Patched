package thaumcraftneipluginpatched.nei.recipehandler;

import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.CrucibleRecipeHandler;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.crafting.CrucibleRecipe;

import static thaumcraftneipluginpatched.api.util.Utils.compareItemMeta;
import static thaumcraftneipluginpatched.api.util.Utils.getUsername;

@SuppressWarnings("unused")
public class PatchedCrucibleRecipeHandler extends CrucibleRecipeHandler {

    public PatchedCrucibleRecipeHandler() {
        super();
    }

   public void loadCraftingRecipes(String outputId, Object... results) {
      if (outputId.equals(this.getOverlayIdentifier())) {
          for (Object o : ThaumcraftApi.getCraftingRecipes()) {
              if (o instanceof CrucibleRecipe) {
                  CrucibleRecipe tcRecipe = (CrucibleRecipe) o;
                  CrucibleCachedRecipe recipe = new CrucibleCachedRecipe(tcRecipe);
                  if (ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.key) && recipe.isValid()) {
                      recipe.computeVisuals();
                      this.arecipes.add(recipe);
                      this.aspectsAmount.add(recipe.getAspectList());
                  }
              }
          }
      } else if (outputId.equals("item")) {
         this.loadCraftingRecipes((ItemStack)results[0]);
      }

   }

   public void loadCraftingRecipes(ItemStack result) {
       for (Object o : ThaumcraftApi.getCraftingRecipes()) {
           if (o instanceof CrucibleRecipe) {
               CrucibleRecipe tcRecipe = (CrucibleRecipe) o;
               boolean condition = compareItemMeta(result, tcRecipe.getRecipeOutput(), false);
               if (condition) {
                   CrucibleCachedRecipe recipe = new CrucibleCachedRecipe(tcRecipe);
                   if (ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.key) && recipe.isValid()) {
                       recipe.computeVisuals();
                       this.arecipes.add(recipe);
                       this.aspectsAmount.add(recipe.getAspectList());
                   }
               }
           }
       }

   }

   public void loadUsageRecipes(ItemStack ingredient) {
       for (Object o : ThaumcraftApi.getCraftingRecipes()) {
           if (o instanceof CrucibleRecipe) {
               CrucibleRecipe tcRecipe = (CrucibleRecipe) o;
               CrucibleCachedRecipe recipe = new CrucibleCachedRecipe(tcRecipe);
               boolean condition = compareItemMeta(ingredient, tcRecipe.getRecipeOutput(), false);
               if (recipe.ingredient != null && recipe.ingredient.item != null && condition && ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.key) && recipe.isValid()) {
                   recipe.computeVisuals();
                   recipe.setIngredientPermutation(recipe.ingredient, ingredient);
                   this.arecipes.add(recipe);
                   this.aspectsAmount.add(recipe.getAspectList());
               }
           }
       }

   }
}
