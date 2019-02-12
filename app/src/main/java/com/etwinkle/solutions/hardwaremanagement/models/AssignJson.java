package com.etwinkle.solutions.hardwaremanagement.models;

import com.google.gson.JsonArray;

public class AssignJson {
    public JsonArray availableTechnician;

    public AssignJson(JsonArray availableTechnician) {
        this.availableTechnician = availableTechnician;
    }

    public JsonArray getDetails() {
        return availableTechnician;
    }

    public void setDetails(JsonArray availableTechnician) {
        this.availableTechnician = availableTechnician;
    }
}
