package com.example.iliuxa.refactorbalina.view;

import java.util.List;


public interface MainActivityView {
    void showDataInGrid(List data);
    void showErrorDialog();
    void showRetryDialog();
    void startNextActivity(int categoryId);
}
