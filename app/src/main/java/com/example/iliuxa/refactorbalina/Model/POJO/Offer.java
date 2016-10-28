package com.example.iliuxa.refactorbalina.Model.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Iliuxa on 28.10.16.
 */

public class Offer {
    @SerializedName("@id")
    private String id;
    private String name;
    private String price;
    private String description;
    private String picture;
    private int categoryId;
    private ArrayList<Param> param;
}
