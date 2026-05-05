package com.example.utspemrograman_piranti_bergerak;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder> {

    private Context context;
    private ArrayList<Siswa> listSiswa;
    private OnItemLongClickListener longClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClick(Siswa siswa);
    }

    public SiswaAdapter(Context context, ArrayList<Siswa> listSiswa, OnItemLongClickListener longClickListener) {
        this.context = context;
        this.listSiswa = listSiswa;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public SiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_siswa, parent, false);
        return new SiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaViewHolder holder, int position) {
        Siswa siswa = listSiswa.get(position);
        holder.tvNama.setText(siswa.getNama());
        holder.tvNim.setText("NIM: " + siswa.getNim());
        holder.tvNilai.setText(String.valueOf(siswa.getNilai()));

        if (siswa.getFoto() != null && !siswa.getFoto().isEmpty()) {
            holder.ivFoto.setImageURI(Uri.parse(siswa.getFoto()));
        } else {
            holder.ivFoto.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onItemLongClick(siswa);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listSiswa.size();
    }

    static class SiswaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvNama, tvNim, tvNilai;

        public SiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFotoRow);
            tvNama = itemView.findViewById(R.id.tvNamaRow);
            tvNim = itemView.findViewById(R.id.tvNimRow);
            tvNilai = itemView.findViewById(R.id.tvNilaiRow);
        }
    }
}
