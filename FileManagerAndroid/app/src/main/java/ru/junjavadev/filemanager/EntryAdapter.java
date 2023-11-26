package ru.junjavadev.filemanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {
    interface OnEntryClickListener{
        void onEntryClick(Entry entry, int position);
    }

    private final OnEntryClickListener onClickListener;
    private final LayoutInflater inflater;
    private final List<Entry> entries;



    EntryAdapter(Context context, List<Entry> entries, OnEntryClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.entries = entries;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public EntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) { // разобраться с  @SuppressLint("RecyclerView")
            Entry entry = entries.get(position);
            holder.entryIconView.setImageResource(entry.getEntryIconResource());
            holder.entryNameView.setText(entry.getEntryName());
            holder.entryDescriptionView.setText(entry.getEntryDescription());

            // обработка нажатия
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // вызываем метод слушателя, передавая ему данные
                    onClickListener.onEntryClick(entry, position);
                }
            });

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView entryIconView;
        final TextView entryNameView, entryDescriptionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            entryIconView = itemView.findViewById(R.id.entry_icon);
            entryNameView = itemView.findViewById(R.id.entry_name);
            entryDescriptionView = itemView.findViewById(R.id.entry_description);

        }




    }
}