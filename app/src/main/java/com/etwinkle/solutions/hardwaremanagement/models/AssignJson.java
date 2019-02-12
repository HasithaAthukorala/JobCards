package com.etwinkle.solutions.hardwaremanagement.models;

import com.google.gson.JsonArray;

public class AssignJson {
    public JsonArray attendTechnician;

    public AssignJson(JsonArray attendTechnician) {
        this.attendTechnician = attendTechnician;
    }

    public JsonArray getDetails() {
        return attendTechnician;
    }

    public void setDetails(JsonArray attendTechnician) {
        this.attendTechnician = attendTechnician;
    }
}
