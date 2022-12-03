package com.example.quanlythuvien.ui.thanh_vien.xac_nhan_muon_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivityMuonSachBinding;
import com.example.quanlythuvien.model.Sach;
import com.example.quanlythuvien.model.ThanhVien;
import com.example.quanlythuvien.sqlite.SQLiteHelper;
import com.example.quanlythuvien.ui.thanh_vien.ThanhVienActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MuonSachActivity extends AppCompatActivity {

    private ActivityMuonSachBinding binding;
    private ThanhVien thanhVien;
    private Sach sach;
    private String dat;
    private SQLiteHelper sqLiteHelper ;
    private String ngayTra = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_muon_sach);
        sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        sach = (Sach) getIntent().getSerializableExtra("sach");
        thanhVien = (ThanhVien) getIntent().getSerializableExtra("thanhvien");
        getDay();
        binding.tvTenTV.setText(thanhVien.getHoten());
        binding.tvTenSach.setText(sach.getTenSach());
        binding.tvTongTien.setText(sach.getGiaThue() + " đ");
        binding.tvNgayMuon.setText(dat);

        binding.tvNgayTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH) + 1;
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MuonSachActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        ngayTra = i2 +"/"+ (i1+1) + "/" + i;
                        binding.tvNgayTra.setText(ngayTra);
                    }
                },year,month,day);
                dialog.show();
            }
        });

        binding.btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matt = "admin";
                sqLiteHelper.QueryData("INSERT INTO PhieuMuon VALUES(null,'" + thanhVien.getTaiKhoan() + "','" + sach.getMaSach() + "','"+matt+"','"+dat+"','" + ngayTra +"','" + sach.getGiaThue() +"')");
                sqLiteHelper.QueryData("UPDATE Sach1 SET status ='1' WHERE MaSach = '"+ sach.getMaSach() +"'");
                Toast.makeText(MuonSachActivity.this, "Đặt sách thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MuonSachActivity.this, ThanhVienActivity.class);
                intent.putExtra("thanhvien",thanhVien);
                startActivity(intent);
            }
        });
    }

    public void getDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dat = sdf.format(date);
    }
}