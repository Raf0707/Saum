package ru.tabiin.ramadan.ui.post;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ru.tabiin.ramadan.R;
import ru.tabiin.ramadan.databinding.FragmentRamadanPostBinding;

public class RamadanPostFragment extends Fragment {
    FragmentRamadanPostBinding binding;
    private Handler handler;
    private int countCheck = 0;
    private SharedPreferences sPreff;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRamadanPostBinding.inflate(getLayoutInflater());

        binding.r1.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r2.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r3.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r4.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r5.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r6.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r7.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r8.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r9.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r10.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r11.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r12.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r16.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r17.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r18.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r19.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r20.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r21.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r22.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r23.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r24.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r25.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r26.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r27.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r28.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r29.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.r30.setOnCheckedChangeListener((buttonView, isChecked) -> onCountCheck(isChecked));

        binding.buttonReset.setOnClickListener(v -> {
            if (countCheck != 0 && binding.itoooog.getText().toString() != "0")
                onAlert();
        });

        return binding.getRoot();
    }

    public void onCountCheck(boolean isChecked) {
        if (isChecked) {
            countCheck++;
            binding.itoooog.setText(Integer.toString(countCheck));
            binding.postProgressBar.setProgress(countCheck);
        }
        if (!isChecked && countCheck != 0) {
            countCheck--;
            if (countCheck < 0) countCheck = 0;
            binding.itoooog.setText(Integer.toString(countCheck));
            binding.postProgressBar.setProgress(countCheck);
        }

    }

    public void saveText() {
        sPreff = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPreff.edit();

        ed.putBoolean("binding.r1", binding.r1.isChecked());
        ed.putBoolean("binding.r2", binding.r2.isChecked());
        ed.putBoolean("binding.r3", binding.r3.isChecked());
        ed.putBoolean("binding.r4", binding.r4.isChecked());
        ed.putBoolean("binding.r5", binding.r5.isChecked());
        ed.putBoolean("binding.r6", binding.r6.isChecked());
        ed.putBoolean("binding.r7", binding.r7.isChecked());
        ed.putBoolean("binding.r8", binding.r8.isChecked());
        ed.putBoolean("binding.r9", binding.r9.isChecked());
        ed.putBoolean("binding.r10", binding.r10.isChecked());
        ed.putBoolean("binding.r11", binding.r11.isChecked());
        ed.putBoolean("binding.r12", binding.r12.isChecked());
        ed.putBoolean("binding.r13", binding.r13.isChecked());
        ed.putBoolean("binding.r14", binding.r14.isChecked());
        ed.putBoolean("binding.r15", binding.r15.isChecked());
        ed.putBoolean("binding.r16", binding.r16.isChecked());
        ed.putBoolean("binding.r17", binding.r17.isChecked());
        ed.putBoolean("binding.r18", binding.r18.isChecked());
        ed.putBoolean("binding.r19", binding.r19.isChecked());
        ed.putBoolean("binding.r20", binding.r20.isChecked());
        ed.putBoolean("binding.r21", binding.r21.isChecked());
        ed.putBoolean("binding.r22", binding.r22.isChecked());
        ed.putBoolean("binding.r23", binding.r23.isChecked());
        ed.putBoolean("binding.r24", binding.r24.isChecked());
        ed.putBoolean("binding.r25", binding.r25.isChecked());
        ed.putBoolean("binding.r26", binding.r26.isChecked());
        ed.putBoolean("binding.r27", binding.r27.isChecked());
        ed.putBoolean("binding.r28", binding.r28.isChecked());
        ed.putBoolean("binding.r29", binding.r29.isChecked());
        ed.putBoolean("binding.r30", binding.r30.isChecked());



        ed.putString("ПродержалПост", binding.itoooog.getText().toString());
        //binding.postProgressBar.setProgress(countCheck);
        countCheck = Integer.parseInt(binding.itoooog.getText().toString());
        ed.apply();
    }

    public void loadText() {
        sPreff = getActivity().getPreferences(MODE_PRIVATE);

        binding.r1.setChecked(sPreff.getBoolean("binding.r1", false));
        binding.r2.setChecked(sPreff.getBoolean("binding.r2", false));
        binding.r3.setChecked(sPreff.getBoolean("binding.r3", false));
        binding.r4.setChecked(sPreff.getBoolean("binding.r4", false));
        binding.r5.setChecked(sPreff.getBoolean("binding.r5", false));
        binding.r6.setChecked(sPreff.getBoolean("binding.r6", false));
        binding.r7.setChecked(sPreff.getBoolean("binding.r7", false));
        binding.r8.setChecked(sPreff.getBoolean("binding.r8", false));
        binding.r9.setChecked(sPreff.getBoolean("binding.r9", false));
        binding.r10.setChecked(sPreff.getBoolean("binding.r10", false));
        binding.r11.setChecked(sPreff.getBoolean("binding.r11", false));
        binding.r12.setChecked(sPreff.getBoolean("binding.r12", false));
        binding.r13.setChecked(sPreff.getBoolean("binding.r13", false));
        binding.r14.setChecked(sPreff.getBoolean("binding.r14", false));
        binding.r15.setChecked(sPreff.getBoolean("binding.r15", false));
        binding.r16.setChecked(sPreff.getBoolean("binding.r16", false));
        binding.r17.setChecked(sPreff.getBoolean("binding.r17", false));
        binding.r18.setChecked(sPreff.getBoolean("binding.r18", false));
        binding.r19.setChecked(sPreff.getBoolean("binding.r19", false));
        binding.r20.setChecked(sPreff.getBoolean("binding.r20", false));
        binding.r21.setChecked(sPreff.getBoolean("binding.r21", false));
        binding.r22.setChecked(sPreff.getBoolean("binding.r22", false));
        binding.r23.setChecked(sPreff.getBoolean("binding.r23", false));
        binding.r24.setChecked(sPreff.getBoolean("binding.r24", false));
        binding.r25.setChecked(sPreff.getBoolean("binding.r25", false));
        binding.r26.setChecked(sPreff.getBoolean("binding.r26", false));
        binding.r27.setChecked(sPreff.getBoolean("binding.r27", false));
        binding.r28.setChecked(sPreff.getBoolean("binding.r28", false));
        binding.r29.setChecked(sPreff.getBoolean("binding.r29", false));
        binding.r30.setChecked(sPreff.getBoolean("binding.r30", false));



        String tselText = sPreff.getString("ПродержалПост",
                binding.itoooog.getText().toString());
        binding.itoooog.setText(tselText);
        binding.postProgressBar.setProgress(countCheck);

    }

    public void onAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Обновить счетчик дней?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                        binding.r1.setChecked(false);
                        binding.r2.setChecked(false);
                        binding.r3.setChecked(false);
                        binding.r4.setChecked(false);
                        binding.r5.setChecked(false);
                        binding.r6.setChecked(false);
                        binding.r7.setChecked(false);
                        binding.r8.setChecked(false);
                        binding.r9.setChecked(false);
                        binding.r10.setChecked(false);
                        binding.r11.setChecked(false);
                        binding.r12.setChecked(false);
                        binding.r13.setChecked(false);
                        binding.r14.setChecked(false);
                        binding.r15.setChecked(false);
                        binding.r16.setChecked(false);
                        binding.r17.setChecked(false);
                        binding.r18.setChecked(false);
                        binding.r19.setChecked(false);
                        binding.r20.setChecked(false);
                        binding.r21.setChecked(false);
                        binding.r22.setChecked(false);
                        binding.r23.setChecked(false);
                        binding.r24.setChecked(false);
                        binding.r25.setChecked(false);
                        binding.r26.setChecked(false);
                        binding.r27.setChecked(false);
                        binding.r28.setChecked(false);
                        binding.r29.setChecked(false);
                        binding.r30.setChecked(false);

                        binding.itoooog.setText("0");
                        countCheck = 0;
                        binding.postProgressBar.setProgress(countCheck);

                    })

                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }

    /*
    @Override
    public void onStart() {
        //loadText();
        Log.d("onStart", "onStart");
        super.onStart();
    }

    @Override
    public void onPause() {
        saveText();
        //loadText();
        Log.d("onPause", "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        saveText();
        //loadText();
        Log.d("onStop", "onStop");
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        saveText();
        Log.d("onSaveInstanceState", "onSaveInstanceState");
        //loadText();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        saveText();
        //loadText();
        Log.d("onViewStateRestored", "onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }


     */
    @Override
    public void onDestroy() {
        saveText();
        loadText();
        Log.d("onDestroy", "onDestroy");
        super.onDestroy();
    }

}