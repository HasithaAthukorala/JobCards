package com.etwinkle.solutions.hardwaremanagement.models;

public class Summary {
    private String todayAllJobs;
    private String todayCompletedJobs;
    private String todayIncompletedJobs;

    public Summary(String todayAllJobs, String todayCompletedJobs, String todayIncompletedJobs) {
        this.todayAllJobs = todayAllJobs;
        this.todayCompletedJobs = todayCompletedJobs;
        this.todayIncompletedJobs = todayIncompletedJobs;
    }

    public String getTodayAllJobs() {
        return todayAllJobs;
    }

    public void setTodayAllJobs(String todayAllJobs) {
        this.todayAllJobs = todayAllJobs;
    }

    public String getTodayCompletedJobs() {
        return todayCompletedJobs;
    }

    public void setTodayCompletedJobs(String todayCompletedJobs) {
        this.todayCompletedJobs = todayCompletedJobs;
    }

    public String getTodayIncompletedJobs() {
        return todayIncompletedJobs;
    }

    public void setTodayIncompletedJobs(String todayIncompletedJobs) {
        this.todayIncompletedJobs = todayIncompletedJobs;
    }
}
