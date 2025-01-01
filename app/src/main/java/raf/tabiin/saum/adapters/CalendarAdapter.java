package raf.tabiin.saum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import raf.tabiin.saum.R;
import raf.tabiin.saum.util.OnSwipeTouchListener;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    private final ArrayList<String> dayOfMonths;
    private final OnItemListener itemListener;

    public CalendarAdapter(ArrayList<String> dayOfMonths, OnItemListener itemListener) {
        this.dayOfMonths = dayOfMonths;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.calendar_cell, parent, false);
        return new CalendarViewHolder(view, itemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(dayOfMonths.get(position));
    }

    @Override
    public int getItemCount() {
        return dayOfMonths.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
        void onItemLongClick(int position, String dayText);
        void onItemDoubleClick(int position, String dayText);
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder {
        public final TextView dayOfMonth;

        public CalendarViewHolder(@NonNull View itemView, OnItemListener itemListener) {
            super(itemView);
            dayOfMonth = itemView.findViewById(R.id.cellDayText);

            itemView.setOnTouchListener(new OnSwipeTouchListener(itemView.getContext()) {
                @Override
                public void onClick() {
                    if (itemListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            itemListener.onItemClick(position, dayOfMonth.getText().toString());
                        }
                    }
                }

                @Override
                public void onDoubleClick() {
                    if (itemListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            itemListener.onItemDoubleClick(position, dayOfMonth.getText().toString());
                        }
                    }
                }

                @Override
                public void onLongClick() {
                    if (itemListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            itemListener.onItemLongClick(position, dayOfMonth.getText().toString());
                        }
                    }
                }
            });
        }
    }
}