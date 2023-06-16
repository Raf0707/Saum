package raf.tabiin.saum.ui.saum;

import static android.content.Context.MODE_PRIVATE;

import android.content.*;
import android.os.*;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

import raf.tabiin.saum.R;
import raf.tabiin.saum.util.SharedPreferencesUtil;
import raf.tabiin.saum.util.SharedPreferencesUtils;


public class RamadanSaumFragment extends Fragment implements View.OnClickListener {
    private Handler handler;
    private int countCheck = 0;
    private TextView itog;
    private SharedPreferences sPreff;
    private View vview;

    private ProgressBar postProgressBar;

    private Button reset;

    private CheckBox c1;
    private CheckBox c2;
    private CheckBox c3;
    private CheckBox c4;
    private CheckBox c5;
    private CheckBox c6;
    private CheckBox c7;
    private CheckBox c8;
    private CheckBox c9;
    private CheckBox c10;
    private CheckBox c11;
    private CheckBox c12;
    private CheckBox c13;
    private CheckBox c14;
    private CheckBox c15;
    private CheckBox c16;
    private CheckBox c17;
    private CheckBox c18;
    private CheckBox c19;
    private CheckBox c20;
    private CheckBox c21;
    private CheckBox c22;
    private CheckBox c23;
    private CheckBox c24;
    private CheckBox c25;
    private CheckBox c26;
    private CheckBox c27;
    private CheckBox c28;
    private CheckBox c29;
    private CheckBox c30;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post_ramadan, null);

        itog = view.findViewById(R.id.itogPost);

        postProgressBar = view.findViewById(R.id.postRamadanProgressBar);

        reset = view.findViewById(R.id.button_reset);

        reset.setOnClickListener(this);

        c1 = view.findViewById(R.id.r1);
        c2 = view.findViewById(R.id.r2);
        c3 = view.findViewById(R.id.r3);
        c4 = view.findViewById(R.id.r4);
        c5 = view.findViewById(R.id.r5);
        c6 = view.findViewById(R.id.r6);
        c7 = view.findViewById(R.id.r7);
        c8 = view.findViewById(R.id.r8);
        c9 = view.findViewById(R.id.r9);
        c10 = view.findViewById(R.id.r10);
        c11 = view.findViewById(R.id.r11);
        c12 = view.findViewById(R.id.r12);
        c13 = view.findViewById(R.id.r13);
        c14 = view.findViewById(R.id.r14);
        c15 = view.findViewById(R.id.r15);
        c16 = view.findViewById(R.id.r16);
        c17 = view.findViewById(R.id.r17);
        c18 = view.findViewById(R.id.r18);
        c19 = view.findViewById(R.id.r19);
        c20 = view.findViewById(R.id.r20);
        c21 = view.findViewById(R.id.r21);
        c22 = view.findViewById(R.id.r22);
        c23 = view.findViewById(R.id.r23);
        c24 = view.findViewById(R.id.r24);
        c25 = view.findViewById(R.id.r25);
        c26 = view.findViewById(R.id.r26);
        c27 = view.findViewById(R.id.r27);
        c28 = view.findViewById(R.id.r28);
        c29 = view.findViewById(R.id.r29);
        c30 = view.findViewById(R.id.r30);


        this.c1.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c2.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c3.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c4.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c5.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c6.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c7.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c8.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c9.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c10.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c11.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c12.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c16.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c17.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c18.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c19.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c20.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c21.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c22.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c23.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c24.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c25.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c26.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c27.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c28.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c29.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        this.c30.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        loadText();

        //exportDataToMainFragment();

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

        saveText();
        loadText();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_reset:
                if (countCheck != 0 && itog.getText().toString() != "0") onAlert();
                break;
        }

        saveText();
        loadText();
    }

    public void saveText() {
        //sPreff = requireContext().getSharedPreferences(String.valueOf(MODE_PRIVATE), 0);
        //sPreff = requireActivity().getPreferences(MODE_PRIVATE);
        //SharedPreferences.Editor ed = sPreff.edit();
        //ed.clear();

        SharedPreferencesUtil.saveBoolean(requireContext(), "c1", c1.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c2", c2.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c3", c3.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c4", c4.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c5", c5.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c6", c6.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c7", c7.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c8", c8.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c9", c9.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c10", c10.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c11", c11.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c12", c12.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c13", c13.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c14", c14.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c15", c15.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c16", c16.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c17", c17.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c18", c18.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c19", c19.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c20", c20.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c21", c21.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c22", c22.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c23", c23.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c24", c24.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c25", c25.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c26", c26.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c27", c27.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c28", c28.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c29", c29.isChecked());
        SharedPreferencesUtil.saveBoolean(requireContext(), "c30", c30.isChecked());



        /*

        ed.putBoolean("c1", c1.isChecked());
        ed.putBoolean("c2", c2.isChecked());
        ed.putBoolean("c3", c3.isChecked());
        ed.putBoolean("c4", c4.isChecked());
        ed.putBoolean("c5", c5.isChecked());
        ed.putBoolean("c6", c6.isChecked());
        ed.putBoolean("c7", c7.isChecked());
        ed.putBoolean("c8", c8.isChecked());
        ed.putBoolean("c9", c9.isChecked());
        ed.putBoolean("c10", c10.isChecked());
        ed.putBoolean("c11", c11.isChecked());
        ed.putBoolean("c12", c12.isChecked());
        ed.putBoolean("c13", c13.isChecked());
        ed.putBoolean("c14", c14.isChecked());
        ed.putBoolean("c15", c15.isChecked());
        ed.putBoolean("c16", c16.isChecked());
        ed.putBoolean("c17", c17.isChecked());
        ed.putBoolean("c18", c18.isChecked());
        ed.putBoolean("c19", c19.isChecked());
        ed.putBoolean("c20", c20.isChecked());
        ed.putBoolean("c21", c21.isChecked());
        ed.putBoolean("c22", c22.isChecked());
        ed.putBoolean("c23", c23.isChecked());
        ed.putBoolean("c24", c24.isChecked());
        ed.putBoolean("c25", c25.isChecked());
        ed.putBoolean("c26", c26.isChecked());
        ed.putBoolean("c27", c27.isChecked());
        ed.putBoolean("c28", c28.isChecked());
        ed.putBoolean("c29", c29.isChecked());
        ed.putBoolean("c30", c30.isChecked());

         */



        //ed.putString("saumRamadan", itog.getText().toString());
        SharedPreferencesUtil.saveString(requireContext(), "saumRamadan", itog.getText().toString());
        postProgressBar.setProgress(countCheck);
        countCheck = Integer.parseInt(itog.getText().toString());
        //ed.apply();
        //ed.commit();


        /*MainSaumFragment mainSaumFragment = new MainSaumFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ramadanProgress", countCheck);
        mainSaumFragment.setArguments(bundle);*/
        //getFragmentManager().beginTransaction().replace(R.id.containerFragment, mainSaumFragment).commitAllowingStateLoss();
    }

    public void loadText() {
        sPreff = requireActivity().getPreferences(MODE_PRIVATE);

        c1.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(), "c1"));
        c2.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c2"));
        c3.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c3"));
        c4.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c4"));
        c5.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c5"));
        c6.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c6"));
        c7.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c7"));
        c8.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c8"));
        c9.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c9"));
        c10.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c10"));
        c11.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c11"));
        c12.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c12"));
        c13.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c13"));
        c14.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c14"));
        c15.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c15"));
        c16.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c16"));
        c17.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c17"));
        c18.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c18"));
        c19.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c19"));
        c20.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c20"));
        c21.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c21"));
        c22.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c22"));
        c23.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c23"));
        c24.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c24"));
        c25.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c25"));
        c26.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c26"));
        c27.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c27"));
        c28.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c28"));
        c29.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c29"));
        c30.setChecked(SharedPreferencesUtil.loadBoolean(requireContext(),"c30"));



        //String tselText = sPreff.getString("saumRamadan", itog.getText().toString());
        itog.setText(SharedPreferencesUtils.getString(requireContext(), "saumRamadan", itog.getText().toString()));
        postProgressBar.setProgress(countCheck);
    }

    public void onAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Обновить счетчик дней?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                        c1.setChecked(false);
                        c2.setChecked(false);
                        c3.setChecked(false);
                        c4.setChecked(false);
                        c5.setChecked(false);
                        c6.setChecked(false);
                        c7.setChecked(false);
                        c8.setChecked(false);
                        c9.setChecked(false);
                        c10.setChecked(false);
                        c11.setChecked(false);
                        c12.setChecked(false);
                        c13.setChecked(false);
                        c14.setChecked(false);
                        c15.setChecked(false);
                        c16.setChecked(false);
                        c17.setChecked(false);
                        c18.setChecked(false);
                        c19.setChecked(false);
                        c20.setChecked(false);
                        c21.setChecked(false);
                        c22.setChecked(false);
                        c23.setChecked(false);
                        c24.setChecked(false);
                        c25.setChecked(false);
                        c26.setChecked(false);
                        c27.setChecked(false);
                        c28.setChecked(false);
                        c29.setChecked(false);
                        c30.setChecked(false);

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

    public void exportDataToMainFragment() {
        saveText();
        //MainSaumFragment mainSaumFragment = new MainSaumFragment();
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        bundle.putInt("ramadanProgress", countCheck);
        fragment.setArguments(bundle);
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        //exportDataToMainFragment();
        saveText();
        loadText();
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        //exportDataToMainFragment();
        saveText();
        loadText();
        super.onStop();
    }

    @Override
    public void onPause() {
        //exportDataToMainFragment();
        saveText();
        loadText();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        //exportDataToMainFragment();
        saveText();
        loadText();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        //exportDataToMainFragment();
        saveText();
        loadText();
        super.onDestroy();
    }
}