package raf.tabiin.saum.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.NazrCardItemBinding;
import raf.tabiin.saum.domain.models.NazrItem;

public class NazrAdapter extends RecyclerView.Adapter<NazrAdapter.MyViewHolder> {

    private int counter = 0;
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

        NazrItem nazrItem = nazrList.get(position);

        holder.binding.titleNazr.setText(nazrItem.getTitleNazr());
        holder.binding.textNazar.setText(nazrItem.getTextNazr());
        holder.binding.progressNazr.setProgress(nazrItem.getProgress());

        // Установка состояний чекбоксов
        holder.binding.day1.setChecked(nazrItem.isDay1());
        holder.binding.day2.setChecked(nazrItem.isDay2());
        holder.binding.day3.setChecked(nazrItem.isDay3());
        holder.binding.done.setChecked(nazrItem.isCompleted());

        holder.binding.day1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            nazrItem.setDay1(isChecked);
            updateProgressAndCompletion(holder, nazrItem);
            clickListener.updateItem(nazrItem);
        });

        holder.binding.day2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            nazrItem.setDay2(isChecked);
            updateProgressAndCompletion(holder, nazrItem);
            clickListener.updateItem(nazrItem);
        });

        holder.binding.day3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            nazrItem.setDay3(isChecked);
            updateProgressAndCompletion(holder, nazrItem);
            clickListener.updateItem(nazrItem);
        });

        holder.binding.done.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    nazrItem.setCompleted(isChecked);
                    updateProgressAndCompletion(holder, nazrItem);
                    clickListener.updateItem(nazrItem);
                });

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

    private void updateProgressAndCompletion(MyViewHolder holder, NazrItem nazrItem) {
        boolean isDay1Checked = holder.binding.day1.isChecked();
        boolean isDay2Checked = holder.binding.day2.isChecked();
        boolean isDay3Checked = holder.binding.day3.isChecked();

        int checkedCount = 0;
        if (isDay1Checked) {
            checkedCount++;
        }
        if (isDay2Checked) {
            checkedCount++;
        }
        if (isDay3Checked) {
            checkedCount++;
        }

        counter = checkedCount;

        nazrItem.setDay1(isDay1Checked);
        nazrItem.setDay2(isDay2Checked);
        nazrItem.setDay3(isDay3Checked);
        nazrItem.setProgress(checkedCount);
        nazrItem.setCompleted(checkedCount == 3);

        holder.binding.progressNazr.setProgress(checkedCount);

        holder.binding.done.setChecked(nazrItem.isCompleted());
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
        void updateItem(NazrItem nazrItem);
    }
}

