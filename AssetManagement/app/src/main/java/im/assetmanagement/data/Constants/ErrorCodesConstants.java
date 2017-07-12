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

        errorCodesMap.put(3000, "Please enter a valid Number");
        errorCodesMap.put(3000, "Please enter a valid photo path");
        errorCodesMap.put(3002, "Invalid Session. Please login again");

        errorCodesMap.put(1001, "Please enter UserName");
        errorCodesMap.put(1002, "Please enter First Name");
        errorCodesMap.put(1003, "Please enter Last Name");
        errorCodesMap.put(1004, "Please enter Email ID");
        errorCodesMap.put(1005, "Please enter Password");
        errorCodesMap.put(1006, "Please enter Mobile Number");
        errorCodesMap.put(1007, "Please enter Date of Birth");
        errorCodesMap.put(1008, "Please enter a valid Email ID");
        errorCodesMap.put(1009, "Please enter a valid Date of Birth");
        errorCodesMap.put(1010, "Please enter a valid Mobile Number");
        errorCodesMap.put(1011, "Please enter a Registered UserName");
        errorCodesMap.put(1012, "User Name Already Exists");
        errorCodesMap.put(1013, "Email Already Exists");
        errorCodesMap.put(1014, "Please enter a Registered Email ID");
        errorCodesMap.put(1015, "Please enter a Registered UserName");
        errorCodesMap.put(1016, "User does not have Permission");
        errorCodesMap.put(1017, "Please enter a email between 5 and 45 characters");

        errorCodesMap.put(2001, "Please enter First Name");
        errorCodesMap.put(2002, "Please enter Last Name");
        errorCodesMap.put(2003, "Please enter Date of Birth");
        errorCodesMap.put(2004, "Please enter Gender");
        errorCodesMap.put(2005, "Please enter your Relationship");
        errorCodesMap.put(2006, "Please enter User Id");
        errorCodesMap.put(2007, "Please enter Communication ID");
        errorCodesMap.put(2008, "Please enter Communication Type");
        errorCodesMap.put(2009, "Please enter Communication Address");
        errorCodesMap.put(2010, "Patient Id is Empty");
        errorCodesMap.put(2011, "Patient doesn't exist");
        errorCodesMap.put(2012, "Please enter a valid gender");
        errorCodesMap.put(2013, "Please enter a valid contact Id");
        errorCodesMap.put(2014, "Error While removing patient");
        errorCodesMap.put(2015, "Please enter a license number between 0 and 13 characters");
        errorCodesMap.put(2016, "Please enter a valid Prefix");

        errorCodesMap.put(4001, "Provider is Already Mapped To Patient");
        errorCodesMap.put(4002, "Please enter a valid Provider Id");
        errorCodesMap.put(4003, "Please select at least one provider sub type");
        errorCodesMap.put(4004, "Please enter Provider ID");
        errorCodesMap.put(4005, "Provider is Not Mapped To Patient");
        errorCodesMap.put(4006, "Please enter a valid Payer Id");
        errorCodesMap.put(4007, "Payer is Already Mapped To Patient");
        errorCodesMap.put(4008, "Provider is Not Mapped To Gateway");

        errorCodesMap.put(5001, "Please enter a valid Notification ID");

        errorCodesMap.put(6001, "Please enter a valid patient Id");
        errorCodesMap.put(6002, "Please enter a valid data");
        errorCodesMap.put(6003, "Please enter a valid Schedule Type");
        errorCodesMap.put(6004, "Please enter a valid Handler");

        errorCodesMap.put(7001, "Please enter To Address");
        errorCodesMap.put(7002, "Please enter From Address");
        errorCodesMap.put(7003, "Message Doesn't Exist");

        errorCodesMap.put(8000, "Please enter a valid Status");
        errorCodesMap.put(8001, "Please enter a valid Priority");
        errorCodesMap.put(8002, "Please enter a valid Related To Module");
        errorCodesMap.put(8003, "Please enter a valid Tech Module");

        errorCodesMap.put(9001, "Content Doesn't Exist");
        errorCodesMap.put(9002, "Please enter a valid Content Type");
        errorCodesMap.put(9003, "Please enter a valid language");

    }


    public static String getErrorMessage(Integer key) {
        initialiseErrorMap();
        return errorCodesMap.get(key);
    }
}



