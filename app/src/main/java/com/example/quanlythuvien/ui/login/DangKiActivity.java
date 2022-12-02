package com.example.quanlythuvien.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quanlythuvien.R;

import com.example.quanlythuvien.databinding.ActivityDangKiBinding;
import com.example.quanlythuvien.sqlite.SQLiteHelper;

public class DangKiActivity extends AppCompatActivity {

    private ActivityDangKiBinding binding;
    private String taiKhoan;
    private String matKhau;
    private String hoTen;
    private String namSinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = DataBindingUtil.setContentView(this,R.layout.activity_dang_ki);

        binding.btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taiKhoan = binding.edTaiKhoan.getText().toString().trim();
                matKhau = binding.edMatKhau.getText().toString().trim();
                hoTen = binding.edTen.getText().toString().trim();
                namSinh = binding.edNamSinh.getText().toString().trim();
                if(taiKhoan.isEmpty() || matKhau.isEmpty() || hoTen.isEmpty() || namSinh.isEmpty()){
                    showToast("Thiếu thông tin !");
                }else if(matKhau.length() < 6){
                    showToast("Mật khẩu quá ngắn !");
                }else {
                    int checkExsits = 0;
                    SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
                    Cursor data = sqLiteHelper.GetData("SELECT * FROM User");
                    while(data.moveToNext()){
                        String tk = data.getString(1);
                        if(tk.contains(taiKhoan)){
                            checkExsits++;
                        }
                    }
                    if(checkExsits == 0){
                        int year = Integer.parseInt(namSinh);
                        sqLiteHelper.QueryData("INSERT INTO User VALUES(null,'" + taiKhoan + "','" + matKhau + "','"+hoTen+"','" + year +"')");
                        showToast("Đăng kí thành công !");
                        Intent intent = new Intent(DangKiActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else {
                        showToast("Tài khoản đã tồn tại !");
                    }
                }
            }
        });

    }

    private void showToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}