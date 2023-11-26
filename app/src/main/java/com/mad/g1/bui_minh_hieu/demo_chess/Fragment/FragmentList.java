package com.mad.g1.bui_minh_hieu.demo_chess.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mad.g1.bui_minh_hieu.demo_chess.Adapter.RecycleViewAdapter;
import com.mad.g1.bui_minh_hieu.demo_chess.R;
import com.mad.g1.bui_minh_hieu.demo_chess.activity.UpdateDeleteActivity;
import com.mad.g1.bui_minh_hieu.demo_chess.dbh.DBHandle;
import com.mad.g1.bui_minh_hieu.demo_chess.model.Match;

import java.io.Serializable;
import java.util.List;

public class FragmentList extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private DBHandle db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyHome);
        adapter= new RecycleViewAdapter();
        db = new DBHandle(getContext());
        List<Match> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Match match = adapter.getMatch(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("matches", (Serializable) match);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Match> list = db.getAll();
        adapter.setList(list);
    }
}
