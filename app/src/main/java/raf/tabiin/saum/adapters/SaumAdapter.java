package raf.tabiin.saum.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.MonThsElementItemBinding;
import raf.tabiin.saum.domain.models.SaumItem;

public class SaumAdapter extends RecyclerView.Adapter<SaumAdapter.MyViewHolder> {

    private Context context;
    private List<SaumItem> saumList;
    HandleCounterClick clickListener;

    public SaumAdapter(Context context, HandleCounterClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setSaumList(List<SaumItem> saumsList) {
        this.saumList = saumsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mon_ths_element_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SaumItem saumItem = saumList.get(position);

        holder.binding.daySaum.setText(saumItem.getDay());
        holder.binding.monthSaum.setText(saumItem.getMonth());
        holder.binding.progressMT.setProgress(saumItem.getProgress());
        holder.binding.done.setChecked(saumItem.isCompleted());

        holder.binding.done.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saumItem.setCompleted(isChecked);
            saumItem.setProgress(1);
            clickListener.updateItem(saumItem);
        });

        holder.binding.deleteDBCounterItem.setOnClickListener(v -> {
            clickListener.deleteItem(saumItem);
        });

        holder.binding.editDBCounterItem.setOnClickListener(v -> {
            clickListener.editItem(saumItem);
        });
    }

    @Override
    public int getItemCount() {
        return saumList == null ? 0 : saumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        MonThsElementItemBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MonThsElementItemBinding.bind(itemView);
        }
    }

    public interface HandleCounterClick {
        void deleteItem(SaumItem counterItem);

        void editItem(SaumItem counterItem);
        void updateItem(SaumItem counterItem);
    }
}
