package com.etwinkle.solutions.hardwaremanagement.models;

public class Shop {
    /**
     * shopID	"123456"
     * repID	"123456"
     * shopCode	"SHOP123456"
     * shopName	"Test Groceries"
     * email	"test@test.com"
     * latitude	"12"
     * longitude	"56"
     * address	"Test address"
     * tele	"0342215168"
     * mobile	"0713578459"
     * photo	"photos/SHOP1.jpg"
     * qrCode	"123456"
     * lastPendingBill	"Test bill record"
     * lastPendingBillDate	"2018-11-01 00:00:00"
     * creditLimit	"100000"
     * chequeReturn	"no"
     * size	"M"
     * ownerName	"Tester"
     * riskScore	"G"
     * status	"inOperation"
     **/
    private String shopID;
    private String repID;
    private String shopCode;
    private String shopName;
    private String email;
    private String latitude;
    private String longitude;
    private String address;
    private String tele;
    private String mobile;
    private String photo;
    private String qrCode;
    private String lastPendingBill;
    private String lastPendingBillDate;
    private String creditLimit;
    private String chequeReturn;
    private String size;
    private String ownerName;
    private String riskScore;
    private String status;

    public Shop() {
    }

    public Shop(String shopID, String repID, String shopCode, String shopName, String email, String latitude, String longitude, String address, String tele, String mobile, String photo, String qrCode, String lastPendingBill, String lastPendingBillDate, String creditLimit, String chequeReturn, String size, String ownerName, String riskScore, String status) {
        this.shopID = shopID;
        this.repID = repID;
        this.shopCode = shopCode;
        this.shopName = shopName;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.tele = tele;
        this.mobile = mobile;
        this.photo = photo;
        this.qrCode = qrCode;
        this.lastPendingBill = lastPendingBill;
        this.lastPendingBillDate = lastPendingBillDate;
        this.creditLimit = creditLimit;
        this.chequeReturn = chequeReturn;
        this.size = size;
        this.ownerName = ownerName;
        this.riskScore = riskScore;
        this.status = status;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getRepID() {
        return repID;
    }

    public void setRepID(String repID) {
        this.repID = repID;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getLastPendingBill() {
        return lastPendingBill;
    }

    public void setLastPendingBill(String lastPendingBill) {
        this.lastPendingBill = lastPendingBill;
    }


    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getChequeReturn() {
        return chequeReturn;
    }

    public void setChequeReturn(String chequeReturn) {
        this.chequeReturn = chequeReturn;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(String riskScore) {
        this.riskScore = riskScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastPendingBillDate() {
        return lastPendingBillDate;
    }

    public void setLastPendingBillDate(String lastPendingBillDate) {
        this.lastPendingBillDate = lastPendingBillDate;
    }
}
