package com.example.quanlythuvien.ui.thu_thu.sua_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivitySuaSachBinding;
import com.example.quanlythuvien.model.Sach;
import com.example.quanlythuvien.sqlite.SQLiteHelper;
import com.example.quanlythuvien.ui.thu_thu.ql_sach.QuanLySachActivity;

import java.util.ArrayList;
import java.util.List;

public class SuaSachActivity extends AppCompatActivity {

    private ActivitySuaSachBinding binding;
    private String tenSach;
    private String tenTacGia;
    private String giaTien;
    private List<String> listLoaiSach;
    private SQLiteHelper sqLiteHelper ;
    private int maTheLoai ;
    private Sach sach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sua_sach);
        listLoaiSach = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        initSpinner();
        sach = (Sach) getIntent().getSerializableExtra("sach");
        binding.edTenTheSach.setText(sach.getTenSach());
        binding.edTenTacGia.setText(sach.getTenTG());
        binding.edGiaTien.setText(String.valueOf(sach.getGiaThue()));
        binding.btnSuaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaSach();
            }
        });
    }

    private void initSpinner() {
        Cursor data = sqLiteHelper.GetData("SELECT * FROM LoaiSach");
        while(data.moveToNext()){
            String tenloai = data.getString(1);
            listLoaiSach.add(tenloai);
        }
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listLoaiSach);
        binding.spinner.setAdapter(spinnerAdapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String msg = listLoaiSach.get(position);
                Cursor data = sqLiteHelper.GetData("SELECT * FROM LoaiSach");
                while(data.moveToNext()){
                    int ms = data.getInt(0);
                    String tenLoai = data.getString(1);
                    if(tenLoai.contains(msg)){
                        maTheLoai = ms;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void suaSach(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        tenSach = binding.edTenTheSach.getText().toString().trim();
        tenTacGia = binding.edTenTacGia.getText().toString().trim();
        giaTien = binding.edGiaTien.getText().toString().trim();
        if(tenSach.isEmpty() || tenTacGia.isEmpty() || giaTien.isEmpty()){
            Toast.makeText(this, "Thiếu thông tin !", Toast.LENGTH_SHORT).show();
        }else {
            int gt = Integer.parseInt(giaTien);
            sqLiteHelper.QueryData("UPDATE Sach1 SET MaLoai ='"+maTheLoai+"', TenSach = '"+tenSach+"', TenTG = '"+tenTacGia+"', GiaThue = '"+gt+"' WHERE MaSach = '"+ sach.getMaSach() +"'");
            Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SuaSachActivity.this, QuanLySachActivity.class));
        }
    }
}