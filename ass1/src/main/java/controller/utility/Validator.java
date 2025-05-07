package controller.utility;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validator implements Serializable {
    private String gmail = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
    private String passWord = "[a-zA-Z0-9]{4,}"; // can be lower cas uppercase or number but at least 4 digits
    private String userName = "^[a-zA-Z0-9_]{1,20}$";// can be lower upper or number but maximum 20 digits
    private String phone = "[0-9]{10}"; //phone number have to be 10 digits

    public Validator(){

    }

    boolean validate(String usePattern,String input){
        Pattern pattern = Pattern.compile(usePattern, Pattern.CASE_INSENSITIVE);// case- insentive so that no compare upper or lower case
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public boolean checkEmpty(String userNamePattern, String passwordPattern){
        return userNamePattern.isEmpty() || passwordPattern.isEmpty();
    }

    public boolean gmailValidate(String gmailPattern){// check for valid gmail which need @gmail
       return validate(gmail, gmailPattern);

    }
    public boolean passwordValidate(String passwordPattern) {
        return validate(passWord,passwordPattern);
    }

    public boolean userNameValidate(String userNamePattern) {
        return validate(userName, userNamePattern);
    }
    public boolean phoneValidate(String phonePattern){
        return validate(phone, phonePattern);
    }

 
}
