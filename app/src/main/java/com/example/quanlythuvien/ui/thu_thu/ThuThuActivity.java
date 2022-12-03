package com.example.quanlythuvien.ui.thu_thu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityThuThuBinding;
import com.example.quanlythuvien.ui.login.LoginActivity;
import com.example.quanlythuvien.ui.thu_thu.ql_sach.QuanLySachActivity;
import com.example.quanlythuvien.ui.thu_thu.ql_theloai.QuanLyTheLoaiActivity;
import com.example.quanlythuvien.ui.thu_thu.sach_sap_het_han.SachSapHetHanActivity;

public class ThuThuActivity extends AppCompatActivity {

    private ActivityThuThuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_thu_thu);

        binding.btnQuanLySach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuThuActivity.this, QuanLySachActivity.class);
                startActivity(intent);
            }
        });

        binding.btnQuanLyDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuThuActivity.this, QuanLyTheLoaiActivity.class);
                startActivity(intent);
            }
        });
        binding.btnSachDaQuaHan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuThuActivity.this, SachQuaHanActivity.class);
                startActivity(intent);
            }
        });
        binding.btnSachSapHetHan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuThuActivity.this, SachSapHetHanActivity.class);
                startActivity(intent);
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuThuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}