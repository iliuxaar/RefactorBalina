package com.example.iliuxa.refactorbalina.view;

import java.util.List;


public interface CategoriesActivityView {
    void showCategoriesList(List data);
    void showErrorDialog();
    void showRetryDialog();
    void startOfferActivity(int categoryId);
}
