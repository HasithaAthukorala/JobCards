package com.etwinkle.solutions.hardwaremanagement.models;

import com.google.gson.JsonArray;

public class TechJson {
    public JsonArray assignTechnicianJobs;

    public TechJson(JsonArray assignTechnicianJobs) {
        this.assignTechnicianJobs = assignTechnicianJobs;
    }

    public JsonArray getAssignTechnicianJobs() {
        return assignTechnicianJobs;
    }

    public void setAssignTechnicianJobs(JsonArray assignTechnicianJobs) {
        this.assignTechnicianJobs = assignTechnicianJobs;
    }
}
