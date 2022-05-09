package com.example.tcs;

public class LoginForm {

    private static String username;
    private static String password;

    public LoginForm(){
        super();
        // TODO Auto-generated Constructor stub
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
       LoginForm.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        LoginForm.password = password;
    }
}
