package com.cleanway.apackage.cleanarchpackageway.model_layer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppsRes {
    @SerializedName("message")
    public Message message;

    @SerializedName("success")
    public boolean success;

    @SerializedName("data")
    public List<Apps> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Apps> getData() {
        return data;
    }

    public void setData(List<Apps> data) {
        this.data = data;
    }


}

class Message {
    @SerializedName("CODE")
    public int code;
    @SerializedName("MESSAGE")
    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
