package thaumcraftneipluginpatched.api.util.exceptions;

public class InnerClassNotFound extends ThaumcraftNEIPluginPatchedException {

    public InnerClassNotFound(String clazzID, String packageID) { // ToDo Move to NemexLib
        super("Class \"".concat(clazzID).concat("\" not found in package : ").concat(packageID));
    }
}
