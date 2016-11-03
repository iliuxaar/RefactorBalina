package com.example.iliuxa.refactorbalina.presenter;

import android.os.AsyncTask;

import com.example.iliuxa.refactorbalina.model.DataBase;
import com.example.iliuxa.refactorbalina.pojo.Yml_catalog;
import com.example.iliuxa.refactorbalina.view.MainActivityView;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainPresenter implements MyPresenter{
    MainActivityView view;
    DataBase dataBase;
    Yml_catalog catalog;

    public MainPresenter(MainActivityView view){
        this.view = view;
        dataBase = new DataBase();
    }


    public void saveData(){
        DownloadDataBase test = new DownloadDataBase();
        test.execute();
    }


    @Override
    public void setItemsList() {
        //view.showDataInGrid(dataBase.getCategoriesList());
    }

    @Override
    public void createNewWindow() {
        //view.startNextActivity();
    }

    private String getHttpRequest(String path) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private class DownloadDataBase extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            XmlParserCreator parserCreator = new XmlParserCreator() {
                @Override
                public XmlPullParser createParser() {
                    try {
                        return XmlPullParserFactory.newInstance().newPullParser();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            GsonXml gsonXml = new GsonXmlBuilder()
                    .setXmlParserCreator(parserCreator)
                    .create();
            try {
                catalog = gsonXml.fromXml(
                        getHttpRequest("http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN")
                        , Yml_catalog.class);
                //dataBase.saveCategoriesToDataBase(catalog.getShop().getCategories());
                //dataBase.saveDishesToDataBase(catalog.getShop().getOffers());
                catalog.getClass();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute()   {
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }

}
