package com.etwinkle.solutions.hardwaremanagement.models;

import com.etwinkle.solutions.hardwaremanagement.tableview.model.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopEquipment {

    private String equipID;
    private String shopID;
    private String balance;
    private String sold;
    private String error;
    private String equipCode;
    private String equipname;
    private String unitPrice;
    private String description;
    private String photo;

    public ShopEquipment(String equipID, String shopID, String balance, String sold, String error, String equipCode, String equipname, String unitPrice, String description, String photo) {
        this.equipID = equipID;
        this.shopID = shopID;
        this.balance = balance;
        this.sold = sold;
        this.error = error;
        this.equipCode = equipCode;
        this.equipname = equipname;
        this.unitPrice = unitPrice;
        this.description = description;
        this.photo = photo;
    }

    public String getEquipID() {
        return equipID;
    }

    public void setEquipID(String equipID) {
        this.equipID = equipID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEquipCode() {
        return equipCode;
    }

    public void setEquipCode(String equipCode) {
        this.equipCode = equipCode;
    }

    public String getEquipname() {
        return equipname;
    }

    public void setEquipname(String equipname) {
        this.equipname = equipname;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static List<String> getColumnNames(){
        List<String> columnsList = new ArrayList<>();
        columnsList.add("photo");
        columnsList.add("equipID");
        columnsList.add("balance");
        columnsList.add("sold");
        columnsList.add("equipCode");
        columnsList.add("equipname");
        columnsList.add("unitPrice");
        columnsList.add("description");
        return columnsList;
    }

    public static List<List<Cell>> getCellList(List<ShopEquipment> shopEquipmentsList) {
        List<List<Cell>> list = new ArrayList<>();
        int i =0;
        for(ShopEquipment shopEquipment:shopEquipmentsList){
            List<Cell> cellList = new ArrayList<>();
            Cell cell0 = new Cell(0 + "-" + i,shopEquipment.getPhoto());
            Cell cell1 = new Cell(1 + "-" + i,shopEquipment.getEquipID());
            Cell cell2 = new Cell(2 + "-" + i,shopEquipment.getBalance());
            Cell cell3 = new Cell(3 + "-" + i,shopEquipment.getSold());
            Cell cell4 = new Cell(4 + "-" + i,shopEquipment.getEquipCode());
            Cell cell5 = new Cell(5 + "-" + i,shopEquipment.getEquipname());
            Cell cell6 = new Cell(6 + "-" + i,shopEquipment.getUnitPrice());
            Cell cell7 = new Cell(7 + "-" + i,shopEquipment.getDescription());
            cellList.add(cell0);
            cellList.add(cell1);
            cellList.add(cell2);
            cellList.add(cell3);
            cellList.add(cell4);
            cellList.add(cell5);
            cellList.add(cell6);
            cellList.add(cell7);
            list.add(cellList);
            i++;
        }
        return list;
    }

}
