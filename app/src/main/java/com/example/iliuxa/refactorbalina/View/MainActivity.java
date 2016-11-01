package com.example.iliuxa.refactorbalina.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.pojo.Yml_catalog;
import com.example.iliuxa.refactorbalina.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private RecyclerView categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoriesList = (RecyclerView) findViewById(R.id.categoriesList);
        final MainPresenter presenter = new MainPresenter(this);
        presenter.saveData();
    }


    @Override
    public void showData(Yml_catalog data) {

    }
}
