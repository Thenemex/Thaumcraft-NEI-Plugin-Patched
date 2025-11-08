package thaumcraftneipluginpatched.api.util;

import codechicken.nei.recipe.ShapedRecipeHandler;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraftneipluginpatched.api.util.exceptions.InnerClassNotFound;

import java.lang.reflect.InvocationTargetException;

public class Utils {

    protected static final String packageID = "com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.";

    public static boolean isRecipeValid(ShapedArcaneRecipe r) {
        boolean valid = false;
        for (Object o : r.getInput())
            if (o != null) return true;
        return valid;
    }

    public static String getUsername() {
        return Minecraft.getMinecraft().thePlayer.getDisplayName();
    }

    protected static Class<?> getArcaneShapedCachedRecipeClass() {
        return getInnerClassCachedRecipe("ArcaneShapedRecipeHandler$ArcaneShapedCachedRecipe");
    }
    public static ShapedRecipeHandler.CachedShapedRecipe getArcaneShapedCachedRecipeInstance(ShapedArcaneRecipe recipe) {
        try {
            return (ShapedRecipeHandler.CachedShapedRecipe) getArcaneShapedCachedRecipeClass()
                    .getConstructor(recipe.getClass())
                    .newInstance(recipe);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {
            return null;
        }
    }

    protected static Class<?> getArcaneWandChachedRecipeClass() {
        return getInnerClassCachedRecipe("ArcaneShapedRecipeHandler$ArcaneWandChachedRecipe");
    }
    public static ShapedRecipeHandler.CachedShapedRecipe getArcaneWandChachedRecipeInstance(WandRod rod, WandCap cap, ItemStack result, boolean isScepter) {
        try {
            return (ShapedRecipeHandler.CachedShapedRecipe) getArcaneWandChachedRecipeClass()
                    .getConstructor(rod.getClass(), cap.getClass(), result.getClass(), boolean.class)
                    .newInstance(rod, cap, result, isScepter);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {
            return null;
        }
    }

    protected static Class<?> getInnerClassCachedRecipe(String clazzID) {
        if (clazzID == null) throw new ParameterIsNullOrEmpty();
        try {
            return Class.forName("com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.".concat(clazzID));
        } catch (ClassNotFoundException ignored) {
            throw new InnerClassNotFound(clazzID, packageID);
        }
    }
}
