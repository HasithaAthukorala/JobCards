package com.etwinkle.solutions.hardwaremanagement.models;

import com.google.gson.JsonArray;

public class PendingJobsJson {
    private JsonArray pendingJobs;

    public PendingJobsJson(JsonArray pendingJobs) {
        this.pendingJobs = pendingJobs;
    }

    public JsonArray getPendingJobs() {
        return pendingJobs;
    }

    public void setPendingJobs(JsonArray pendingJobs) {
        this.pendingJobs = pendingJobs;
    }
}
