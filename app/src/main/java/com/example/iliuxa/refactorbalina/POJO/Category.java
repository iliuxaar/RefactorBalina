package com.example.iliuxa.refactorbalina.pojo;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "categories")
public class Category{
    @SerializedName("@id")
    @DatabaseField
    private int idCategory;
    @SerializedName("$")
    @DatabaseField(columnName = "category_name", canBeNull = false)
    private String name;

    public Category(){}
}
