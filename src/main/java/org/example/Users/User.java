package org.example.Users;

abstract public class User {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    User(String name, String userName, String password) {
        this.name = name;
        this.password = password;
    }

    User(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
