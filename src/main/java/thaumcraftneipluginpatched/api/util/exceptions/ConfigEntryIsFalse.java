package thaumcraftneipluginpatched.api.util.exceptions;

import nemexlib.api.util.exceptions.TCRException;

public class ConfigEntryIsFalse extends TCRException {

    public ConfigEntryIsFalse(String mod, String entry) {
        super("\nThe config entry called " + entry + " from mod " + mod + "must be set to true, if Thaumic Exploration and Thaumcraft 4 : Patched are both loaded, to avoid a crash.");
    }
}
