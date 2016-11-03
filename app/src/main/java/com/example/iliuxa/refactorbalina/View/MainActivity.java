package com.example.iliuxa.refactorbalina.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.adapter.MyAdapter;
import com.example.iliuxa.refactorbalina.presenter.MainPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainActivityView {

    private RecyclerView categoriesList;
    final MainPresenter presenter = new MainPresenter(this);;

    @ViewById(R.id.categoriesList)
    RecyclerView mCategoriesList;

    @AfterViews
    void downloadData(){
        presenter.saveData();
    }

    @Override
    public void showDataInGrid(List data) {
        MyAdapter adapter = new MyAdapter(R.layout.simple_category_list_item, (ArrayList)data);
        categoriesList.setAdapter(adapter);
        categoriesList.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
//        presenter.setItemsList();
    }

    @Override
    public void showErrorDialog() {

    }

    @Override
    public void showRetryDialog() {

    }

    @Override
    public void startNextActivity(int categoryId) {

    }
}
