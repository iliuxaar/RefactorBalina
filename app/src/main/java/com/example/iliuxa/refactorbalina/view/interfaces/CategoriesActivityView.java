package com.example.iliuxa.refactorbalina.view.interfaces;

import java.util.List;


public interface CategoriesActivityView {
    void showCategoriesList(List data);
    void showRetryDialog();
    void showProgressDialog();
    void closeProgressDialog();
    boolean isNetworkAvailable();
    void startOfferActivity(int categoryId);
}
