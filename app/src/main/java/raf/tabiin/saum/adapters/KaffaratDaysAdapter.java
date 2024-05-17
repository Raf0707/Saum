package raf.tabiin.saum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.domain.models.KaffaratDay;

public class KaffaratDaysAdapter extends RecyclerView.Adapter<KaffaratDaysAdapter.ViewHolder> {
    private List<KaffaratDay> kaffaratDaysList;
    private OnCheckedChangeListener listener;

    public KaffaratDaysAdapter(List<KaffaratDay> kaffaratDaysList, OnCheckedChangeListener listener) {
        this.kaffaratDaysList = kaffaratDaysList;
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
        holder.bind(kaffaratDaysList.get(position));
    }

    @Override
    public int getItemCount() {
        return kaffaratDaysList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_saum_day);
        }

        void bind(final KaffaratDay kaffaratDay) {
            checkBox.setText(kaffaratDay.getDay());
            checkBox.setChecked(kaffaratDay.isChecked());

            checkBox.setOnCheckedChangeListener(null);

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                kaffaratDay.setChecked(isChecked);
                if (listener != null) {
                    listener.onCheckedChanged(kaffaratDay);
                    int checkedCount = getCheckedCount();
                    listener.onCheckedCountChanged(checkedCount);
                }
            });
        }
    }

    public int getCheckedCount() {
        int count = 0;
        for (KaffaratDay kaffaratDay : kaffaratDaysList) {
            if (kaffaratDay.isChecked()) {
                count++;
            }
        }
        return count;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(KaffaratDay kaffaratDay);
        void onCheckedCountChanged(int count);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }
}
