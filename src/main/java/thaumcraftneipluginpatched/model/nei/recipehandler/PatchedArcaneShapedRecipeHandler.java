package thaumcraftneipluginpatched.model.nei.recipehandler;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.ShapedRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.items.ItemAspect;
import com.djgiannuzz.thaumcraftneiplugin.nei.NEIHelper;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.ArcaneShapedRecipeHandler;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;

import java.util.Collection;
import java.util.List;

import static thaumcraftneipluginpatched.model.nei.recipehandler.config.ConfigNEI.getUsername;

public class PatchedArcaneShapedRecipeHandler extends ArcaneShapedRecipeHandler {

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(this.getOverlayIdentifier())) {
            for (Object o : ThaumcraftApi.getCraftingRecipes())
                if (o instanceof ShapedArcaneRecipe) {
                    ShapedArcaneRecipe tcRecipe = (ShapedArcaneRecipe) o;
                    ArcaneShapedCachedRecipe recipe = new ArcaneShapedCachedRecipe(tcRecipe);
                    if (recipe.isValid() && ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.getResearch())) {
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
            if (!wand.isSceptre(result) || ThaumcraftApiHelper.isResearchComplete(getUsername(), "SCEPTRE"))
                if (ThaumcraftApiHelper.isResearchComplete(getUsername(), cap.getResearch()) && ThaumcraftApiHelper.isResearchComplete(getUsername(), rod.getResearch())) {
                    ArcaneWandChachedRecipe recipe = new ArcaneWandChachedRecipe(rod, cap, result, wand.isSceptre(result));
                    recipe.computeVisuals();
                    this.arecipes.add(recipe);
                    this.aspectsAmount.add(NEIHelper.getWandAspectsWandCost(result));
                }
        } else
            for (Object o : ThaumcraftApi.getCraftingRecipes())
                if (o instanceof ShapedArcaneRecipe) {
                    ShapedArcaneRecipe tcRecipe = (ShapedArcaneRecipe) o;
                    boolean condition = result.getItem().equals(tcRecipe.getRecipeOutput().getItem())
                            && result.getItemDamage() == tcRecipe.getRecipeOutput().getItemDamage();
                    if (condition) {
                        ArcaneShapedCachedRecipe recipe = new ArcaneShapedCachedRecipe(tcRecipe);
                        if (recipe.isValid() && ThaumcraftApiHelper.isResearchComplete(getUsername(), tcRecipe.getResearch())) {
                            recipe.computeVisuals();
                            this.arecipes.add(recipe);
                            this.aspectsAmount.add(getAmounts(tcRecipe));
                        }
                    }
                }
   }

    protected class ArcaneWandChachedRecipe extends ShapedRecipeHandler.CachedShapedRecipe {
        protected AspectList aspects;
        protected Object[] overlay;

        public ArcaneWandChachedRecipe(WandRod rod, WandCap cap, ItemStack result, boolean isScepter) {
            super(3, 3, isScepter ? NEIHelper.buildScepterInput(rod, cap) : NEIHelper.buildWandInput(rod, cap), result);
            this.overlay = isScepter ? NEIHelper.buildScepterInput(rod, cap) : NEIHelper.buildWandInput(rod, cap);
            this.result = new PositionedStack(result, 74, 2);
            this.aspects = NEIHelper.getPrimalAspectListFromAmounts(NEIHelper.getWandAspectsWandCost(result));
            NEIHelper.addAspectsToIngredients(this.aspects, this.ingredients, 0);
        }

        public void setIngredients(int width, int height, Object[] items) {
            if (items != null && items.length > 0) {
                int[][] positions = new int[][]{{48, 32}, {75, 33}, {103, 33}, {49, 60}, {76, 60}, {103, 60}, {49, 87}, {76, 87}, {103, 87}},
                        positions2 = new int[][]{{48, 32}, {75, 33}, {49, 60}, {76, 60}};
                int shiftX = 0, shiftY = 0;

                for(int x = 0; x < width; ++x)
                    for(int y = 0; y < height; ++y)
                        if (items[y * width + x] != null && (items[y * width + x] instanceof ItemStack || items[y * width + x] instanceof ItemStack[] || items[y * width + x] instanceof String || items[y * width + x] instanceof List) && (!(items[y * width + x] instanceof List) || !((List<?>)items[y * width + x]).isEmpty())) {
                            if (width == 2 && height == 2)
                                positions = positions2;

                            PositionedStack stack = new PositionedStack(items[y * width + x], positions[y * width + x][0] + shiftX, positions[y * width + x][1] + shiftY, false);
                            stack.setMaxSize(1);
                            this.ingredients.add(stack);
                        }
            }
        }

        public boolean contains(Collection<PositionedStack> ingredients, ItemStack ingredient) {
            return !(ingredient.getItem() instanceof ItemAspect) && super.contains(ingredients, ingredient);
        }
    }

    protected class ArcaneShapedCachedRecipe extends ShapedRecipeHandler.CachedShapedRecipe {
        protected AspectList aspects;
        protected Object[] overlay;
        protected int width, height;

        public ArcaneShapedCachedRecipe(ShapedArcaneRecipe recipe) {
            super(recipe.width, recipe.height, recipe.getInput(), recipe.getRecipeOutput());
            this.result = new PositionedStack(recipe.getRecipeOutput(), 74, 2);
            this.aspects = recipe.getAspects();
            this.overlay = recipe.getInput();
            this.width = recipe.width;
            this.height = recipe.height;
            NEIHelper.addAspectsToIngredients(this.aspects, this.ingredients, 0);
        }

        public boolean isValid() {
            return !this.ingredients.isEmpty() && this.result != null;
        }

        @Override
        public void setIngredients(int width, int height, Object[] items) {
            if (items != null && items.length > 0) {
                int[][][] positions2 = new int[width][height][2];
                int shiftX = 0, shiftY = 0;
                for(int x = 0; x < width && x < 3; ++x)
                    for(int y = 0; y < height && y < 3; ++y) {
                        positions2[x][y][0] = ArcaneShapedRecipeHandler.positions[y][x][0];
                        positions2[x][y][1] = ArcaneShapedRecipeHandler.positions[y][x][1];
                    }
                for(int x = 0; x < width && x < 3; ++x)
                    for(int y = 0; y < height && y < 3; ++y)
                        if (items[y * width + x] != null && (items[y * width + x] instanceof ItemStack || items[y * width + x] instanceof ItemStack[] || items[y * width + x] instanceof String || items[y * width + x] instanceof List) && (!(items[y * width + x] instanceof List) || !((List<?>)items[y * width + x]).isEmpty())) {
                            PositionedStack stack = new PositionedStack(items[y * width + x], positions2[x][y][0] + shiftX, positions2[x][y][1] + shiftY, false);
                            stack.setMaxSize(1);
                            this.ingredients.add(stack);
                        }
            }
        }

        @Override
        public boolean contains(Collection<PositionedStack> ingredients, ItemStack ingredient) {
            return !(ingredient.getItem() instanceof ItemAspect) && super.contains(ingredients, ingredient);
        }
    }
}
