package com.example.iliuxa.refactorbalina.presenter;


import java.sql.SQLException;

public interface MyPresenter {
    void setItemsList()throws SQLException;
    void createNewWindow();
}
