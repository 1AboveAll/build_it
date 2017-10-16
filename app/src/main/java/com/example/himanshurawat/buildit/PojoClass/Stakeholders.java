package com.example.himanshurawat.buildit.PojoClass;

import java.io.Serializable;

/**
 * Created by himanshurawat on 15/10/17.
 */

public class Stakeholders implements Serializable {
    private String id;
    private String name;
    private String email;
    private String profileURL;

    public Stakeholders() {
    }

    public Stakeholders(String id, String name, String email, String profileURL) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileURL = profileURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
}

