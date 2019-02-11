package com.etwinkle.solutions.hardwaremanagement.utils;

import com.etwinkle.solutions.hardwaremanagement.models.ShopEquipment;

import java.util.List;

public class OnDataChangeEvent {
    private List<ShopEquipment> shopEquipmentsList;
    private int rowID;
    private String balance;
    private String sold;

    public OnDataChangeEvent(List<ShopEquipment> shopEquipmentsList, int rowID, String balance, String sold) {
        this.shopEquipmentsList = shopEquipmentsList;
        this.rowID = rowID;
        this.balance = balance;
        this.sold = sold;
    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public List<ShopEquipment> getShopEquipmentsList() {
        return shopEquipmentsList;
    }

    public void setShopEquipmentsList(List<ShopEquipment> shopEquipmentsList) {
        this.shopEquipmentsList = shopEquipmentsList;
    }
}
