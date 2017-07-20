package com.elearning.elearning.mvp.model;

import com.elearning.elearning.network.APIConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vomin on 20/07/2017.
 */

public class Category {
    private int id;
    private String nameCategory;
    private String urlImage;

    public Category() {

    }

    public Category(int id, String nameCategory, String urlImage) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.urlImage = urlImage;
    }

    public static List<Category> pareListCategory(JSONArray jsonArray) throws JSONException {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject data = (JSONObject) jsonArray.get(i);
            Category category = new Category();
            category.setId(data.getInt(APIConstant.ID));
            category.setNameCategory(data.getString(APIConstant.LESSON_NAME));
            category.setUrlImage(data.getString(APIConstant.LESSON_URLIMAGE));
            categoryList.add(category);
        }
        return categoryList;
    }

    public int getId() {
        return id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
