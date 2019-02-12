package com.etwinkle.solutions.hardwaremanagement.models;

public class Assign {
    public String id;
    public String name;
    public String jobId;

    public Assign(String id, String name, String jobId) {
        this.id = id;
        this.name = name;
        this.jobId = jobId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
