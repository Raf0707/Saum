package raf.tabiin.saum.ui.saum;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.MODE_PRIVATE;

import android.content.*;
import android.os.*;

import android.widget.*;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import raf.tabiin.saum.R;

public class ZulHijaSaumFragment extends Fragment implements View.OnClickListener {

    private Handler handler;
    private int countCheck = 0;
    private TextView itog;
    private SharedPreferences sPreff;
    private View vview;

    private ProgressBar postProgressBar;

    private Button reset;

    private CheckBox z1;
    private CheckBox z2;
    private CheckBox z3;
    private CheckBox z4;
    private CheckBox z5;
    private CheckBox z6;

    private CheckBox z7;
    private CheckBox z8;
    private CheckBox z9;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zul_hija_saum, null);

        itog = view.findViewById(R.id.itogPost);

        postProgressBar = view.findViewById(R.id.postZulHijaProgressBar);

        reset = view.findViewById(R.id.button_reset_z);

        reset.setOnClickListener(v -> {
            onAlert();
        });

        z1 = view.findViewById(R.id.z1);
        z2 = view.findViewById(R.id.z2);
        z3 = view.findViewById(R.id.z3);
        z4 = view.findViewById(R.id.z4);
        z5 = view.findViewById(R.id.z5);
        z6 = view.findViewById(R.id.z6);
        z7 = view.findViewById(R.id.z7);
        z8 = view.findViewById(R.id.z8);
        z9 = view.findViewById(R.id.z9);

        this.z1.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.z2.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.z3.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.z4.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.z5.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.z6.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.z7.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.z8.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.z9.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        loadText();

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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_reset:
                if (countCheck != 0 && itog.getText().toString() != "0") onAlert();
                break;
        }

    }

    public void saveText() {
        sPreff = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPreff.edit();

        ed.putBoolean("z1", z1.isChecked());
        ed.putBoolean("z2", z2.isChecked());
        ed.putBoolean("z3", z3.isChecked());
        ed.putBoolean("z4", z4.isChecked());
        ed.putBoolean("z5", z5.isChecked());
        ed.putBoolean("z6", z6.isChecked());
        ed.putBoolean("z7", z7.isChecked());
        ed.putBoolean("z8", z8.isChecked());
        ed.putBoolean("z9", z9.isChecked());




        ed.putString("saumZulHija", itog.getText().toString());
        postProgressBar.setProgress(countCheck);
        countCheck = Integer.parseInt(itog.getText().toString());
        ed.apply();
    }

    public void loadText() {
        sPreff = getActivity().getPreferences(MODE_PRIVATE);

        z1.setChecked(sPreff.getBoolean("z1", false));
        z2.setChecked(sPreff.getBoolean("z2", false));
        z3.setChecked(sPreff.getBoolean("z3", false));
        z4.setChecked(sPreff.getBoolean("z4", false));
        z5.setChecked(sPreff.getBoolean("z5", false));
        z6.setChecked(sPreff.getBoolean("z6", false));
        z7.setChecked(sPreff.getBoolean("z7", false));
        z8.setChecked(sPreff.getBoolean("z8", false));
        z9.setChecked(sPreff.getBoolean("z9", false));



        String tselText = sPreff.getString("saumZulHija", itog.getText().toString());
        itog.setText(tselText);
        postProgressBar.setProgress(countCheck);
    }

    public void onAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Обновить счетчик дней?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    z1.setChecked(false);
                    z2.setChecked(false);
                    z3.setChecked(false);
                    z4.setChecked(false);
                    z5.setChecked(false);
                    z6.setChecked(false);
                    z7.setChecked(false);
                    z8.setChecked(false);
                    z9.setChecked(false);


                    itog.setText("0");
                    countCheck = 0;
                    postProgressBar.setProgress(countCheck);


                    saveText();
                    loadText();
                })

                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        saveText();
        loadText();
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        saveText();
        loadText();
        super.onStop();
    }

    @Override
    public void onPause() {
        saveText();
        loadText();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        saveText();
        loadText();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        saveText();
        loadText();
        super.onDestroy();
    }
}