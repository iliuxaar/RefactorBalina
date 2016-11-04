package com.example.iliuxa.refactorbalina.presenter;


import java.sql.SQLException;

public interface CategoriesActivityPresenter {
    void setCategoriesInList()throws SQLException;
    void getDataForList();
}
