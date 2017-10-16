package com.example.himanshurawat.buildit.PojoClass;

import java.io.Serializable;

/**
 * Created by himanshurawat on 14/10/17.
 */

public class User implements Serializable{

    private String email;
    private String userType;

    public User(){

    }

    public User(String email, String userType) {
        this.email = email;
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
