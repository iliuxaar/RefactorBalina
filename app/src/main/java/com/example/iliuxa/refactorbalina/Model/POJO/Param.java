package com.example.iliuxa.refactorbalina.Model.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Iliuxa on 28.10.16.
 */

public class Param {
    @SerializedName("@name")
    private String paramName;
    @SerializedName("$")
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getName() {
        return paramName;
    }

    public void setName(String name) {
        this.paramName = name;
    }
}
