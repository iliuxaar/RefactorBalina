package com.example.iliuxa.refactorbalina.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.example.iliuxa.refactorbalina.application.MyApplication;
import com.example.iliuxa.refactorbalina.model.DataBaseFactory;
import com.example.iliuxa.refactorbalina.pojo.Category;
import com.example.iliuxa.refactorbalina.pojo.Yml_catalog;
import com.example.iliuxa.refactorbalina.presenter.interfaces.CategoriesActivityPresenter;
import com.example.iliuxa.refactorbalina.view.interfaces.CategoriesActivityView;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CategoriesPresenter implements CategoriesActivityPresenter {
    private CategoriesActivityView view;
    private Yml_catalog catalog;
    private final String URL = "http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN";

    public CategoriesPresenter(CategoriesActivityView view){
        this.view = view;
    }

    @Override
    public void setCategoriesInList(List<Category> categories){
        view.showCategoriesList(categories);
    }

    @Override
    public void getDataForList() {
        LoadDataBase load =new LoadDataBase();
        load.execute();
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

    private Yml_catalog getCatalog() throws IOException {
        GsonXml gsonXml = new GsonXmlBuilder()
                .setXmlParserCreator(getXmlPullParser())
                .create();
        return gsonXml.fromXml(getHttpRequest(URL), Yml_catalog.class);
    }

    private class LoadDataBase extends AsyncTask<Void, Void, List<Category>> {

        @Override
        protected List<Category> doInBackground(Void... params) {
            try {
                if (DataBaseFactory.getHelper().isDataBaseEmpty()) {
                    if(view.isNetworkAvailable()) {
                        Yml_catalog catalog = getCatalog();
                        DataBaseFactory.getHelper().getCategoryDAO().createWithCheck(catalog.getShop().getCategories());
                        DataBaseFactory.getHelper().getOfferDao().createWithCheck(catalog.getShop().getOffers());
                        return DataBaseFactory.getHelper().getCategoryDAO().getAllCategories();
                    }else return null;
                } else return DataBaseFactory.getHelper().getCategoryDAO().getAllCategories();
            } catch (SQLException e) {
                throw new RuntimeException("Error with database");
            } catch (IOException e) {
                throw new RuntimeException("Parce Error");
            }
        }

        @Override
        protected void onPreExecute(){
            view.showProgressDialog();
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            view.closeProgressDialog();
            try {
                if (categories != null) {
                    setCategoriesInList(categories);
                } else if (DataBaseFactory.getHelper().isDataBaseEmpty()) {
                    view.showRetryDialog();
                }
            }catch (SQLException e){
                throw new RuntimeException("Error with DataBase");
            }
        }
    }

}
