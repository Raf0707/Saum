package raf.tabiin.saum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.domain.models.ShaawalDay;

public class ShaawalDaysAdapter extends RecyclerView.Adapter<ShaawalDaysAdapter.ViewHolder> {

    private List<ShaawalDay> ShaawalDaysList;
    private OnCheckedChangeListener listener;

    public ShaawalDaysAdapter(List<ShaawalDay> ShaawalDaysList, OnCheckedChangeListener listener) {
        this.ShaawalDaysList = ShaawalDaysList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_checkbox, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(ShaawalDaysList.get(position));
    }

    @Override
    public int getItemCount() {
        return ShaawalDaysList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_saum_day);
        }

        void bind(final ShaawalDay ShaawalDay) {
            checkBox.setText(ShaawalDay.getDay());
            checkBox.setChecked(ShaawalDay.isChecked());

            checkBox.setOnCheckedChangeListener(null);

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                ShaawalDay.setChecked(isChecked);
                if (listener != null) {
                    listener.onCheckedChanged(ShaawalDay);
                    int checkedCount = getCheckedCount();
                    listener.onCheckedCountChanged(checkedCount);
                }
            });
        }
    }

    public int getCheckedCount() {
        int count = 0;
        for (ShaawalDay ShaawalDay : ShaawalDaysList) {
            if (ShaawalDay.isChecked()) {
                count++;
            }
        }
        return count;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(ShaawalDay ShaawalDay);
        void onCheckedCountChanged(int count);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }
}