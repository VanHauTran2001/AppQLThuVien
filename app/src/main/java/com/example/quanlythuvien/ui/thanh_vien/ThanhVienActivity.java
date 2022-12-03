package com.example.quanlythuvien.ui.thanh_vien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityThanhVienBinding;
import com.example.quanlythuvien.model.Sach;
import com.example.quanlythuvien.model.ThanhVien;
import com.example.quanlythuvien.sqlite.SQLiteHelper;
import com.example.quanlythuvien.ui.login.LoginActivity;
import com.example.quanlythuvien.ui.thanh_vien.xac_nhan_muon_sach.MuonSachActivity;
import com.example.quanlythuvien.ui.thu_thu.ql_sach.QuanLySachActivity;
import com.example.quanlythuvien.ui.thu_thu.ql_sach.SachAdapter;
import com.example.quanlythuvien.ui.thu_thu.sua_sach.SuaSachActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ThanhVienActivity extends AppCompatActivity implements SachThanhVienAdapter.ISachThanhVien {

    private ActivityThanhVienBinding binding;
    private ArrayList<Sach> sachArrayList;
    private SQLiteHelper sqLiteHelper ;
    private SachThanhVienAdapter sachAdapter;
    private List<String> listLoaiSach;
    private int maTheLoai = 1;
    private String searchStr = "";
    private ThanhVien thanhVien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_thanh_vien);
        thanhVien = (ThanhVien) getIntent().getSerializableExtra("thanhvien");
        sachArrayList = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        listLoaiSach = new ArrayList<>();
        onSearch();
        initSpinner();
        getDataSachAll();
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(ThanhVienActivity.this, LoginActivity.class));
            }
        });
    }

    private void onSearch() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchStr = s;
                sachArrayList.clear();
                getDataSachBySearch(s);
                binding.rcvSach.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
    }

    private void getDataSachBySearch(String str){
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Sach1 WHERE TenSach LIKE '%"+str+"%' AND status = '0'");
        while(data.moveToNext()){
            int maSach = data.getInt(0);
            int maLoai = data.getInt(1);
            String tenSach = data.getString(2);
            String tacGia = data.getString(3);
            int giaThue = data.getInt(4);
            int status = data.getInt(5);
            sachArrayList.add(new Sach(maSach,maLoai,tenSach,tacGia,giaThue,status));
        }
    }

    private void getDataSachAll(){
        sachArrayList.clear();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Sach1 WHERE status = '0'");
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
    }


    private void getDataSach(){
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Sach1 WHERE MaLoai='"+maTheLoai+"' AND status = '0'");
        while(data.moveToNext()){
            int maSach = data.getInt(0);
            int maLoai = data.getInt(1);
            String tenSach = data.getString(2);
            String tacGia = data.getString(3);
            int giaThue = data.getInt(4);
            int status = data.getInt(5);
            sachArrayList.add(new Sach(maSach,maLoai,tenSach,tacGia,giaThue,status));
        }
    }

    private void initRecylerViewSach() {
        sachAdapter = new SachThanhVienAdapter(this);
        binding.rcvSach.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvSach.setAdapter(sachAdapter);
    }

    private void initSpinner(){
        Cursor data = sqLiteHelper.GetData("SELECT * FROM LoaiSach");
        while(data.moveToNext()){
            String tensach = data.getString(1);
            listLoaiSach.add(tensach);
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
                sachArrayList.clear();
                getDataSach();
                binding.rcvSach.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public int getCount() {
        return sachArrayList.size();
    }

    @Override
    public Sach getListSach(int position) {
        return sachArrayList.get(position);
    }

    @Override
    public void onCLickMuonSach(int position) {
        Sach sach = sachArrayList.get(position);
        Intent intent = new Intent(ThanhVienActivity.this, MuonSachActivity.class);
        intent.putExtra("sach", (Serializable) sach);
        intent.putExtra("thanhvien", (Serializable) thanhVien);
        startActivity(intent);
    }
}