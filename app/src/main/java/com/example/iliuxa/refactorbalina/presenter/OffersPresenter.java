package com.example.iliuxa.refactorbalina.presenter;

import android.os.AsyncTask;

import com.example.iliuxa.refactorbalina.model.DataBaseFactory;
import com.example.iliuxa.refactorbalina.pojo.Offer;
import com.example.iliuxa.refactorbalina.view.OffersActivityView;

import java.sql.SQLException;
import java.util.List;


public class OffersPresenter implements OffersActivityPresenter{
    private OffersActivityView view;

    public OffersPresenter(OffersActivityView view){
        this.view = view;
    }

    @Override
    public void setOffersInList(List<Offer> data){
        view.showOffersList(data);
    }

    @Override
    public void getDataForList(int id) {
        GetOffersByCategory getOffersByCategory = new GetOffersByCategory();
        getOffersByCategory.execute(id);
    }


    private class GetOffersByCategory extends AsyncTask<Integer,Void,List<Offer>>{

        @Override
        protected List<Offer> doInBackground(Integer... params) {
            try {
                return DataBaseFactory.getHelper().getOfferDao().getOffersByCategoryId(params[0]);
            } catch (SQLException e) {
                throw new RuntimeException("DataBase cannnot read offers");
            }
        }

        @Override
        protected void onPostExecute(List<Offer> offers) {
            setOffersInList(offers);
        }
    }

}
