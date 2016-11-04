package com.example.iliuxa.refactorbalina.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.adapter.MyAdapter;
import com.example.iliuxa.refactorbalina.pojo.Category;
import com.example.iliuxa.refactorbalina.presenter.CategoriesPresenter;
import com.example.iliuxa.refactorbalina.view.interfaces.CategoriesActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EActivity(R.layout.activity_categories)
public class CategoriesActivity extends AppCompatActivity implements CategoriesActivityView {

    private MyAdapter adapter;
    private ProgressDialog dialog;
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
    public void showRetryDialog() {
        openAlertDialog(getString(R.string.Dialog_title_network_unavaliable),getString(R.string.Dialog_negative_retry));
    }

    @Override
    public void startOfferActivity(int categoryId) {
        OffersActivity_.intent(this)
                .extra("id",((Category)adapter.getClickedItem(categoryId)).getIdCategory())
                .start();
    }

    @Override
    public void onBackPressed() {
        openAlertDialog(getString(R.string.Dialog_title_exit),null);
    }

    private void openAlertDialog(String title,String negativeWord) {
        final String negativeText;
        if(negativeWord == null){
            negativeText = getString(R.string.Dialog_negative_cancel);
        }else negativeText = negativeWord;
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                CategoriesActivity.this);
        quitDialog.setTitle(title);

        quitDialog.setPositiveButton("Выйти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(negativeText.equals(getString(R.string.Dialog_negative_cancel))) {
                    dialog.dismiss();
                }else if(negativeText.equals(getString(R.string.Dialog_negative_retry))){
                    downloadData();
                    dialog.dismiss();
                }
            }
        });

        quitDialog.show();
    }

    @Override
    public void showProgressDialog(){
        dialog = new ProgressDialog(CategoriesActivity.this);
        dialog.setTitle("Загрузка данных");
        dialog.setMessage("Пожалуйста, подождите");
        dialog.show();
    }

    @Override
    public void closeProgressDialog() {
        dialog.dismiss();
    }

    @Override
    public boolean isNetworkAvailable() {
        if(getApplicationContext() == null) { return false; }
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
