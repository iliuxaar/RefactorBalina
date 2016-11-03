package com.example.iliuxa.refactorbalina.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.example.iliuxa.refactorbalina.application.MyApplication;
import com.example.iliuxa.refactorbalina.model.DataBase;
import com.example.iliuxa.refactorbalina.model.HelperFactory;
import com.example.iliuxa.refactorbalina.pojo.Yml_catalog;
import com.example.iliuxa.refactorbalina.view.MainActivityView;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.sql.SQLException;

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
    public void setItemsList() throws SQLException{
        view.showDataInGrid(HelperFactory.getHelper().getCategoryDAO().getAllCategories());
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

    private XmlParserCreator getXmlPullParser(){
        XmlParserCreator parserCreator = new XmlParserCreator() {
            @Override
            public XmlPullParser createParser() {
                try {
                    return XmlPullParserFactory.newInstance().newPullParser();
                } catch (XmlPullParserException e) {
                    Log.e(MyApplication.TAG, "Xml Parser Exeption");
                        throw new RuntimeException(e);
                }
            }
        };
        return parserCreator;
    }

    private class DownloadDataBase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (HelperFactory.getHelper().isDataBaseEmpty()) {
                    GsonXml gsonXml = new GsonXmlBuilder()
                            .setXmlParserCreator(getXmlPullParser())
                            .create();
                    catalog = gsonXml
                            .fromXml(getHttpRequest("http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN"), Yml_catalog.class);
                    HelperFactory.getHelper().getCategoryDAO().createWithCheck(catalog.getShop().getCategories());
                    HelperFactory.getHelper().getOfferDAO().createWithCheck(catalog.getShop().getOffers());
                }else return null;
            } catch (SQLException e) {
                Log.e(MyApplication.TAG, "Error with database");
                throw new RuntimeException();
            } catch (IOException e) {
                Log.e(MyApplication.TAG, "Parce Error");
                throw new RuntimeException();
            }
            return null;
        }

        @Override
        protected void onPreExecute()   {
        }

        @Override
        protected void onPostExecute(Void Void) {
            try {
                setItemsList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
