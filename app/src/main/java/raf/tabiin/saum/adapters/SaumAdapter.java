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

    public SaumAdapter(Context context) {
        this.context = context;
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

        holder.binding.daySaum.setText(this.saumList.get(position).day);
        holder.binding.monthSaum.setText(new StringBuilder()
                .append(this.saumList.get(position).month).toString());
        holder.binding.done.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked, holder));
        //holder.binding.progressMT.setProgress(this.saumList.get(position).progress);


        holder.binding.deleteDBCounterItem.setOnClickListener(v -> {
            clickListener.deleteItem(saumList.get(position));
        });

        holder.binding.editDBCounterItem.setOnClickListener(v -> {
            clickListener.editItem(saumList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        if (saumList == null || saumList.size() == 0) {
            return 0;
        } else {
            return saumList.size();
        }
    }


    public void onCountCheck(boolean isChecked, MyViewHolder holder) {
        if (isChecked) {
            holder.binding.progressMT.setProgress(1);
        } else {
            holder.binding.progressMT.setProgress(0);
        }

        if (holder.binding.progressMT.getProgress() == 1) {
            isChecked = true;
            holder.binding.done.setChecked(isChecked);
        } else {
            isChecked = false;
            holder.binding.done.setChecked(isChecked);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        MonThsElementItemBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MonThsElementItemBinding.bind(itemView);
        }
    }

    public interface HandleCounterClick {
        void itemClick(SaumItem counterItem);
        void deleteItem(SaumItem counterItem);
        void editItem(SaumItem counterItem);
    }
}
