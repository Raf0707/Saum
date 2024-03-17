package raf.tabiin.saum.ui.saum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.domain.models.RamadanDay;
import raf.tabiin.saum.adapters.RamadanDaysAdapter;
import raf.tabiin.saum.databinding.FragmentPostRamadanBinding;
import raf.tabiin.saum.util.FileUtils;
import raf.tabiin.saum.viewmodel.RamadanDaysViewModel;

public class RamadanSaumFragment extends Fragment {
    private FragmentPostRamadanBinding binding;
    private RamadanDaysAdapter adapter;
    private RamadanDaysViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(RamadanDaysViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostRamadanBinding.inflate(inflater, container, false);

        binding.recyclerViewRamadanDays.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        List<RamadanDay> ramadanDaysList = viewModel.getRamadanDaysList();
        adapter = new RamadanDaysAdapter(ramadanDaysList, new RamadanDaysAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RamadanDay ramadanDay) {
                saveRamadanDaysToJson();
            }

            @Override
            public void onCheckedCountChanged(int count) {
                binding.postRamadanProgressBar.setProgress(count);
                binding.itogPost.setText(String.valueOf(count));
            }
        });
        binding.recyclerViewRamadanDays.setAdapter(adapter);

        binding.buttonReset.setOnClickListener(v -> {
            binding.postRamadanProgressBar.setProgress(0);
            binding.itogPost.setText(String.valueOf(0));
            onAlert();
            loadRamadanDays();
            adapter.notifyDataSetChanged();
        });

        loadRamadanDays();

        int checkedCount = adapter.getCheckedCount();
        binding.postRamadanProgressBar.setProgress(checkedCount);
        binding.itogPost.setText(String.valueOf(checkedCount));

        return binding.getRoot();
    }

    private void loadRamadanDays() {
        List<RamadanDay> ramadanDaysList = viewModel.getRamadanDaysList();
        try {
            File internalDir = getActivity().getFilesDir();
            File ramadanDaysFile = new File(internalDir, "ramadan_days.json");
            String json = FileUtils.readFileToString(ramadanDaysFile);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("ramadan_days");

            // Очистка списка перед загрузкой новых данных
            ramadanDaysList.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dayObject = jsonArray.getJSONObject(i);
                String day = dayObject.getString("day");
                boolean isChecked = dayObject.getBoolean("is_checked");
                ramadanDaysList.add(new RamadanDay(day, isChecked));
            }

            adapter.notifyDataSetChanged();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveRamadanDaysToJson() {
        List<RamadanDay> ramadanDaysList = viewModel.getRamadanDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (RamadanDay ramadanDay : ramadanDaysList) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", ramadanDay.getDay());
                dayObject.put("is_checked", ramadanDay.isChecked());
                jsonArray.put(dayObject);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ramadan_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "ramadan_days.json");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void resetJsonFile() {
        List<RamadanDay> ramadanDaysList = viewModel.getRamadanDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 1; i <= 30; i++) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", String.valueOf(i));
                dayObject.put("is_checked", false);
                jsonArray.put(dayObject);
                // Добавляем в список ramadanDaysList
                ramadanDaysList.add(new RamadanDay(String.valueOf(i), false));
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ramadan_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "ramadan_days.json");

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void onAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Обновить счетчик дней?")
                .setPositiveButton("Да", (dialogInterface, i) -> {

                    binding.postRamadanProgressBar.setProgress(0);
                    binding.itogPost.setText(String.valueOf(0));

                    resetJsonFile();
                    loadRamadanDays();
                })

                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }
}