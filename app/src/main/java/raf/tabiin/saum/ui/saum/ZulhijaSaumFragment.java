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
import raf.tabiin.saum.databinding.FragmentZulHijaSaumBinding;
import raf.tabiin.saum.domain.models.ZulhijaDay;
import raf.tabiin.saum.adapters.ZulhijaDaysAdapter;
import raf.tabiin.saum.util.FileUtils;
import raf.tabiin.saum.viewmodel.ZulhijaDaysViewModel;

public class ZulhijaSaumFragment extends Fragment {
    private FragmentZulHijaSaumBinding binding;
    private ZulhijaDaysAdapter adapter;
    private ZulhijaDaysViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ZulhijaDaysViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentZulHijaSaumBinding.inflate(inflater, container, false);

        binding.recyclerViewZulhijaDays.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        List<ZulhijaDay> ZulhijaDaysList = viewModel.getZulhijaDaysList();
        adapter = new ZulhijaDaysAdapter(ZulhijaDaysList, new ZulhijaDaysAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ZulhijaDay ZulhijaDay) {
                saveZulhijaDaysToJson();
            }

            @Override
            public void onCheckedCountChanged(int count) {
                binding.postZulhijaProgressBar.setProgress(count);
                binding.itogPost.setText(String.valueOf(count));
            }
        });
        binding.recyclerViewZulhijaDays.setAdapter(adapter);

        binding.buttonReset.setOnClickListener(v -> {
            binding.postZulhijaProgressBar.setProgress(0);
            binding.itogPost.setText(String.valueOf(0));
            onAlert();
            loadZulhijaDays();
            adapter.notifyDataSetChanged();
        });

        loadZulhijaDays();

        int checkedCount = adapter.getCheckedCount();
        binding.postZulhijaProgressBar.setProgress(checkedCount);
        binding.itogPost.setText(String.valueOf(checkedCount));

        return binding.getRoot();
    }

    private void loadZulhijaDays() {
        List<ZulhijaDay> ZulhijaDaysList = viewModel.getZulhijaDaysList();
        try {
            File internalDir = getActivity().getFilesDir();
            File ZulhijaDaysFile = new File(internalDir, "zulhija_days.json");
            String json = FileUtils.readFileToString(ZulhijaDaysFile);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("zulhija_days");

            // Очистка списка перед загрузкой новых данных
            ZulhijaDaysList.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dayObject = jsonArray.getJSONObject(i);
                String day;
                if (i == 8) {
                    day = dayObject.getString("day") + " День Арафата";
                } else {
                    day = dayObject.getString("day");
                }
                boolean isChecked = dayObject.getBoolean("is_checked");
                ZulhijaDaysList.add(new ZulhijaDay(day, isChecked));
            }

            adapter.notifyDataSetChanged();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveZulhijaDaysToJson() {
        List<ZulhijaDay> ZulhijaDaysList = viewModel.getZulhijaDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (ZulhijaDay ZulhijaDay : ZulhijaDaysList) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", ZulhijaDay.getDay());
                dayObject.put("is_checked", ZulhijaDay.isChecked());
                jsonArray.put(dayObject);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("zulhija_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "zulhija_days.json");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void resetJsonFile() {
        List<ZulhijaDay> ZulhijaDaysList = viewModel.getZulhijaDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 1; i <= 9; i++) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", String.valueOf(i));
                dayObject.put("is_checked", false);
                jsonArray.put(dayObject);
                // Добавляем в список ZulhijaDaysList
                ZulhijaDaysList.add(new ZulhijaDay(String.valueOf(i), false));
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("zulhija_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "zulhija_days.json");

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

                    binding.postZulhijaProgressBar.setProgress(0);
                    binding.itogPost.setText(String.valueOf(0));

                    resetJsonFile();
                    loadZulhijaDays();
                })

                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }
}