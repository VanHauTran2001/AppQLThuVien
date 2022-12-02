package com.example.quanlythuvien.ui.thu_thu.ql_theloai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityQuanLyTheLoaiBinding;
import com.example.quanlythuvien.sqlite.SQLiteHelper;

public class QuanLyTheLoaiActivity extends AppCompatActivity {

    private ActivityQuanLyTheLoaiBinding binding;
    private String tenLoai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_quan_ly_the_loai);
        binding.btnThemTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tenLoai = binding.edTenTheLoai.getText().toString().trim();
                int checkExsits = 0;
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
                Cursor data = sqLiteHelper.GetData("SELECT * FROM LoaiSach");
                while(data.moveToNext()){
                    String tensach = data.getString(1);
                    if(tensach.contains(tenLoai)){
                        checkExsits++;
                    }
                }
                if(checkExsits == 0){
                    sqLiteHelper.QueryData("INSERT INTO LoaiSach VALUES(null,'" + tenLoai + "')");
                    Toast.makeText(QuanLyTheLoaiActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    binding.edTenTheLoai.setText("");
                }else {
                    Toast.makeText(QuanLyTheLoaiActivity.this, "Đã tồn tại !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}