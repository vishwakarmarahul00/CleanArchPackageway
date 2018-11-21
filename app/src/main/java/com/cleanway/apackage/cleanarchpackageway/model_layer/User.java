
package com.cleanway.apackage.cleanarchpackageway.model_layer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
