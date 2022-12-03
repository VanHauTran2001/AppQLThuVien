package com.example.quanlythuvien.ui.thu_thu.ql_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityQuanLySachBinding;
import com.example.quanlythuvien.model.Sach;
import com.example.quanlythuvien.sqlite.SQLiteHelper;
import com.example.quanlythuvien.ui.thu_thu.ThuThuActivity;
import com.example.quanlythuvien.ui.thu_thu.sua_sach.SuaSachActivity;
import com.example.quanlythuvien.ui.thu_thu.them_sach.ThemSachActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuanLySachActivity extends AppCompatActivity implements SachAdapter.ISach{

    private ActivityQuanLySachBinding binding;
    private ArrayList<Sach> sachArrayList;
    private SQLiteHelper sqLiteHelper ;
    private SachAdapter sachAdapter;
    private List<String> listLoaiSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_quan_ly_sach);
        sachArrayList = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        listLoaiSach = new ArrayList<>();
  //      initSpinner();
        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLySachActivity.this, ThemSachActivity.class);
                startActivity(intent);
            }
        });
        getDataSach();
        initRecylerViewSach();
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                doOnDifficultyLevelChanged(radioGroup, i);
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLySachActivity.this, ThuThuActivity.class));
            }
        });
    }

    private void doOnDifficultyLevelChanged(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();
        if(checkedRadioId== R.id.rdTatCa) {
            sachArrayList.clear();
            getDataSach();
            initRecylerViewSach();
        } else if(checkedRadioId== R.id.rdSachCon ) {
            sachArrayList.clear();
            getDataSachByStatus(0);
            binding.rcvSach.getAdapter().notifyDataSetChanged();
        } else if(checkedRadioId== R.id.rdDangChoThue) {
            sachArrayList.clear();
            getDataSachByStatus(1);
            binding.rcvSach.getAdapter().notifyDataSetChanged();
        }
    }

    private void getDataSach(){
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
    }

    private void getDataSachByStatus(int status){
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Sach1 WHERE status ='"+status+"'");
        while(data.moveToNext()){
            int maSach = data.getInt(0);
            int maLoai = data.getInt(1);
            String tenSach = data.getString(2);
            String tacGia = data.getString(3);
            int giaThue = data.getInt(4);
            int stt = data.getInt(5);
            sachArrayList.add(new Sach(maSach,maLoai,tenSach,tacGia,giaThue,stt));
        }
    }

    private void initRecylerViewSach() {
        sachAdapter = new SachAdapter(this,this);
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

    @Override
    public void onClickDelete(int position) {
        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có xác nhận xóa sách này ?")
                .setPositiveButton("Có", (dialog, which) -> {
                      Sach sach = sachArrayList.get(position);
                      int maSach = sach.getMaSach();
                      sqLiteHelper.QueryData("DELETE FROM Sach1 WHERE MaSach = '"+ maSach +"'");
                      Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                      sachArrayList.clear();
                      getDataSach();
                      binding.rcvSach.getAdapter().notifyDataSetChanged();
                })
                .setNegativeButton("Không", (dialog, which) -> {

                })
                .create();
        alertDialog.show();
    }

    @Override
    public void onClickEdit(int position) {
        Sach sach = sachArrayList.get(position);
        Intent intent = new Intent(QuanLySachActivity.this, SuaSachActivity.class);
        intent.putExtra("sach", (Serializable) sach);
        startActivity(intent);
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

    @Override
    protected void onResume() {
        super.onResume();
        initRecylerViewSach();
    }
}