package ru.pas_zhukov.notificationservice.model;

public class User {
    private String login;
    private UserRole authority;

    public User() {
    }

    public User(UserRole authority, String login) {
        this.authority = authority;
        this.login = login;
    }

    public UserRole getAuthority() {
        return authority;
    }

    public void setAuthority(UserRole authority) {
        this.authority = authority;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}