package com.example.quanlythuvien.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityThanhVienLoginBinding;
import com.example.quanlythuvien.model.ThanhVien;
import com.example.quanlythuvien.sqlite.SQLiteHelper;
import com.example.quanlythuvien.ui.thanh_vien.ThanhVienActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class ThanhVienLoginActivity extends AppCompatActivity {
    private ActivityThanhVienLoginBinding binding;
    private ArrayList<ThanhVien> thanhVienArrayList;
    private SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_thanh_vien_login);
        thanhVienArrayList = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        Cursor data = sqLiteHelper.GetData("SELECT * FROM User");
        while(data.moveToNext()){
            String tk = data.getString(1);
            String mk = data.getString(2);
            String hoten = data.getString(3);
            int namSinh = data.getInt(4);
            thanhVienArrayList.add(new ThanhVien(tk,mk,hoten,String.valueOf(namSinh)));
        }
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String userName = binding.edTaiKhoan.getText().toString().trim();
        String passWord = binding.edMatKhau.getText().toString().trim();
        for (int i=0;i<thanhVienArrayList.size();i++){
            if (userName.equals(thanhVienArrayList.get(i).getTaiKhoan()) && passWord.equals(thanhVienArrayList.get(i).getPassword())){
                Intent intent = new Intent(ThanhVienLoginActivity.this, ThanhVienActivity.class);
                intent.putExtra("thanhvien", (Serializable) thanhVienArrayList.get(i));
                startActivity(intent);
                Toast.makeText(this, "Đăng nhập thành công !!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không chính xác !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}