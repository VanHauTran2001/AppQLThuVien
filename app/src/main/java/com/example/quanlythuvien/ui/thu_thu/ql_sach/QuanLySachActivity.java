package com.example.quanlythuvien.ui.thu_thu.ql_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityQuanLySachBinding;
import com.example.quanlythuvien.model.Sach;
import com.example.quanlythuvien.model.ThanhVien;
import com.example.quanlythuvien.sqlite.SQLiteHelper;
import com.example.quanlythuvien.ui.thu_thu.them_sach.ThemSachActivity;

import java.util.ArrayList;
import java.util.List;

public class QuanLySachActivity extends AppCompatActivity implements SachAdapter.ISach{

    private ActivityQuanLySachBinding binding;
    private ArrayList<Sach> sachArrayList;
    private SQLiteHelper sqLiteHelper ;
    private SachAdapter sachAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_quan_ly_sach);
        sachArrayList = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        //initSpinner();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Sach1");
        while(data.moveToNext()){
            int maSach = data.getInt(0);
            int maLoai = data.getInt(1);
            String tenSach = data.getString(2);
            String tacGia = data.getString(3);
            int giaThue = data.getInt(4);
            int status = data.getInt(5);
            sachArrayList.add(new Sach(maSach,maLoai,tenSach,tacGia,giaThue,status));
        }
        initRecylerViewSach();
        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLySachActivity.this, ThemSachActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecylerViewSach() {
        sachAdapter = new SachAdapter(this);
        binding.rcvSach.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvSach.setAdapter(sachAdapter);
    }

    @Override
    public int getCount() {
        return sachArrayList.size();
    }

    @Override
    public Sach getListSach(int position) {
        return sachArrayList.get(position);
    }

//    private void initSpinner(){
//        Cursor data = sqLiteHelper.GetData("SELECT * FROM LoaiSach");
//        while(data.moveToNext()){
//            String tensach = data.getString(1);
//            listLoaiSach.add(tensach);
//        }
//        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listLoaiSach);
//        binding.spinner.setAdapter(spinnerAdapter);
//
//        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                String msg = listLoaiSach.get(position);
//                Toast.makeText(QuanLySachActivity.this, msg, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
}