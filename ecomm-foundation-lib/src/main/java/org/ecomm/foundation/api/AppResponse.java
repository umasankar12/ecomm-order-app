package org.ecomm.foundation.api;

import java.util.ArrayList;
import java.util.List;

public class AppResponse<T> {
    List<T> models = new ArrayList<>();
    AppRequest<T> request;
    boolean success;
    int status;
    String message;

    public AppResponse() {
    }

    public AppResponse(T model) {
        this.models.add(model);
    }

    public AppResponse(List<T> models) {
        this.models = models;
    }

    public AppRequest<T> getRequest() {
        return request;
    }

    public AppResponse<T> setRequest(AppRequest<T> request) {
        this.request = request;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public AppResponse<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public AppResponse<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AppResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public static <Model> AppResponse<Model> successOf(List<Model> results, String message, AppRequest<Model> request) {
        AppResponse<Model> response = new AppResponse<>(results);
        return response.setSuccess(true).setMessage(message).setRequest(request);
    }

    public static <Model> AppResponse<Model> successOf(Model result, String message, AppRequest<Model> request) {
        AppResponse<Model> response = new AppResponse<>(result);
        return response.setSuccess(true).setMessage(message).setRequest(request);
    }

    public static <Model> AppResponse<Model> failureOf(String message, AppRequest<Model> request) {
        AppResponse<Model> response = new AppResponse<>();
        return response.setSuccess(false)
          .setMessage(message)
          .setRequest(request);
    }
}
