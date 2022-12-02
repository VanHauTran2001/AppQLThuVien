package com.example.quanlythuvien.model;

public class ThanhVien {

    private String taiKhoan;
    private String password;
    private String hoten;
    private String namSinh;

    public ThanhVien(String taiKhoan, String password, String hoten, String namSinh) {
        this.taiKhoan = taiKhoan;
        this.password = password;
        this.hoten = hoten;
        this.namSinh = namSinh;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
