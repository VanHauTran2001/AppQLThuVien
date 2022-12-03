package com.example.quanlythuvien.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Sach implements Serializable, Parcelable {

    private int maSach;
    private int maLoai;
    private String tenSach;
    private String tenTG;
    private int giaThue;
    private int status;

    public Sach(){}

    public Sach(
            int maSach,
            int maLoai,
            String tenSach,
            String tenTG,
            int giaThue,
            int status
    ) {
        this.maSach = maSach;
        this.maLoai = maLoai;
        this.tenSach = tenSach;
        this.tenTG = tenTG;
        this.giaThue = giaThue;
        this.status = status;
    }

    protected Sach(Parcel in) {
        maSach = in.readInt();
        maLoai = in.readInt();
        tenSach = in.readString();
        tenTG = in.readString();
        giaThue = in.readInt();
        status = in.readInt();
    }

    public static final Creator<Sach> CREATOR = new Creator<Sach>() {
        @Override
        public Sach createFromParcel(Parcel in) {
            return new Sach(in);
        }

        @Override
        public Sach[] newArray(int size) {
            return new Sach[size];
        }
    };

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(maSach);
        parcel.writeInt(maLoai);
        parcel.writeString(tenSach);
        parcel.writeString(tenTG);
        parcel.writeInt(giaThue);
        parcel.writeInt(status);
    }
}
