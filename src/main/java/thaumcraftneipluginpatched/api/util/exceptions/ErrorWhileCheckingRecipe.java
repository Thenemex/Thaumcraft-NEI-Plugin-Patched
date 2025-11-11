package thaumcraftneipluginpatched.api.util.exceptions;

import nemexlib.api.util.exceptions.TCRException;
import net.minecraft.item.ItemStack;

public class ErrorWhileCheckingRecipe extends TCRException {

    public ErrorWhileCheckingRecipe(String recipeType, ItemStack input, Class<?> exception, String message) {
        super(recipeType + " recipe with input " + input + " cannot be checked properly.\nThis is probably caused by an element of the recipe being null or not instanciated properly.\nException type is : " + exception + " / " + message);
    }

    public ErrorWhileCheckingRecipe(String recipeType, Class<?> exception, String message, ItemStack output) {
        super(recipeType + " recipe with output " + output + " cannot be checked properly.\nThis is probably caused by an element of the recipe being null or not instanciated properly.\nException type is : " + exception + " / " + message);
    }
}
