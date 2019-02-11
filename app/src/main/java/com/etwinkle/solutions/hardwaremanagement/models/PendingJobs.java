package com.etwinkle.solutions.hardwaremanagement.models;

public class PendingJobs {
    private String jobId;
    private String description;
    private String faultImage;
    private String departmentName;
    private String faultName;
    private String faultCategoryName;
    private String date;

    public PendingJobs(String jobId, String description, String faultImage, String departmentName, String faultName, String faultCategoryName, String date) {
        this.jobId = jobId;
        this.description = description;
        this.faultImage = faultImage;
        this.departmentName = departmentName;
        this.faultName = faultName;
        this.faultCategoryName = faultCategoryName;
        this.date = date;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFaultImage() {
        return faultImage;
    }

    public void setFaultImage(String faultImage) {
        this.faultImage = faultImage;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFaultName() {
        return faultName;
    }

    public void setFaultName(String faultName) {
        this.faultName = faultName;
    }

    public String getFaultCategoryName() {
        return faultCategoryName;
    }

    public void setFaultCategoryName(String faultCategoryName) {
        this.faultCategoryName = faultCategoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

/*

"_id": "5c5cd027cf9a0030f8eb70b6",
            "jobId": "01245",
            "date": "2019-02-11T00:41:11.024Z",
            "description": "",
            "faultImage": "fault_images\\1549586470822_webshots21.jpg",
            "serialNumber": "1234RTL0916010002",
            "departmentName": "Cutting",
            "faultName": "Oil Leak",
            "faultCategoryName": "Mechanical"

*/
