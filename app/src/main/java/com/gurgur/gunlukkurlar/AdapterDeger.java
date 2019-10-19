package com.gurgur.gunlukkurlar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterDeger extends RecyclerView.Adapter<AdapterDeger.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView BirimAdi;
        public TextView BirimAlis;
        public TextView BirimSatis;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BirimAdi = itemView.findViewById(R.id.birimadi);
            BirimAlis = itemView.findViewById(R.id.birimalis);
            BirimSatis = itemView.findViewById(R.id.birimsatis);

        }
    }

    private List<ModelDeger> user_list;
    private Context context;

    AdapterDeger(List<ModelDeger> user_list, Context context){
        this.user_list = user_list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDeger.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vr = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deger,parent,false);
        final  ViewHolder view_holder = new ViewHolder(vr);



        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.BirimAdi.setText(user_list.get(position).getBirimAdi());
        holder.BirimAlis.setText(user_list.get(position).getBirimAlis());
        holder.BirimSatis.setText(user_list.get(position).getBirimSatis());




    }

    @Override
    public int getItemCount() {
        return user_list.size();
    }




}