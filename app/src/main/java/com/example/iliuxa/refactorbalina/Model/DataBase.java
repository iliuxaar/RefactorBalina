package com.example.iliuxa.refactorbalina.model;


public class DataBase {
    /*public void saveCategoriesToDataBase(ArrayList<Category> categories){
        ActiveAndroid.beginTransaction();
        for (int i = 0; i< categories.size(); i++){
            categories.get(i).save();
        }
        ActiveAndroid.setTransactionSuccessful();
        ActiveAndroid.endTransaction();
    }

    public void saveDishesToDataBase(ArrayList<Offer> offers){
        ActiveAndroid.beginTransaction();
        for(int i = 0; i < offers.size(); i++){
            offers.get(i).save();
        }
        ActiveAndroid.setTransactionSuccessful();
        ActiveAndroid.endTransaction();
    }

    public List<Offer> getOfferListByCategory(int categoryId){
        return new Select()
                .from(Offer.class)
                .where("category_id = ?", categoryId)
                .execute();
    }

    public List<Category> getCategoriesList(){
        return new Select()
                .from(Category.class)
                .orderBy("RANDOM()")
                .execute();
    }

    public Offer getOffer(int id){
        return new Select()
                .from(Offer.class)
                .where(" = ?", id)
                .executeSingle();
        //todo: change there
    }*/
}
