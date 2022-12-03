package com.example.quanlythuvien.ui.thu_thu.ql_sach;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ItemSachBinding;
import com.example.quanlythuvien.model.Sach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder>{
    private ISach iSach;
    private Context context;

    public SachAdapter(ISach iSach, Context context) {
        this.iSach = iSach;
        this.context = context;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSachBinding binding = ItemSachBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SachViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, @SuppressLint("RecyclerView") int position) {
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
        holder.binding.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSach.onClickEdit(position);
            }
        });
        holder.binding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSach.onClickDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iSach.getCount();
    }

    interface ISach{
        int getCount();
        Sach getListSach(int position);
        void onClickDelete(int position);
        void onClickEdit(int position);
    }
    public class SachViewHolder extends RecyclerView.ViewHolder{
        private ItemSachBinding binding;
        public SachViewHolder(@NonNull ItemSachBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
