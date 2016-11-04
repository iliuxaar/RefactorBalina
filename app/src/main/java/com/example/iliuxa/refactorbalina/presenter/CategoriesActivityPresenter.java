package com.example.iliuxa.refactorbalina.presenter;


import com.example.iliuxa.refactorbalina.pojo.Category;

import java.util.List;

public interface CategoriesActivityPresenter {
    void setCategoriesInList(List<Category> categories);
    void getDataForList();
}
