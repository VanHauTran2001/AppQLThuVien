package com.example.quanlythuvien.model;

public class PhieuMuon {

    private int maPM;
    private ThanhVien thanhVien;
    private Sach sach;
    private String maTT;
    private String ngayMuon;
    private String ngayTra;
    private int tienThue;

    public PhieuMuon(){}

    public PhieuMuon(int maPM, ThanhVien thanhVien, Sach sach, String maTT, String ngayMuon, String ngayTra, int tienThue) {
        this.maPM = maPM;
        this.thanhVien = thanhVien;
        this.sach = sach;
        this.maTT = maTT;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.tienThue = tienThue;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }
}
