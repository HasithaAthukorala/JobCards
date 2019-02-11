package com.etwinkle.solutions.hardwaremanagement.models;

import com.google.gson.JsonArray;

public class FaultJson {
    public JsonArray details;

    public FaultJson(JsonArray details) {
        this.details = details;
    }

    public JsonArray getDetails() {
        return details;
    }

    public void setDetails(JsonArray details) {
        this.details = details;
    }
}
