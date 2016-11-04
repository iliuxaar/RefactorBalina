package com.example.iliuxa.refactorbalina.presenter;

import com.example.iliuxa.refactorbalina.model.DataBaseFactory;
import com.example.iliuxa.refactorbalina.view.OffersActivityView;

import java.sql.SQLException;


public class OffersPresenter implements OffersActivityPresenter{
    private OffersActivityView view;

    public OffersPresenter(OffersActivityView view){
        this.view = view;
    }

    @Override
    public void setOffersInList(int id) throws SQLException {
        view.showOffersList(DataBaseFactory.getHelper().getOfferDao().getOffersByCategoryId(id));
    }

    @Override
    public void getDataForList(int id) throws SQLException {
        setOffersInList(id);
    }
}
