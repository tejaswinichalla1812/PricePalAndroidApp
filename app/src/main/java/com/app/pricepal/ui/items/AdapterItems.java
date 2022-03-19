package com.app.pricepal.ui.items;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.pricepal.R;
import com.app.pricepal.models.items_model;

import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<items_model> itemsList;

    public AdapterItems(Context mContext, List<items_model> itemsList) {
        this.mContext = mContext;
        this.itemsList = itemsList;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(List<items_model> filterList) {
        itemsList = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final items_model item = itemsList.get(position);
        viewHolder.tvItemName.setText(item.getItemName());
        viewHolder.tvStoreName.setText(item.getStoreId()+"");
        viewHolder.tvItemQty.setText(item.getItemQty());
        viewHolder.tvItemPrice.setText("$ "+item.getItemPrice());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tvItemName;
        public TextView tvStoreName;
        public TextView tvItemQty;
        public TextView tvItemPrice;
        public ImageView tvItemImg;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tvItemName = rootView.findViewById(R.id.tvItemName);
            this.tvStoreName = rootView.findViewById(R.id.tvStoreName);
            this.tvItemPrice = rootView.findViewById(R.id.tvPrice);
            this.tvItemQty = rootView.findViewById(R.id.tvItemQty);
            this.tvItemImg = rootView.findViewById(R.id.itemImg);
        }
    }
}
