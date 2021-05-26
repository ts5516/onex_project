package com.onex.onexproject.Model;

import android.net.Uri;

public class User {
    private String name;
    private String description;
    private String imageUri;

    public User(){}

    public User(String name){
        this.name = name;
        this.description = null;
    }
    public User(String name, String description, String imageUri) {
        this.name = name;
        this.description = description;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) { this.imageUri = imageUri; }
}
