package thaumcraftneipluginpatched.api.util.exceptions;

import nemexlib.api.util.exceptions.TCRException;

public class InnerClassNotFound extends TCRException {

    public InnerClassNotFound(String clazzID, String packageID) { // ToDo Move to NemexLib
        super("Class \"".concat(clazzID).concat("\" not found in package : ").concat(packageID));
    }
}
