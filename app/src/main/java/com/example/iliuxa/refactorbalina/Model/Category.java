package com.example.iliuxa.refactorbalina.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Iliuxa on 28.10.16.
 */

public class Category {
    @SerializedName("@id")
    private String id;
    @SerializedName("$")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
