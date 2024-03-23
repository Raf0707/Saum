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

        CheckBox day1 = holder.binding.day1;
        CheckBox day2 = holder.binding.day2;
        CheckBox day3 = holder.binding.day3;

        holder.binding.titleNazr.setText(this.nazrList.get(position).titleNazr);
        holder.binding.textNazar.setText(new StringBuilder()
                .append(this.nazrList.get(position).textNazr).toString());
        holder.binding.day1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (day1.isChecked()) {
                counter++;
                holder.binding.progressNazr.setProgress(counter);
                if (day1.isChecked() && day2.isChecked() && day3.isChecked()) {
                    holder.binding.done.setChecked(true);
                } else {
                    holder.binding.done.setChecked(false);
                }
            } else {
                counter--;
                holder.binding.progressNazr.setProgress(counter);
                if (day1.isChecked() && day2.isChecked() && day3.isChecked()) {
                    holder.binding.done.setChecked(true);
                } else {
                    holder.binding.done.setChecked(false);
                }
            }
        });

        holder.binding.day2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (day2.isChecked()) {
                counter++;
                holder.binding.progressNazr.setProgress(counter);
                if (day1.isChecked() && day2.isChecked() && day3.isChecked()) {
                    holder.binding.done.setChecked(true);
                } else {
                    holder.binding.done.setChecked(false);
                }
            } else {
                counter--;
                holder.binding.progressNazr.setProgress(counter);
                if (day1.isChecked() && day2.isChecked() && day3.isChecked()) {
                    holder.binding.done.setChecked(true);
                } else {
                    holder.binding.done.setChecked(false);
                }
            }
        });

        holder.binding.day3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (day3.isChecked()) {
                counter++;
                holder.binding.progressNazr.setProgress(counter);
                if (day1.isChecked() && day2.isChecked() && day3.isChecked()) {
                    holder.binding.done.setChecked(true);
                } else {
                    holder.binding.done.setChecked(false);
                }
            } else {
                counter--;
                holder.binding.progressNazr.setProgress(counter);
                if (day1.isChecked() && day2.isChecked() && day3.isChecked()) {
                    holder.binding.done.setChecked(true);
                } else {
                    holder.binding.done.setChecked(false);
                }
            }
        });
        holder.binding.done.setOnCheckedChangeListener(
                (buttonView, isChecked) -> onCountCheck(isChecked, day1, day2, day3, holder));

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


    public void onCountCheck(boolean isChecked, CheckBox day1, CheckBox day2, CheckBox day3,
                             MyViewHolder holder) {
        if (isChecked) {
            holder.binding.progressNazr.setProgress(3);
            day1.setChecked(true);
            day2.setChecked(true);
            day3.setChecked(true);
        } else {
            holder.binding.progressNazr.setProgress(0);
            day1.setChecked(false);
            day2.setChecked(false);
            day3.setChecked(false);
        }

        if (holder.binding.progressNazr.getProgress() == 3) {
            isChecked = true;
            holder.binding.done.setChecked(isChecked);
        } else {
            isChecked = false;
            holder.binding.done.setChecked(isChecked);
        }


        if (day1.isChecked() && day2.isChecked() && day3.isChecked()) {
            holder.binding.done.setChecked(true);
        } else {
            holder.binding.done.setChecked(false);
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

