package com.example.iliuxa.refactorbalina.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.adapter.MyAdapter;
import com.example.iliuxa.refactorbalina.pojo.Category;
import com.example.iliuxa.refactorbalina.presenter.CategoriesPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EActivity(R.layout.activity_categories)
public class CategoriesActivity extends AppCompatActivity implements CategoriesActivityView {

    private MyAdapter adapter;
    private final CategoriesPresenter presenter = new CategoriesPresenter(this);
    private final int LAYOUT_RESOURCE = R.layout.simple_category_list_item;

    @ViewById(R.id.categoriesList)
    RecyclerView mCategoriesList;

    @AfterViews
    void downloadData() {
        presenter.getDataForList();
    }

    @Override
    public void showCategoriesList(List data) {
        adapter = new MyAdapter(getApplicationContext(), LAYOUT_RESOURCE , (ArrayList) data);
        mCategoriesList.setAdapter(adapter);
        mCategoriesList.setLayoutManager(new GridLayoutManager(this, 2));
        mCategoriesList.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),mCategoriesList, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startOfferActivity(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

    @Override
    public void showErrorDialog() {

    }

    @Override
    public void showRetryDialog() {

    }

    @Override
    public void startOfferActivity(int categoryId) {
        OffersActivity_.intent(this)
                .extra("id",((Category)adapter.getClickedItem(categoryId)).getIdCategory())
                .start();
    }

}
