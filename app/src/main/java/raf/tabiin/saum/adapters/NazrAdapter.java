package raf.tabiin.saum.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.NazrCardItemBinding;
import raf.tabiin.saum.domain.models.NazrItem;

public class NazrAdapter extends RecyclerView.Adapter<NazrAdapter.MyViewHolder> {

    private Context context;
    private List<NazrItem> nazrList;
    HandleCounterClick clickListener;


    public NazrAdapter(Context context, HandleCounterClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public NazrAdapter(Context context) {
        this.context = context;
    }
    public void setNazrList(List<NazrItem> nazrList) {
        this.nazrList = nazrList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nazr_card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.titleNazr.setText(this.nazrList.get(position).titleNazr);
        holder.binding.textNazar.setText(new StringBuilder()
                .append(this.nazrList.get(position).textNazr).toString());
        holder.binding.done.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked, holder));
        //holder.binding.progressMT.setProgress(this.saumList.get(position).progress);


        holder.binding.deleteNazrItem.setOnClickListener(v -> {
            clickListener.deleteItem(nazrList.get(position));
        });

        holder.binding.editNazrItem.setOnClickListener(v -> {
            clickListener.editItem(nazrList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        if (nazrList == null || nazrList.size() == 0) {
            return 0;
        } else {
            return nazrList.size();
        }
    }


    public void onCountCheck(boolean isChecked, MyViewHolder holder) {
        if (isChecked) {
            holder.binding.progressNazr.setProgress(3);
        } else {
            holder.binding.progressNazr.setProgress(0);
        }

        if (holder.binding.progressNazr.getProgress() == 3) {
            isChecked = true;
            holder.binding.done.setChecked(isChecked);
        } else {
            isChecked = false;
            holder.binding.done.setChecked(isChecked);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        NazrCardItemBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NazrCardItemBinding.bind(itemView);
        }
    }

    public interface HandleCounterClick {
        void itemClick(NazrItem nazrItem);
        void deleteItem(NazrItem nazrItem);
        void editItem(NazrItem nazrItem);
    }
}

