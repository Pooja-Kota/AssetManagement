package im.assetmanagement.data.Constants;

import java.util.HashMap;

/**
 * @author pkota
 */
public class ErrorCodesConstants {

    static HashMap<Integer, String> errorCodesMap = new HashMap<>();

    public static final String USERNAME_EMPTY = "Please enter UserName";
    public static final String PASS_WORD_EMPTY = "Please enter Password";


    private static void initialiseErrorMap() {

        errorCodesMap.put(900, "Please enter a valid token");
        errorCodesMap.put(901, "Please enter token");
        errorCodesMap.put(902, "Please enter a valid Password");
        errorCodesMap.put(903, "Please enter a valid Username");
        errorCodesMap.put(904, "Please enter Username");
        errorCodesMap.put(905, "Please enter Password");
        errorCodesMap.put(906, "Please enter correct current password");

        errorCodesMap.put(1001, "Please enter UserName");
        errorCodesMap.put(1002, "Please enter First Name");
        errorCodesMap.put(1003, "Please enter Last Name");
        errorCodesMap.put(1004, "Please enter Email ID");
        errorCodesMap.put(1005, "Please enter Password");

    }


    public static String getErrorMessage(Integer key) {
        initialiseErrorMap();
        return errorCodesMap.get(key);
    }
}



