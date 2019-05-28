package com.example.healthtrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiaryLayoutAdapter extends RecyclerView.Adapter<DiaryLayoutAdapter.DiaryHolder> {

    public static class DiaryHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textQuantity;
        public TextView textDescription;
        public TextView textTimestamp;

        public DiaryHolder(@NonNull View itemView) {
            super(itemView);

            //get individual controls from layout
            this.textTitle = itemView.findViewById(R.id.text_title);
            this.textQuantity = itemView.findViewById(R.id.text_quantity);
            this.textDescription = itemView.findViewById(R.id.text_description);
            this.textTimestamp = itemView.findViewById(R.id.text_timestamp);
        }

        public void setDiary(Diary diary) {
            //set fields diary entries
            this.textTitle.setText(diary.getTitle());
            this.textQuantity.setText(diary.getQuantity());
            this.textDescription.setText(diary.getDescription());
            this.textTimestamp.setText(diary.getTimestamp());
        }
    }

    private List<Diary> diaries;

    public DiaryLayoutAdapter(List<Diary> diaries) {
        //adapter manages data source
        this.diaries = diaries;
    }

    public void removeDiary(int index) {
        //notify recycler if data is to be modified
        this.diaries.remove(index);
        this.notifyItemRemoved(index);
    }

    public void setDiaries(ArrayList<Diary> diaries) {
        this.diaries = diaries;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the view - turn .xml into view object
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.diary_view, parent, false);

        //wrap view in holder and return
        DiaryHolder holder = new DiaryHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryHolder holder, int position) {
        //bind object to view holder
        Diary diary = diaries.get(position);
        holder.setDiary(diary);
    }

    @Override
    public int getItemCount() {
        return diaries.size();
    }
}
