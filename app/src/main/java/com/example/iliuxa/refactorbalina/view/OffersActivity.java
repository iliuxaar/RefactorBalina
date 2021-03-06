package com.example.iliuxa.refactorbalina.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.adapter.MyAdapter;
import com.example.iliuxa.refactorbalina.model.DataBaseFactory;
import com.example.iliuxa.refactorbalina.presenter.OffersPresenter;
import com.example.iliuxa.refactorbalina.view.interfaces.OffersActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_offers)
public class OffersActivity extends AppCompatActivity implements OffersActivityView {

    private final int LAYOUT_RESOURCE =  R.layout.simple_offer_list_item;
    private final OffersPresenter presenter = new OffersPresenter(this);

    @ViewById(R.id.offersList)
    RecyclerView offersList;

    @AfterViews
    void setBackArrow(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return false;
    }

    @Extra("id")
    int id;

    @AfterViews
    void getData(){
        presenter.getDataForList(id);
    }

    @Override
    public void showOffersList(List data) {
        try {
            MyAdapter adapter =
                    new MyAdapter(getApplicationContext(), LAYOUT_RESOURCE
                            , (ArrayList) DataBaseFactory.getHelper().getOfferDao().getOffersByCategoryId(id));
            offersList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            offersList.setAdapter(adapter);
        }catch (SQLException e){
            throw new RuntimeException("Error with dataBase");
        }
    }
}
