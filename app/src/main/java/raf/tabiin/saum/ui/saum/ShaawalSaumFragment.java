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
import raf.tabiin.saum.databinding.FragmentShaawalSaumBinding;
import raf.tabiin.saum.domain.models.ShaawalDay;
import raf.tabiin.saum.adapters.ShaawalDaysAdapter;
import raf.tabiin.saum.util.FileUtils;
import raf.tabiin.saum.viewmodel.ShaawalDaysViewModel;

public class ShaawalSaumFragment extends Fragment {
    private FragmentShaawalSaumBinding binding;
    private ShaawalDaysAdapter adapter;
    private ShaawalDaysViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ShaawalDaysViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShaawalSaumBinding.inflate(inflater, container, false);

        binding.recyclerViewShaawalDays.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        List<ShaawalDay> ShaawalDaysList = viewModel.getShaawalDaysList();
        adapter = new ShaawalDaysAdapter(ShaawalDaysList, new ShaawalDaysAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ShaawalDay ShaawalDay) {
                saveShaawalDaysToJson();
            }

            @Override
            public void onCheckedCountChanged(int count) {
                binding.postShaawalProgressBar.setProgress(count);
                binding.itogPost.setText(String.valueOf(count));
            }
        });

        binding.recyclerViewShaawalDays.setAdapter(adapter);

        binding.buttonReset.setOnClickListener(v -> {
            binding.postShaawalProgressBar.setProgress(0);
            binding.itogPost.setText(String.valueOf(0));
            onAlert();
            loadShaawalDays();
            adapter.notifyDataSetChanged();
        });

        loadShaawalDays();

        int checkedCount = adapter.getCheckedCount();
        binding.postShaawalProgressBar.setProgress(checkedCount);
        binding.itogPost.setText(String.valueOf(checkedCount));

        return binding.getRoot();
    }

    private void loadShaawalDays() {
        List<ShaawalDay> ShaawalDaysList = viewModel.getShaawalDaysList();
        try {
            File internalDir = getActivity().getFilesDir();
            File ShaawalDaysFile = new File(internalDir, "shaawal_days.json");
            String json = FileUtils.readFileToString(ShaawalDaysFile);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("shaawal_days");

            // Очистка списка перед загрузкой новых данных
            ShaawalDaysList.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dayObject = jsonArray.getJSONObject(i);
                String day = dayObject.getString("day");
                boolean isChecked = dayObject.getBoolean("is_checked");
                ShaawalDaysList.add(new ShaawalDay(day, isChecked));
            }

            adapter.notifyDataSetChanged();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveShaawalDaysToJson() {
        List<ShaawalDay> ShaawalDaysList = viewModel.getShaawalDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (ShaawalDay ShaawalDay : ShaawalDaysList) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", ShaawalDay.getDay());
                dayObject.put("is_checked", ShaawalDay.isChecked());
                jsonArray.put(dayObject);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("shaawal_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "shaawal_days.json");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void resetJsonFile() {
        List<ShaawalDay> ShaawalDaysList = viewModel.getShaawalDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 1; i <= 6; i++) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", String.valueOf(i));
                dayObject.put("is_checked", false);
                jsonArray.put(dayObject);
                // Добавляем в список ShaawalDaysList
                ShaawalDaysList.add(new ShaawalDay(String.valueOf(i), false));
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("shaawal_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "shaawal_days.json");

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

                    binding.postShaawalProgressBar.setProgress(0);
                    binding.itogPost.setText(String.valueOf(0));

                    resetJsonFile();
                    loadShaawalDays();
                })

                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }
}