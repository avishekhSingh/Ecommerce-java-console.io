package com.EcommerceStore.Users;

public abstract class User {
    private String userId=" ";
    private String Password = " ";
    private boolean loginStatus = false;
    abstract boolean verifyUser();                   // Body can't describe here because of Interface.

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}
