package com.etwinkle.solutions.hardwaremanagement.models;

public class AcceptJob {
    private String accept;
    private String _id;
    private String jobID;
    private String description;

    public AcceptJob(String accept, String _id, String jobID, String description) {
        this.accept = accept;
        this._id = _id;
        this.jobID = jobID;
        this.description = description;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
