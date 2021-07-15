package com.signalpancakeswap.signalpancakeswap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.signalpancakeswap.signalpancakeswap.R;
import com.signalpancakeswap.signalpancakeswap.models.signal.getSignalModel;


import java.util.ArrayList;
import java.util.List;

public class CustomAdapterSignal extends RecyclerView.Adapter<CustomAdapterSignal.viewHolderSignal> {
    Context context;
    List<getSignalModel> list = new ArrayList<>();

    public CustomAdapterSignal(Context context, List<getSignalModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolderSignal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderSignal(LayoutInflater.from(context).inflate(R.layout.item_signal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterSignal.viewHolderSignal holder, int position) {
        getSignalModel item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.type.setText(item.getType());
        holder.price.setText(item.getPrice());
        holder.growth.setText(item.getGrowth());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderSignal extends RecyclerView.ViewHolder {
        TextView title,type,price,growth;
        public viewHolderSignal(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            price = itemView.findViewById(R.id.price);
            growth = itemView.findViewById(R.id.growth);
        }
    }
}
