package seoullost.seoullost_android;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LostListAdapter extends RecyclerView.Adapter<LostListAdapter.ViewHolder> {
    private List<Item> lostList;

    public LostListAdapter(List<Item> lostList) {
        this.lostList = lostList;
    }


    public LostListAdapter() {
        this.lostList = new ArrayList<>();
    }

    public void addLostList(List<Item> lostList) {
        this.lostList.addAll(lostList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lost, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = lostList.get(position);
        holder.titleView.setText(item.getTitle());
        holder.itemTypeView.setText(item.getItemType());
    }

    @Override
    public int getItemCount() {
        return lostList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public TextView itemTypeView;

        public ViewHolder(View view) {
            super(view);
            titleView = view.findViewById(R.id.lost_title);
            itemTypeView = view.findViewById(R.id.lost_itemType);
        }
    }
}
