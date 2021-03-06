package com.spark.bipaywallet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spark.bipaywallet.R;
import com.spark.bipaywallet.entity.Wallet;

import java.util.List;

public class SwitchWalletAdapter extends RecyclerView.Adapter<SwitchWalletAdapter.MyHolder> {
    private Context context;
    private List<Wallet> list;
    private int selectItem = -1;

    private AdapterView.OnItemClickListener itemClickListener;

    public SwitchWalletAdapter(Context context, List<Wallet> stringList) {
        this.context = context;
        this.list = stringList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = li.inflate(R.layout.item_switch_wallet, null);
        convertView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new MyHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.tvName.setText(list.get(position).getName());

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(null, null, position, 0);
                    select(position);
                }
            }
        });

        if (position == selectItem) {
            holder.tvName.setSelected(true);
            holder.ivChecked.setVisibility(View.VISIBLE);
        } else {
            holder.tvName.setSelected(false);
            holder.ivChecked.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView ivChecked;
        private LinearLayout llItem;

        public MyHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivChecked = itemView.findViewById(R.id.ivChecked);
            llItem = itemView.findViewById(R.id.llItem);
        }
    }

    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void select(int pos) {
        int lastPos = selectItem;
        selectItem = pos;
        notifyItemChanged(pos);
        notifyItemChanged(lastPos);
    }
}
