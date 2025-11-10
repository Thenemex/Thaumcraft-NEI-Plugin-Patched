package thaumcraftneipluginpatched.api.util.exceptions;

import nemexlib.api.util.exceptions.TCRException;

public class ModIsMissing extends TCRException {
    public ModIsMissing(String mod) {
        super("The mod " + mod + " is missing, because Thaumic Exploration is loaded.\nThat mod needs a bug-patch from " + mod + " or else it will make loading usages with NEI crash the game.");
    }
}
