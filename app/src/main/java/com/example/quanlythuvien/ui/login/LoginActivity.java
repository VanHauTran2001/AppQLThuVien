package com.example.quanlythuvien.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityLoginBinding;
import com.example.quanlythuvien.sqlite.SQLiteHelper;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        eventClick();
        creatable();
    }

    private void eventClick(){
        binding.btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,DangKiActivity.class);
                startActivity(intent);
            }
        });
        binding.btnThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ThanhVienLoginActivity.class));
            }
        });
        binding.btnThuThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ThuThuLogInActivity.class);
                startActivity(intent);
            }
        });
    }
    private void creatable(){
        sqLiteHelper = new SQLiteHelper(this,"Data.sqlite",null,5);
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS User(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "taiKhoan VARCHAR(100)," +
                "matKhau VARCHAR(100)," +
                "hoTen NVARCHAR(100)," +
                "namSinh INTEGER)");
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS LoaiSach(MaLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai NVARCHAR(100))");
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS Sach1(MaSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MaLoai INTEGER," +
                "TenSach NVARCHAR(100)," +
                "TenTG NVARCHAR(100)," +
                "GiaThue INTEGER," +
                "status INTEGER)");
    }
}