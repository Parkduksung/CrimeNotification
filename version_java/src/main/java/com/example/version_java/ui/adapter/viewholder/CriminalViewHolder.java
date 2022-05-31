package com.example.version_java.ui.adapter.viewholder;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.version_java.data.model.DistanceCriminal;
import com.example.version_java.databinding.ItemCriminalListBinding;

import org.jetbrains.annotations.NotNull;

public final class CriminalViewHolder extends RecyclerView.ViewHolder {
    private final ItemCriminalListBinding binding;

    public final void bind(@NotNull DistanceCriminal item) {
        Log.d("결과", item.getName());
        this.binding.setItem(item);
    }

    public CriminalViewHolder(@NotNull ItemCriminalListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
