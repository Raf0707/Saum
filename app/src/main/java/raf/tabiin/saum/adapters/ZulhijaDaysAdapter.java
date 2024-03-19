package raf.tabiin.saum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.domain.models.ZulhijaDay;

public class ZulhijaDaysAdapter extends RecyclerView.Adapter<ZulhijaDaysAdapter.ViewHolder> {

    private List<ZulhijaDay> ZulhijaDaysList;
    private OnCheckedChangeListener listener;

    public ZulhijaDaysAdapter(List<ZulhijaDay> ZulhijaDaysList, OnCheckedChangeListener listener) {
        this.ZulhijaDaysList = ZulhijaDaysList;
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
        holder.bind(ZulhijaDaysList.get(position));
    }

    @Override
    public int getItemCount() {
        return ZulhijaDaysList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_saum_day);
        }

        void bind(final ZulhijaDay ZulhijaDay) {
            checkBox.setText(ZulhijaDay.getDay());
            checkBox.setChecked(ZulhijaDay.isChecked());

            checkBox.setOnCheckedChangeListener(null);

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                ZulhijaDay.setChecked(isChecked);
                if (listener != null) {
                    listener.onCheckedChanged(ZulhijaDay);
                    int checkedCount = getCheckedCount();
                    listener.onCheckedCountChanged(checkedCount);
                }
            });
        }
    }

    public int getCheckedCount() {
        int count = 0;
        for (ZulhijaDay ZulhijaDay : ZulhijaDaysList) {
            if (ZulhijaDay.isChecked()) {
                count++;
            }
        }
        return count;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(ZulhijaDay ZulhijaDay);
        void onCheckedCountChanged(int count);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }
}