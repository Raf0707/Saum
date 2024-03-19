package raf.tabiin.saum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.domain.models.MuharramDay;

public class MuharramDaysAdapter extends RecyclerView.Adapter<MuharramDaysAdapter.ViewHolder> {

    private List<MuharramDay> MuharramDaysList;
    private OnCheckedChangeListener listener;

    public MuharramDaysAdapter(List<MuharramDay> MuharramDaysList, OnCheckedChangeListener listener) {
        this.MuharramDaysList = MuharramDaysList;
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
        holder.bind(MuharramDaysList.get(position));
    }

    @Override
    public int getItemCount() {
        return MuharramDaysList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_saum_day);
        }

        void bind(final MuharramDay MuharramDay) {
            checkBox.setText(MuharramDay.getDay());
            checkBox.setChecked(MuharramDay.isChecked());

            checkBox.setOnCheckedChangeListener(null);

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                MuharramDay.setChecked(isChecked);
                if (listener != null) {
                    listener.onCheckedChanged(MuharramDay);
                    int checkedCount = getCheckedCount();
                    listener.onCheckedCountChanged(checkedCount);
                }
            });
        }
    }

    public int getCheckedCount() {
        int count = 0;
        for (MuharramDay MuharramDay : MuharramDaysList) {
            if (MuharramDay.isChecked()) {
                count++;
            }
        }
        return count;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MuharramDay MuharramDay);
        void onCheckedCountChanged(int count);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }
}