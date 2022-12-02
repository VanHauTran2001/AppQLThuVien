package com.example.quanlythuvien.ui.thu_thu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityThuThuBinding;
import com.example.quanlythuvien.ui.thu_thu.ql_sach.QuanLySachActivity;
import com.example.quanlythuvien.ui.thu_thu.ql_theloai.QuanLyTheLoaiActivity;

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
    }
}