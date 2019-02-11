package com.etwinkle.solutions.hardwaremanagement.models;

import com.google.gson.JsonArray;

public class CompletedJobsJson {
    private JsonArray completedJobsDetails;

    public CompletedJobsJson(JsonArray completedJobsDetails) {
        this.completedJobsDetails = completedJobsDetails;
    }

    public JsonArray getPendingJobs() {
        return completedJobsDetails;
    }

    public void setPendingJobs(JsonArray completedJobsDetails) {
        this.completedJobsDetails = completedJobsDetails;
    }
}
