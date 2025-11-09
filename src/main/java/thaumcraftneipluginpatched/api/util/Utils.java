package thaumcraftneipluginpatched.api.util;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.ShapelessRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.ArcaneShapedRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.ArcaneShapelessRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.InfusionRecipeHandler;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraftneipluginpatched.api.util.exceptions.InnerClassNotFound;
import thaumcraftneipluginpatched.api.util.exceptions.ParameterIsNotTheRightClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.NoSuchElementException;

import static thaumcraftneipluginpatched.TCNEIPluginPatched.*;

@SuppressWarnings("unchecked")
public class Utils {

    protected Utils() {}

    protected static final String packageID = "com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.";

    public static boolean isRecipeValid(ShapedArcaneRecipe r) {
        if (r == null) return false;
        for (Object o : r.getInput())
            if (o != null) return true;
        return false;
    }
    public static boolean isRecipeValid(ShapelessArcaneRecipe r) {
        if (r == null) return false;
        for (Object o : r.getInput())
            if (o != null) return true;
        return false;
    }
    public static boolean isRecipeValid(InfusionRecipe r) {
        if (r == null) return false;
        return r.getRecipeInput() != null && r.getRecipeOutput() != null
                && r.getComponents() != null && r.getComponents().length != 0;
    }

    public static String getUsername() {
        return Minecraft.getMinecraft().thePlayer.getDisplayName();
    }

    public static boolean compareItemMeta(ItemStack i, ItemStack i2, boolean checkMeta32767) {
        if (!checkMeta32767)
            return i.getItem().equals(i2.getItem()) && i.getItemDamage() == i2.getItemDamage();
        else
            return i.getItem().equals(i2.getItem()) && (i.getItemDamage() == 32767 || i2.getItemDamage() == 32767 || i.getItemDamage() == i2.getItemDamage());
    }
    public static boolean isItemStackIn(ItemStack input, boolean checkMeta32767, ItemStack ... array) {
        for (ItemStack element : array)
            if (compareItemMeta(input, element, checkMeta32767))
                return true;
        return false;
    }

    protected static Class<?> getArcaneShapedCachedRecipeClass() {
        return getInnerClassCachedRecipe("ArcaneShapedRecipeHandler$ArcaneShapedCachedRecipe");
    }
    public static ShapedRecipeHandler.CachedShapedRecipe getArcaneShapedCachedRecipeInstance(ArcaneShapedRecipeHandler handler, ShapedArcaneRecipe recipe) {
        try {
            Constructor<?> constructor = getArcaneShapedCachedRecipeClass().getConstructors()[0];
            constructor.setAccessible(true);
            return (ShapedRecipeHandler.CachedShapedRecipe) constructor.newInstance(handler, recipe);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new NoSuchElementException();
        }
    }

    protected static Class<?> getArcaneWandChachedRecipeClass() {
        return getInnerClassCachedRecipe("ArcaneShapedRecipeHandler$ArcaneWandChachedRecipe");
    }
    public static ShapedRecipeHandler.CachedShapedRecipe getArcaneWandChachedRecipeInstance(ArcaneShapedRecipeHandler handler, WandRod rod, WandCap cap, ItemStack result, boolean isScepter) {
        try {
            Constructor<?> constructor = getArcaneWandChachedRecipeClass().getConstructors()[1];
            logger.info(constructor.toGenericString());
            constructor.setAccessible(true);
            return (ShapedRecipeHandler.CachedShapedRecipe) constructor.newInstance(handler, rod, cap, result, isScepter);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new NoSuchElementException();
        }
    }

    protected static Class<?> getArcaneShapelessCachedRecipeClass() {
        return getInnerClassCachedRecipe("ArcaneShapelessRecipeHandler$ArcaneShapelessCachedRecipe");
    }
    public static ShapelessRecipeHandler.CachedShapelessRecipe getArcaneShapelessCachedRecipeInstance(ArcaneShapelessRecipeHandler handler, ShapelessArcaneRecipe recipe) {
        try {
            Constructor<?> constructor = getArcaneShapelessCachedRecipeClass().getConstructors()[0];
            constructor.setAccessible(true);
            return (ShapelessRecipeHandler.CachedShapelessRecipe) constructor.newInstance(handler, recipe);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new NoSuchElementException();
        }
    }

    protected static Class<?> getInfusionCachedRecipeClass() {
        return getInnerClassCachedRecipe("InfusionRecipeHandler$InfusionCachedRecipe");
    }
    public static TemplateRecipeHandler.CachedRecipe getInfusionCachedRecipeInstance(InfusionRecipeHandler handler, InfusionRecipe recipe) {
        try {
            Constructor<?> constructor = getInfusionCachedRecipeClass().getConstructors()[0];
            constructor.setAccessible(true);
            return (TemplateRecipeHandler.CachedRecipe) constructor.newInstance(handler, recipe);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new NoSuchElementException();
        }
    }
    public static void runInfusionCachedRecipe_compileVisuals(TemplateRecipeHandler.CachedRecipe r) {
        if (r == null) throw new ParameterIsNullOrEmpty();
        if (r.getClass() != getInfusionCachedRecipeClass())
            throw new ParameterIsNotTheRightClass(getInfusionCachedRecipeClass(), r.getClass());
        try {
            Method method = getInfusionCachedRecipeClass().getDeclaredMethod("computeVisuals");
            method.setAccessible(true);
            method.invoke(r);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new NoSuchElementException("computeVisuals");
        }
    }
    public static AspectList runInfusionCachedRecipe_getAspectList(TemplateRecipeHandler.CachedRecipe r) {
        if (r == null) throw new ParameterIsNullOrEmpty();
        if (r.getClass() != getInfusionCachedRecipeClass())
            throw new ParameterIsNotTheRightClass(getInfusionCachedRecipeClass(), r.getClass());
        try {
            Method method = getInfusionCachedRecipeClass().getDeclaredMethod("getAspectList");
            method.setAccessible(true);
            return (AspectList) method.invoke(r);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new NoSuchElementException("getAspectList");
        }
    }
    public static List<PositionedStack> getInfusionCachedRecipe_ingredients(TemplateRecipeHandler.CachedRecipe r) {
        if (r == null) throw new ParameterIsNullOrEmpty();
        if (r.getClass() != getInfusionCachedRecipeClass())
            throw new ParameterIsNotTheRightClass(getInfusionCachedRecipeClass(), r.getClass());
        try {
            Field field = getInfusionCachedRecipeClass().getDeclaredField("ingredients");
            field.setAccessible(true);
            return (List<PositionedStack>) field.get(r);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new NoSuchElementException("ingredients");
        }
    }

    protected static Class<?> getInnerClassCachedRecipe(String clazzID) {
        if (clazzID == null) throw new ParameterIsNullOrEmpty();
        try {
            return Class.forName("com.djgiannuzz.thaumcraftneiplugin.nei.recipehandler.".concat(clazzID));
        } catch (ClassNotFoundException | LinkageError e) {
            throw new InnerClassNotFound(clazzID, packageID);
        }
    }
}
