package com.example.iliuxa.refactorbalina.pojo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;


@Table(name = "Categories")
public class Category extends Model{
    @SerializedName("@id")
    @Column(name = "id_category")
    private int idCategory;
    @SerializedName("$")
    @Column(name = "category_name")
    private String name;
}
