package com.example.iliuxa.refactorbalina.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "offers")
public class Offer{
    public static final String OFFER_COLUMN_CATEGORY_ID = "category_id";

    @DatabaseField(columnName = "offer_name")
    private String name;
    @DatabaseField
    private String price;
    @DatabaseField
    private String description;
    @DatabaseField
    private String picture;
    @DatabaseField
    private int categoryId;

    public Offer(){
        super();
    }
}
