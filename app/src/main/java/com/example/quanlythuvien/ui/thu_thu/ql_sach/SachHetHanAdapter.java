package com.example.quanlythuvien.ui.thu_thu.ql_sach;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quanlythuvien.databinding.ItemPhieuQuaHanBinding;
import com.example.quanlythuvien.model.PhieuMuon;

public class SachHetHanAdapter extends RecyclerView.Adapter<SachHetHanAdapter.SachViewHolder>{
    private ISachHetHan iSach;

    public SachHetHanAdapter(ISachHetHan iSach) {
        this.iSach = iSach;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPhieuQuaHanBinding binding = ItemPhieuQuaHanBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SachViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        PhieuMuon phieu = iSach.getPhieu(position);
        holder.binding.txtTenSach.setText("Tên sách :" + phieu.getSach().getTenSach());
        holder.binding.txtNguoiMuon.setText("Người mượn :"+phieu.getThanhVien().getHoten());
        holder.binding.txtNgayMuon.setText("Ngày mượn :"+phieu.getNgayMuon());
        holder.binding.txtNgayTra.setText("Ngày trả :"+phieu.getNgayTra());
        holder.binding.btnGuiMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSach.onClickSendEmail(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iSach.getCount();
    }

    public interface ISachHetHan{
        int getCount();
        PhieuMuon getPhieu(int position);
        void onClickSendEmail(int position);
    }

    public class SachViewHolder extends RecyclerView.ViewHolder{
        private ItemPhieuQuaHanBinding binding;
        public SachViewHolder(@NonNull ItemPhieuQuaHanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
