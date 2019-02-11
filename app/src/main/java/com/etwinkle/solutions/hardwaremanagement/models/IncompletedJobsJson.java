package com.etwinkle.solutions.hardwaremanagement.models;

import com.google.gson.JsonArray;

public class IncompletedJobsJson {
    private JsonArray pendingJobs;

    public IncompletedJobsJson(JsonArray pendingJobs) {
        this.pendingJobs = pendingJobs;
    }

    public JsonArray getPendingJobs() {
        return pendingJobs;
    }

    public void setPendingJobs(JsonArray pendingJobs) {
        this.pendingJobs = pendingJobs;
    }
}
