package com.example.quanlythuvien.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityThuThuLogInBinding;
import com.example.quanlythuvien.model.ThanhVien;
import com.example.quanlythuvien.sqlite.SQLiteHelper;
import com.example.quanlythuvien.ui.thu_thu.ThuThuActivity;

import java.util.ArrayList;

public class ThuThuLogInActivity extends AppCompatActivity {
    private ActivityThuThuLogInBinding binding;
    private String taiKhoan;
    private String matKhau;
    private String taiKhoanAdMin = "admin";
    private String matKhauAdMin = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_thu_thu_log_in);
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhap();
            }
        });
    }
    private void dangNhap() {
        taiKhoan = binding.edTaiKhoan.getText().toString().trim();
        matKhau = binding.edMatKhau.getText().toString().trim();
        if(taiKhoan.equals(taiKhoanAdMin) && matKhau.equals(matKhauAdMin)){
            Intent intent = new Intent(ThuThuLogInActivity.this, ThuThuActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        }else {

            Toast.makeText(this, "Nhập sai tài khoản !", Toast.LENGTH_SHORT).show();
        }
    }
}