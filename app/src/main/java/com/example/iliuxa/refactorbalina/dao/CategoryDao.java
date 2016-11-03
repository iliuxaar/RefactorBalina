package com.example.iliuxa.refactorbalina.dao;

import com.example.iliuxa.refactorbalina.pojo.Category;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Iliuxa on 03.11.16.
 */

public class CategoryDao extends BaseDaoImpl<Category,Integer>{

    public CategoryDao(ConnectionSource connectionSource
            , Class<Category> dataClass)throws SQLException{
        super(connectionSource,dataClass);
    }

    public List<Category> getAllCategories()throws SQLException{
        return this.queryForAll();
    }
}
