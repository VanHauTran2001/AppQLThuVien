package com.example.quanlythuvien.ui.thanh_vien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quanlythuvien.databinding.ItemSachThanhVienBinding;
import com.example.quanlythuvien.model.Sach;

public class SachThanhVienAdapter extends RecyclerView.Adapter<SachThanhVienAdapter.SachViewHolder>{
    private ISachThanhVien iSach;

    public SachThanhVienAdapter(ISachThanhVien iSach) {
        this.iSach = iSach;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSachThanhVienBinding binding = ItemSachThanhVienBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SachViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        Sach sach = iSach.getListSach(position);
        holder.binding.txtTenSach.setText("Tên sách : " + sach.getTenSach());
        holder.binding.txtTacGia.setText("Tác giả : " + sach.getTenTG());
        holder.binding.txtTheLoai.setText("Mã loại : " + sach.getMaLoai());
        holder.binding.txtGia.setText("Giá thuê :" + sach.getGiaThue());
        int status = sach.getStatus();
        if (status==0){
            holder.binding.txtStatus.setText("Còn");
        }else {
            holder.binding.txtStatus.setText("Hết");
        }
        holder.binding.btnThueSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSach.onCLickMuonSach(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iSach.getCount();
    }

    interface ISachThanhVien{
        int getCount();
        Sach getListSach(int position);
        void onCLickMuonSach(int position);
    }
    public class SachViewHolder extends RecyclerView.ViewHolder{
        private ItemSachThanhVienBinding binding;
        public SachViewHolder(@NonNull ItemSachThanhVienBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
