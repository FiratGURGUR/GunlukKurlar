package com.gurgur.gunlukkurlar;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterDeger extends RecyclerView.Adapter<AdapterDeger.ViewHolder> {
    String charString="";
    String searchString="";
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
    public static List<ModelDeger> user_listfiltered;
    private Context context;

    AdapterDeger(List<ModelDeger> user_list, Context context){
        this.user_list = user_list;
        this.user_listfiltered = user_list;
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

        holder.BirimAdi.setText(user_listfiltered.get(position).getBirimAdi());
        holder.BirimAlis.setText( "Alış: "+user_listfiltered.get(position).getBirimAlis());
        holder.BirimSatis.setText("Satış: "+user_listfiltered.get(position).getBirimSatis());


        String name = user_listfiltered.get(position).getBirimAdi().toLowerCase(Locale.getDefault());

        if (name.contains(searchString)) {

            int startPos = name.indexOf(searchString);
            int endPos = startPos + searchString.length();

            Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.BirimAdi.getText());
            spanString.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.BirimAdi.setText(spanString);
        }


    }

    @Override
    public int getItemCount() {
        return user_listfiltered.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                charString = charSequence.toString();
                searchString = charString;
                if (charString.isEmpty()) {
                    user_listfiltered = user_list;
                } else {
                    List<ModelDeger> filteredList = new ArrayList<>();
                    for (ModelDeger row : user_list) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getBirimAdi().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    user_listfiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = user_list;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                user_list = (ArrayList<ModelDeger>) filterResults.values;
                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }


}