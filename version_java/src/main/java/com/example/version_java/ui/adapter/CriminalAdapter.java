package com.example.version_java.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.version_java.R;
import com.example.version_java.data.model.DistanceCriminal;
import com.example.version_java.databinding.ItemCriminalListBinding;
import com.example.version_java.ui.adapter.viewholder.CriminalViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class CriminalAdapter extends RecyclerView.Adapter<CriminalViewHolder> {
    private final List<DistanceCriminal> criminalList = new ArrayList<>();

    @NonNull
    @Override
    public CriminalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCriminalListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_criminal_list, parent, false);
        return new CriminalViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CriminalViewHolder holder, int position) {
        holder.bind((DistanceCriminal) criminalList.get(position));
    }

    @Override
    public int getItemCount() {
        return criminalList.size();
    }

    public final void renewAll(@NotNull List<DistanceCriminal> list) {
        this.criminalList.clear();
        this.criminalList.addAll(list);
        this.notifyDataSetChanged();
    }

    public final void clear() {
        this.criminalList.clear();
        this.notifyDataSetChanged();
    }
}
