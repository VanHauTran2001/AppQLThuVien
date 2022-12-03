package com.example.quanlythuvien.ui.thu_thu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ActivitySachQuaHanBinding;
import com.example.quanlythuvien.model.PhieuMuon;
import com.example.quanlythuvien.model.Sach;
import com.example.quanlythuvien.model.ThanhVien;
import com.example.quanlythuvien.sqlite.SQLiteHelper;
import com.example.quanlythuvien.ui.thu_thu.ql_sach.SachHetHanAdapter;
import com.example.quanlythuvien.ui.thu_thu.sach_sap_het_han.SachSapHetHanActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SachQuaHanActivity extends AppCompatActivity implements SachHetHanAdapter.ISachHetHan {

    private ActivitySachQuaHanBinding binding;
    private SQLiteHelper sqLiteHelper ;
    private List<PhieuMuon> listPhieuMuon;
    private List<PhieuMuon> listSachQuaHan;
    private SachHetHanAdapter sachAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sach_qua_han);
        sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        getPhieuMuon();
        getSachQuaHan();
        initRecyclerView();
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SachQuaHanActivity.this, ThuThuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        sachAdapter = new SachHetHanAdapter( this);
        binding.rvSachQuaHan.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSachQuaHan.setAdapter(sachAdapter);
    }

    private void getSachQuaHan() {
        listSachQuaHan = new ArrayList<>();
        for (PhieuMuon pm : listPhieuMuon) {
            SimpleDateFormat format  = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                Date date1 = c1.getTime();
                Date date2 = format.parse(pm.getNgayTra());
                c1.setTime(date1);
                c2.setTime(date2);
                long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
                if(noDay < 0){
                    listSachQuaHan.add(pm);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void getPhieuMuon(){
        listPhieuMuon = new ArrayList<>();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM PhieuMuon");
        while(data.moveToNext()){
            int maPhieu = data.getInt(0);
            String maTV = data.getString(1);
            int maSach = data.getInt(2);
            String maTT = data.getString(3);
            String ngayThue = data.getString(4);
            String ngayTra = data.getString(5);
            int giaThue = data.getInt(6);
            Cursor dataTV = sqLiteHelper.GetData("SELECT * FROM User WHERE taiKhoan='"+maTV+"'");
            ThanhVien tv = new ThanhVien();
            while(dataTV.moveToNext()){
                String taiKhoan = dataTV.getString(1);
                String matKhau  = dataTV.getString(2);
                String hoTen = dataTV.getString(3);
                String namSinh = dataTV.getString(4);
                tv = new ThanhVien(taiKhoan,matKhau,hoTen,namSinh);
            }
            Cursor dataSach = sqLiteHelper.GetData("SELECT * FROM Sach1 WHERE MaSach='"+maSach+"'");
            Sach s = new Sach();
            while(dataSach.moveToNext()){
                int ms = dataSach.getInt(0);
                int maLoai  = dataSach.getInt(1);
                String tenSach = dataSach.getString(2);
                String tenTG = dataSach.getString(3);
                int giaThua = dataSach.getInt(4);
                int status = dataSach.getInt(5);
                s = new Sach(ms,maLoai,tenSach,tenTG,giaThua,status);
            }
            listPhieuMuon.add(new PhieuMuon(maPhieu,tv,s,maTT,ngayThue,ngayTra,giaThue));
        }
    }

    @Override
    public int getCount() {
        return listSachQuaHan.size();
    }

    @Override
    public PhieuMuon getPhieu(int position) {
        return listSachQuaHan.get(position);
    }

    @Override
    public void onClickSendEmail(int position) {
        buttonOpenDialogClicked();
    }
    private void buttonOpenDialogClicked()  {
        CustomDialog.FullNameListener listener = new CustomDialog.FullNameListener() {
            @Override
            public void fullNameEntered(String fullName) {
                Toast.makeText(SachQuaHanActivity.this, "Gửi email thành công đến địa chỉ : " + fullName, Toast.LENGTH_LONG).show();
            }
        };
        final CustomDialog dialog = new CustomDialog(this, listener);
        dialog.show();
    }
}