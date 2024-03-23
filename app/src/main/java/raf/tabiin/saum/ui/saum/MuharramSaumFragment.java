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
import raf.tabiin.saum.databinding.FragmentMuharramSaumBinding;
import raf.tabiin.saum.domain.models.MuharramDay;
import raf.tabiin.saum.adapters.MuharramDaysAdapter;
import raf.tabiin.saum.util.FileUtils;
import raf.tabiin.saum.viewmodel.MuharramDaysViewModel;

public class MuharramSaumFragment extends Fragment {
    private FragmentMuharramSaumBinding binding;
    private MuharramDaysAdapter adapter;
    private MuharramDaysViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MuharramDaysViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMuharramSaumBinding.inflate(inflater, container, false);

        binding.recyclerViewMuharramDays.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        List<MuharramDay> MuharramDaysList = viewModel.getMuharramDaysList();
        adapter = new MuharramDaysAdapter(MuharramDaysList, new MuharramDaysAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MuharramDay MuharramDay) {
                saveMuharramDaysToJson();
            }

            @Override
            public void onCheckedCountChanged(int count) {
                binding.postMuharramProgressBar.setProgress(count);
                binding.itogPost.setText(String.valueOf(count));
            }
        });
        binding.recyclerViewMuharramDays.setAdapter(adapter);

        binding.buttonReset.setOnClickListener(v -> {
            binding.postMuharramProgressBar.setProgress(0);
            binding.itogPost.setText(String.valueOf(0));
            onAlert();
            loadMuharramDays();
            adapter.notifyDataSetChanged();
        });

        loadMuharramDays();

        int checkedCount = adapter.getCheckedCount();
        binding.postMuharramProgressBar.setProgress(checkedCount);
        binding.itogPost.setText(String.valueOf(checkedCount));

        return binding.getRoot();
    }

    private void loadMuharramDays() {
        List<MuharramDay> MuharramDaysList = viewModel.getMuharramDaysList();
        try {
            File internalDir = getActivity().getFilesDir();
            File MuharramDaysFile = new File(internalDir, "muharram_days.json");
            String json = FileUtils.readFileToString(MuharramDaysFile);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("muharram_days");

            // Очистка списка перед загрузкой новых данных
            MuharramDaysList.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dayObject = jsonArray.getJSONObject(i);
                String day;
                if (i == 9) {
                    day = dayObject.getString("day");// + " День Ашура";
                } else {
                    day = dayObject.getString("day");
                }

                boolean isChecked = dayObject.getBoolean("is_checked");
                MuharramDaysList.add(new MuharramDay(day, isChecked));
            }

            adapter.notifyDataSetChanged();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveMuharramDaysToJson() {
        List<MuharramDay> MuharramDaysList = viewModel.getMuharramDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (MuharramDay MuharramDay : MuharramDaysList) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", MuharramDay.getDay());
                dayObject.put("is_checked", MuharramDay.isChecked());
                jsonArray.put(dayObject);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("muharram_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "muharram_days.json");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void resetJsonFile() {
        List<MuharramDay> MuharramDaysList = viewModel.getMuharramDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 1; i <= 30; i++) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", String.valueOf(i));
                dayObject.put("is_checked", false);
                jsonArray.put(dayObject);
                // Добавляем в список MuharramDaysList
                MuharramDaysList.add(new MuharramDay(String.valueOf(i), false));
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("muharram_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "muharram_days.json");

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

                    binding.postMuharramProgressBar.setProgress(0);
                    binding.itogPost.setText(String.valueOf(0));

                    resetJsonFile();
                    loadMuharramDays();
                })

                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }
}