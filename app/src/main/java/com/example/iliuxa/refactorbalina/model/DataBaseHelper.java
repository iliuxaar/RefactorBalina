package com.example.iliuxa.refactorbalina.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.iliuxa.refactorbalina.dao.CategoryDao;
import com.example.iliuxa.refactorbalina.dao.OfferDao;
import com.example.iliuxa.refactorbalina.pojo.Category;
import com.example.iliuxa.refactorbalina.pojo.Offer;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Iliuxa on 03.11.16.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    public static final String TAG = DataBaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "shop.db";
    private static final int DATABASE_VERSION = 1;

    private CategoryDao catDao;
    private OfferDao offerDao;

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    //Creating table
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, Offer.class);
        }catch (SQLException e){
            Log.e(TAG,"error creating DB" + DATABASE_NAME);
            throw new RuntimeException();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //empty for this task
    }

    //Singleton for categoryDao
    public CategoryDao getCategoryDAO()throws SQLException{
        if(catDao == null){
            catDao = new CategoryDao(getConnectionSource(), Category.class);
        }
        return catDao;
    }

    //Singleton for OfferDao
    public OfferDao getOfferDAO()throws SQLException{
        if(offerDao == null){
            offerDao = new OfferDao(getConnectionSource(), Offer.class);
        }
        return offerDao;
    }

    public boolean isDataBaseEmpty()throws SQLException{
        return getCategoryDAO().getAllCategories().size() == 0 || getOfferDAO().getAllOffers().size() == 0;
    }

    @Override
    public void close() {
        super.close();
        catDao = null;
        offerDao = null;
    }
}
