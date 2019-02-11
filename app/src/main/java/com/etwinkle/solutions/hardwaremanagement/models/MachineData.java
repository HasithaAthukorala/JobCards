package com.etwinkle.solutions.hardwaremanagement.models;

import com.google.gson.JsonObject;

public class MachineData {
    public String _id;
    public String serialNumber;
    public String location;
    public String supervisorID;
    public String departmentName;
    public String departmentID_id;

    public MachineData(String _id, String serialNumber, String location, String supervisorID, String departmentName, String departmentID_id) {
        this._id = _id;
        this.serialNumber = serialNumber;
        this.location = location;
        this.supervisorID = supervisorID;
        this.departmentName = departmentName;
        this.departmentID_id = departmentID_id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentID_id() {
        return departmentID_id;
    }

    public void setDepartmentID_id(String departmentID_id) {
        this.departmentID_id = departmentID_id;
    }
}
