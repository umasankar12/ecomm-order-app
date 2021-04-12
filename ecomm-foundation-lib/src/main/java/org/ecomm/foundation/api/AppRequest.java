package org.ecomm.foundation.api;

import java.time.LocalDateTime;

public class AppRequest<T> {

    T input;
    String user;
    String source;
    LocalDateTime entryTime;

    public AppRequest(){}

    public AppRequest(T input){
        this.input = input;
    }

    public T getInput() {
        return input;
    }

    public AppRequest<T> setInput(T input) {
        this.input = input;
        return this;
    }

    public String getUser() {
        return user;
    }

    public AppRequest<T> setUser(String user) {
        this.user = user;
        return this;
    }

    public String getSource() {
        return source;
    }

    public AppRequest<T> setSource(String source) {
        this.source = source;
        return this;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public AppRequest<T> setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
        return this;
    }
}
