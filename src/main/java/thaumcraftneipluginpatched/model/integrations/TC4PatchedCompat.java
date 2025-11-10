package thaumcraftneipluginpatched.model.integrations;

import nemexlib.api.integrations.ACompat;
import thaumcraft4patched.config.Config;
import thaumcraftneipluginpatched.api.util.exceptions.ConfigEntryIsFalse;

public class TC4PatchedCompat extends ACompat {

    public TC4PatchedCompat(String tc4p) {
        super(tc4p);
    }

    @Override
    public void loadIntegration() {
        if (!Config.removeNecroInfusionRecipe) {
            throw new ConfigEntryIsFalse("Thaumcraft 4 : Patched", "RemoveNecroInfusionRecipe");
        }
    }
}
