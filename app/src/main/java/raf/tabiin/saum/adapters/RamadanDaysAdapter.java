package raf.tabiin.saum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.domain.models.RamadanDay;


public class RamadanDaysAdapter extends RecyclerView.Adapter<RamadanDaysAdapter.ViewHolder> {

    private List<RamadanDay> ramadanDaysList;
    private OnCheckedChangeListener listener;

    public RamadanDaysAdapter(List<RamadanDay> ramadanDaysList, OnCheckedChangeListener listener) {
        this.ramadanDaysList = ramadanDaysList;
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
        holder.bind(ramadanDaysList.get(position));
    }

    @Override
    public int getItemCount() {
        return ramadanDaysList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_ramadan_day);
        }

        void bind(final RamadanDay ramadanDay) {
            checkBox.setText(ramadanDay.getDay());
            checkBox.setChecked(ramadanDay.isChecked());

            checkBox.setOnCheckedChangeListener(null);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ramadanDay.setChecked(isChecked);
                    if (listener != null) {
                        listener.onCheckedChanged(ramadanDay);
                    }
                }
            });
        }
    }

    public int getCheckedCount() {
        int count = 0;
        for (RamadanDay ramadanDay : ramadanDaysList) {
            if (ramadanDay.isChecked()) {
                count++;
            }
        }
        return count;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(RamadanDay ramadanDay);
    }
}