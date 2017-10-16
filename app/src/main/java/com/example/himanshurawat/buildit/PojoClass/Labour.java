package com.example.himanshurawat.buildit.PojoClass;

/**
 * Created by himanshurawat on 14/10/17.
 */
public class Labour {
    String id;
    String name;
    String email;
    String profileURL;

    public Labour() {
    }

    public Labour(String id, String name, String email, String profileURL) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileURL = profileURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
}