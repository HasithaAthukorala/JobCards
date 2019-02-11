package com.etwinkle.solutions.hardwaremanagement.models;


public class UserObject {

    private String repID;
    private String agentID;
    private String repName;
    private String contact;
    private String nic;
    private String email;
    private String imagePath;
    private String status;
    private String addedDate;
    private String error;
    private String success;
    private String employeeId;
    private String employeeTypeName;

    public UserObject(String repID, String agentID, String repName, String contact, String nic, String email, String imagePath, String status, String addedDate, String error, String success, String employeeId, String employeeTypeName) {
        this.repID = repID;
        this.agentID = agentID;
        this.repName = repName;
        this.contact = contact;
        this.nic = nic;
        this.email = email;
        this.imagePath = imagePath;
        this.status = status;
        this.addedDate = addedDate;
        this.error = error;
        this.success = success;
        this.employeeId = employeeId;
        this.employeeTypeName = employeeTypeName;
    }

    public String getRepID() {
        return repID;
    }

    public void setRepID(String repID) {
        this.repID = repID;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeTypeName() {
        return employeeTypeName;
    }

    public void setEmployeeTypeName(String employeeTypeName) {
        this.employeeTypeName = employeeTypeName;
    }
}
