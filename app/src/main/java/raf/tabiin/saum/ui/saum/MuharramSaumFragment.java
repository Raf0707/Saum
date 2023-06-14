package raf.tabiin.saum.ui.saum;

import static android.content.Context.MODE_PRIVATE;

import android.content.*;
import android.os.*;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import raf.tabiin.saum.R;


public class MuharramSaumFragment extends Fragment implements View.OnClickListener {
    private Handler handler;
    private int countCheck = 0;
    private TextView itog;
    private SharedPreferences sPreff;
    private View vview;

    private ProgressBar postProgressBar;

    private Button reset;

    private CheckBox m1;
    private CheckBox m2;
    private CheckBox m3;
    private CheckBox m4;
    private CheckBox m5;
    private CheckBox m6;
    private CheckBox m7;
    private CheckBox m8;
    private CheckBox m9;
    private CheckBox m10;
    private CheckBox m11;
    private CheckBox m12;
    private CheckBox m13;
    private CheckBox m14;
    private CheckBox m15;
    private CheckBox m16;
    private CheckBox m17;
    private CheckBox m18;
    private CheckBox m19;
    private CheckBox m20;
    private CheckBox m21;
    private CheckBox m22;
    private CheckBox m23;
    private CheckBox m24;
    private CheckBox m25;
    private CheckBox m26;
    private CheckBox m27;
    private CheckBox m28;
    private CheckBox m29;
    private CheckBox m30;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_muharram_saum, null);

        itog = view.findViewById(R.id.itogPost);

        postProgressBar = view.findViewById(R.id.postRamadanProgressBar);

        reset = view.findViewById(R.id.button_reset);

        reset.setOnClickListener(this);

        m1 = view.findViewById(R.id.m1);
        m2 = view.findViewById(R.id.m2);
        m3 = view.findViewById(R.id.m3);
        m4 = view.findViewById(R.id.m4);
        m5 = view.findViewById(R.id.m5);
        m6 = view.findViewById(R.id.m6);
        m7 = view.findViewById(R.id.m7);
        m8 = view.findViewById(R.id.m8);
        m9 = view.findViewById(R.id.m9);
        m10 = view.findViewById(R.id.m10);
        m11 = view.findViewById(R.id.m11);
        m12 = view.findViewById(R.id.m12);
        m13 = view.findViewById(R.id.m13);
        m14 = view.findViewById(R.id.m14);
        m15 = view.findViewById(R.id.m15);
        m16 = view.findViewById(R.id.m16);
        m17 = view.findViewById(R.id.m17);
        m18 = view.findViewById(R.id.m18);
        m19 = view.findViewById(R.id.m19);
        m20 = view.findViewById(R.id.m20);
        m21 = view.findViewById(R.id.m21);
        m22 = view.findViewById(R.id.m22);
        m23 = view.findViewById(R.id.m23);
        m24 = view.findViewById(R.id.m24);
        m25 = view.findViewById(R.id.m25);
        m26 = view.findViewById(R.id.m26);
        m27 = view.findViewById(R.id.m27);
        m28 = view.findViewById(R.id.m28);
        m29 = view.findViewById(R.id.m29);
        m30 = view.findViewById(R.id.m30);


        this.m1.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m2.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m3.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m4.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m5.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m6.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m7.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m8.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m9.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m10.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m11.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m12.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m16.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m17.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m18.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m19.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m20.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m21.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m22.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m23.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m24.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m25.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m26.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m27.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m28.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m29.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.m30.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

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

        ed.putBoolean("m1", m1.isChecked());
        ed.putBoolean("m2", m2.isChecked());
        ed.putBoolean("m3", m3.isChecked());
        ed.putBoolean("m4", m4.isChecked());
        ed.putBoolean("m5", m5.isChecked());
        ed.putBoolean("m6", m6.isChecked());
        ed.putBoolean("m7", m7.isChecked());
        ed.putBoolean("m8", m8.isChecked());
        ed.putBoolean("m9", m9.isChecked());
        ed.putBoolean("m10", m10.isChecked());
        ed.putBoolean("m11", m11.isChecked());
        ed.putBoolean("m12", m12.isChecked());
        ed.putBoolean("m13", m13.isChecked());
        ed.putBoolean("m14", m14.isChecked());
        ed.putBoolean("m15", m15.isChecked());
        ed.putBoolean("m16", m16.isChecked());
        ed.putBoolean("m17", m17.isChecked());
        ed.putBoolean("m18", m18.isChecked());
        ed.putBoolean("m19", m19.isChecked());
        ed.putBoolean("m20", m20.isChecked());
        ed.putBoolean("m21", m21.isChecked());
        ed.putBoolean("m22", m22.isChecked());
        ed.putBoolean("m23", m23.isChecked());
        ed.putBoolean("m24", m24.isChecked());
        ed.putBoolean("m25", m25.isChecked());
        ed.putBoolean("m26", m26.isChecked());
        ed.putBoolean("m27", m27.isChecked());
        ed.putBoolean("m28", m28.isChecked());
        ed.putBoolean("m29", m29.isChecked());
        ed.putBoolean("m30", m30.isChecked());



        ed.putString("saumMuharram", itog.getText().toString());
        postProgressBar.setProgress(countCheck);
        countCheck = Integer.parseInt(itog.getText().toString());
        ed.apply();
    }

    public void loadText() {
        sPreff = getActivity().getPreferences(MODE_PRIVATE);

        m1.setChecked(sPreff.getBoolean("m1", false));
        m2.setChecked(sPreff.getBoolean("m2", false));
        m3.setChecked(sPreff.getBoolean("m3", false));
        m4.setChecked(sPreff.getBoolean("m4", false));
        m5.setChecked(sPreff.getBoolean("m5", false));
        m6.setChecked(sPreff.getBoolean("m6", false));
        m7.setChecked(sPreff.getBoolean("m7", false));
        m8.setChecked(sPreff.getBoolean("m8", false));
        m9.setChecked(sPreff.getBoolean("m9", false));
        m10.setChecked(sPreff.getBoolean("m10", false));
        m11.setChecked(sPreff.getBoolean("m11", false));
        m12.setChecked(sPreff.getBoolean("m12", false));
        m13.setChecked(sPreff.getBoolean("m13", false));
        m14.setChecked(sPreff.getBoolean("m14", false));
        m15.setChecked(sPreff.getBoolean("m15", false));
        m16.setChecked(sPreff.getBoolean("m16", false));
        m17.setChecked(sPreff.getBoolean("m17", false));
        m18.setChecked(sPreff.getBoolean("m18", false));
        m19.setChecked(sPreff.getBoolean("m19", false));
        m20.setChecked(sPreff.getBoolean("m20", false));
        m21.setChecked(sPreff.getBoolean("m21", false));
        m22.setChecked(sPreff.getBoolean("m22", false));
        m23.setChecked(sPreff.getBoolean("m23", false));
        m24.setChecked(sPreff.getBoolean("m24", false));
        m25.setChecked(sPreff.getBoolean("m25", false));
        m26.setChecked(sPreff.getBoolean("m26", false));
        m27.setChecked(sPreff.getBoolean("m27", false));
        m28.setChecked(sPreff.getBoolean("m28", false));
        m29.setChecked(sPreff.getBoolean("m29", false));
        m30.setChecked(sPreff.getBoolean("m30", false));



        String tselText = sPreff.getString("saumMuharram", itog.getText().toString());
        itog.setText(tselText);
        postProgressBar.setProgress(countCheck);
    }

    public void onAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Обновить счетчик дней?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    m1.setChecked(false);
                    m2.setChecked(false);
                    m3.setChecked(false);
                    m4.setChecked(false);
                    m5.setChecked(false);
                    m6.setChecked(false);
                    m7.setChecked(false);
                    m8.setChecked(false);
                    m9.setChecked(false);
                    m10.setChecked(false);
                    m11.setChecked(false);
                    m12.setChecked(false);
                    m13.setChecked(false);
                    m14.setChecked(false);
                    m15.setChecked(false);
                    m16.setChecked(false);
                    m17.setChecked(false);
                    m18.setChecked(false);
                    m19.setChecked(false);
                    m20.setChecked(false);
                    m21.setChecked(false);
                    m22.setChecked(false);
                    m23.setChecked(false);
                    m24.setChecked(false);
                    m25.setChecked(false);
                    m26.setChecked(false);
                    m27.setChecked(false);
                    m28.setChecked(false);
                    m29.setChecked(false);
                    m30.setChecked(false);

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