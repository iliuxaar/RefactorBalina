package com.example.iliuxa.refactorbalina.presenter;

import java.sql.SQLException;


public interface OffersActivityPresenter {
    public void setOffersInList(int id) throws SQLException;
    void getDataForList(int id) throws SQLException;
}
