package com.example.quanlythuvien.ui.thu_thu.ql_sach;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlythuvien.databinding.ItemSachBinding;
import com.example.quanlythuvien.model.Sach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder>{
    private ISach iSach;

    public SachAdapter(ISach iSach) {
        this.iSach = iSach;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSachBinding binding = ItemSachBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
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
            holder.binding.txtStatus.setText("Mượn");
        }else {
            holder.binding.txtStatus.setText("Đã được mượn");
        }
    }

    @Override
    public int getItemCount() {
        return iSach.getCount();
    }

    interface ISach{
        int getCount();
        Sach getListSach(int position);
    }
    public class SachViewHolder extends RecyclerView.ViewHolder{
        private ItemSachBinding binding;
        public SachViewHolder(@NonNull ItemSachBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
