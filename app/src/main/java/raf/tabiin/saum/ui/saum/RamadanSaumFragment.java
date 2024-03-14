package raf.tabiin.saum.ui.saum;

import android.os.*;

import raf.tabiin.saum.util.FileUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.adapters.RamadanDaysAdapter;
import raf.tabiin.saum.domain.models.RamadanDay;

public class RamadanSaumFragment extends Fragment implements View.OnClickListener {
    private int countCheck = 0;
    private TextView itog;

    private ProgressBar postProgressBar;

    private Button reset;

    private List<RamadanDay> ramadanDaysList;
    private RecyclerView recyclerView;
    private RamadanDaysAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post_ramadan, container, false);

        itog = view.findViewById(R.id.itogPost);

        postProgressBar = view.findViewById(R.id.postRamadanProgressBar);

        reset = view.findViewById(R.id.button_reset);

        reset.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recycler_view_ramadan_days);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        ramadanDaysList = new ArrayList<>() ;
        adapter = new RamadanDaysAdapter(ramadanDaysList, ramadanDay -> saveRamadanDaysToJson());
        recyclerView.setAdapter(adapter);

        loadRamadanDays();

        return view;
    }

    public void onCountCheck(boolean isChecked) {
        if (isChecked) {
            countCheck++;
            itog.setText(Integer.toString(countCheck));
            postProgressBar.setProgress(countCheck);
        }
        if (!isChecked && countCheck != 0) {
            countCheck--;
            if (countCheck < 0) countCheck = 0;
            itog.setText(Integer.toString(countCheck));
            postProgressBar.setProgress(countCheck);
        }

        saveRamadanDaysToJson();
        loadRamadanDays();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_reset:
                if (countCheck != 0 && itog.getText().toString() != "0") onAlert();
                break;
        }

        saveRamadanDaysToJson();
        loadRamadanDays();
    }
    public void onAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Обновить счетчик дней?")
                .setPositiveButton("Да", (dialogInterface, i) -> {

                        // очистить все

                        itog.setText("0");
                        countCheck = 0;
                        postProgressBar.setProgress(countCheck);


                        saveRamadanDaysToJson();
                        loadRamadanDays();
                })

                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //exportDataToMainFragment();
        saveRamadanDaysToJson();
        loadRamadanDays();
        super.onSaveInstanceState(outState);
    }

    private void loadRamadanDays() {
        try {
            File internalDir = getActivity().getFilesDir();
            File ramadanDaysFile = new File(internalDir, "ramadan_days.json");
            String json = FileUtils.readFileToString(ramadanDaysFile);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("ramadan_days");

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

    @Override
    public void onStop() {
        //exportDataToMainFragment();
        saveRamadanDaysToJson();
        loadRamadanDays();
        super.onStop();
    }

    @Override
    public void onPause() {
        //exportDataToMainFragment();
        saveRamadanDaysToJson();
        loadRamadanDays();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        //exportDataToMainFragment();
        saveRamadanDaysToJson();
        loadRamadanDays();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        //exportDataToMainFragment();
        saveRamadanDaysToJson();
        loadRamadanDays();
        super.onDestroy();
    }
}