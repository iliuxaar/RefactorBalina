package com.example.iliuxa.refactorbalina.pojo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "Dishes")
public class Offer extends Model{

    @Column(name = "dish_name")
    private String name;
    @Column(name = "price")
    private String price;
    @Column(name = "description")
    private String description;
    @Column(name = "picture")
    private String picture;
    @Column(name = "category_id")
    private int categoryId;

    public Offer(){
        super();
    }
}
