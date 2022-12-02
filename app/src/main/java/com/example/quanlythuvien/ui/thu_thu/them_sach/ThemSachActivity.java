package com.example.quanlythuvien.ui.thu_thu.them_sach;

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
import com.example.quanlythuvien.databinding.ActivityThemSachBinding;
import com.example.quanlythuvien.sqlite.SQLiteHelper;
import com.example.quanlythuvien.ui.thu_thu.ql_sach.QuanLySachActivity;

import java.util.ArrayList;
import java.util.List;

public class ThemSachActivity extends AppCompatActivity {

    private ActivityThemSachBinding binding;
    private String tenSach;
    private String tenTacGia;
    private String giaTien;
    private List<String> listLoaiSach;
    private SQLiteHelper sqLiteHelper ;
    private int maTheLoai ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_them_sach);
        listLoaiSach = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        initSpinner();
        binding.btnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themSach();
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(ThemSachActivity.this, QuanLySachActivity.class));
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

    private  void themSach(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        tenSach = binding.edTenTheSach.getText().toString().trim();
        tenTacGia = binding.edTenTacGia.getText().toString().trim();
        giaTien = binding.edGiaTien.getText().toString().trim();
        if(tenSach.isEmpty() || tenTacGia.isEmpty() || giaTien.isEmpty()){
            Toast.makeText(this, "Thiếu thông tin !", Toast.LENGTH_SHORT).show();
        }else {
            int gt = Integer.parseInt(giaTien);
            sqLiteHelper.QueryData("INSERT INTO Sach1 VALUES(null,'" + maTheLoai + "','" + tenSach + "','"+tenTacGia+"','" + gt +"','0')");
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            binding.edTenTheSach.setText("");
            binding.edTenTacGia.setText("");
            binding.edGiaTien.setText("");
        }
    }
}