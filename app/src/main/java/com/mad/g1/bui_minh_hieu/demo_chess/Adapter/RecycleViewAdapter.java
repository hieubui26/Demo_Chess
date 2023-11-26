package com.mad.g1.bui_minh_hieu.demo_chess.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mad.g1.bui_minh_hieu.demo_chess.R;
import com.mad.g1.bui_minh_hieu.demo_chess.model.Match;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private List<Match> list;
    private ItemListener itemListener;



    public RecycleViewAdapter() {
        list = new ArrayList<>();

    }

    public List<Match> getList() {
        return list;
    }

    public void setList(List<Match> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Match getMatch(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Match match = list.get(position);
        holder.name.setText(match .getName());
        holder.date.setText(match .getDate());
        holder.description.setText(match .getDescription());
        holder.level.setText(match .getLevel());
        holder.status.setText(!match .getStatus() ? "May":"Nguoi");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name, date,description,  level, status;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            name= view.findViewById(R.id.tvName);
            date= view.findViewById(R.id.tvDate);
            description= view.findViewById(R.id.tvDescription);
            level= view.findViewById(R.id.tvLevel);
            status= view.findViewById(R.id.tvStatus);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (itemListener != null)
                itemListener.onItemClick(view, getAdapterPosition() );
        }
    }
    public interface  ItemListener{
        void onItemClick(View view,int position);
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
}

