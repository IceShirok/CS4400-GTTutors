package edu.gatech.GTTutors.model;

public class LoginStore {

    private String username;
    private String userType;

    public LoginStore() {}

    public void logIn(String username, String userType) {
        this.username = username;
        this.userType = userType;
    }

    public boolean logOut() {
        if (username != null) {
            username = null;
            userType = null;
            return true;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }

}