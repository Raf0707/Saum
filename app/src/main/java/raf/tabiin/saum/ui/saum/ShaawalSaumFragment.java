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


import raf.tabiin.saum.databinding.FragmentShaawalSaumBinding;

public class ShaawalSaumFragment extends Fragment implements View.OnClickListener {

    private Handler handler;
    private int countCheck = 0;
    private TextView itog;
    private SharedPreferences sPreff;
    private View vview;

    private ProgressBar postProgressBar;

    private Button reset;

    private CheckBox s1;
    private CheckBox s2;
    private CheckBox s3;
    private CheckBox s4;
    private CheckBox s5;
    private CheckBox s6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shaawal_saum, null);

        itog = view.findViewById(R.id.itogPost);

        postProgressBar = view.findViewById(R.id.postShaawalProgressBar);

        reset = view.findViewById(R.id.button_reset_s);

        reset.setOnClickListener(v -> {
            onAlert();
        });

        s1 = view.findViewById(R.id.s1);
        s2 = view.findViewById(R.id.s2);
        s3 = view.findViewById(R.id.s3);
        s4 = view.findViewById(R.id.s4);
        s5 = view.findViewById(R.id.s5);
        s6 = view.findViewById(R.id.s6);

        this.s1.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.s2.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.s3.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.s4.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.s5.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.s6.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

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

        ed.putBoolean("s1", s1.isChecked());
        ed.putBoolean("s2", s2.isChecked());
        ed.putBoolean("s3", s3.isChecked());
        ed.putBoolean("s4", s4.isChecked());
        ed.putBoolean("s5", s5.isChecked());
        ed.putBoolean("s6", s6.isChecked());




        ed.putString("saumShaawal", itog.getText().toString());
        postProgressBar.setProgress(countCheck);
        countCheck = Integer.parseInt(itog.getText().toString());
        ed.apply();
    }

    public void loadText() {
        sPreff = getActivity().getPreferences(MODE_PRIVATE);

        s1.setChecked(sPreff.getBoolean("s1", false));
        s2.setChecked(sPreff.getBoolean("s2", false));
        s3.setChecked(sPreff.getBoolean("s3", false));
        s4.setChecked(sPreff.getBoolean("s4", false));
        s5.setChecked(sPreff.getBoolean("s5", false));
        s6.setChecked(sPreff.getBoolean("s6", false));




        String tselText = sPreff.getString("saumShaawal", itog.getText().toString());
        itog.setText(tselText);
        postProgressBar.setProgress(countCheck);
    }

    public void onAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Обновить счетчик дней?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    s1.setChecked(false);
                    s2.setChecked(false);
                    s3.setChecked(false);
                    s4.setChecked(false);
                    s5.setChecked(false);
                    s6.setChecked(false);


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