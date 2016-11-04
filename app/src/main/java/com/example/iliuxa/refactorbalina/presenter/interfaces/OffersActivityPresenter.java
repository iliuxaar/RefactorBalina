package com.example.iliuxa.refactorbalina.presenter.interfaces;

import com.example.iliuxa.refactorbalina.pojo.Offer;

import java.sql.SQLException;
import java.util.List;


public interface OffersActivityPresenter {
    void setOffersInList(List<Offer> data) throws SQLException;
    void getDataForList(int id) throws SQLException;
}
