package thaumcraftneipluginpatched.api.util.exceptions;

import nemexlib.api.util.exceptions.TCRException;

public class ParameterIsNotTheRightClass extends TCRException {

    public ParameterIsNotTheRightClass(Class<?> supposedClazz, Class<?> actualClazz) {
        super("Parameter should be from class \"" + supposedClazz + "\", but is from \"" + actualClazz + "\"");
    }
}
