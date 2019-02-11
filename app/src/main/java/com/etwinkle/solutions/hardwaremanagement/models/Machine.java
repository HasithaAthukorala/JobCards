package com.etwinkle.solutions.hardwaremanagement.models;

import com.etwinkle.solutions.hardwaremanagement.utils.CustomApplication;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Machine {
    public JsonObject unique;
    public String _id;
    public String serialNumber;
    public String location;
    public String supervisorID;
    public String departmentID;
    public String departmentName;
    public String departmentID_id;


    public Machine(JsonObject unique) {
        this.unique = unique;

        JsonObject mainObject = null;
        try {
            mainObject = (unique);

            serialNumber = mainObject.get("serialNumber").toString();
            supervisorID = mainObject.get("supervisorId").toString();
            _id = mainObject.get("_id").toString();
            location = mainObject.get("location").toString();
            JsonObject departmentObject = mainObject.getAsJsonObject("departmentId");
            departmentName = departmentObject.get("departmentName").toString();
            departmentID_id = departmentObject.get("_id").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public JsonObject getUnique() {
        return unique;
    }

    public void setUnique(JsonObject unique) {
        this.unique = unique;
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

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
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
