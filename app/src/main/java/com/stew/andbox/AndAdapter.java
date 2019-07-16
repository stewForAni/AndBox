package com.stew.andbox;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndAdapter extends RecyclerView.Adapter<AndAdapter.AndViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<String> data;
    private ItemClickListener listener;
    private StringBuilder builder;

    AndAdapter(Context context, ArrayList<String> data) {
        this.data = data;
        this.context = context;
        builder = new StringBuilder();
    }

    @NonNull
    @Override
    public AndViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new AndViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AndViewHolder andViewHolder, int i) {
        andViewHolder.name.setText(builder.append(i+1).append("-").append(data.get(i)));
        andViewHolder.itemView.setOnClickListener(this);
        andViewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        listener.onClick((Integer) v.getTag());
    }

    class AndViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;

        AndViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClickListener {
        void onClick(int i);
    }

    void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }
}
