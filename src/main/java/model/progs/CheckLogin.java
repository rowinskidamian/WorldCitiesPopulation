package model.progs;

public class CheckLogin {

    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    public static boolean check(String username, String password) {
        if(USERNAME.equals(username) && PASSWORD.equals(password))
            return true;
        else
            return false;
    }

}


