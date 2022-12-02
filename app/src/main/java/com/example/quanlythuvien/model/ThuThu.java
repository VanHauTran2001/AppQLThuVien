package com.example.quanlythuvien.model;

public class ThuThu {

    private String maTT;
    private String password;
    private String hoTen;

    public ThuThu(String maTT, String password, String hoTen) {
        this.maTT = maTT;
        this.password = password;
        this.hoTen = hoTen;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
