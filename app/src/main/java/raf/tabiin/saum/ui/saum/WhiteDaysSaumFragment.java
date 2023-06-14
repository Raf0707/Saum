package raf.tabiin.saum.ui.saum;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.FragmentWhiteDaysSaumBinding;

public class WhiteDaysSaumFragment extends Fragment implements View.OnClickListener {

    FragmentWhiteDaysSaumBinding b;

    private SharedPreferences sPreff;

    int counterMuharram = 0;
    int counterSafar = 0;
    int counterRaw = 0;
    int counterRas = 0;
    int counterDaw = 0;
    int counterDas = 0;
    int counterRaj = 0;
    int counterShab = 0;
    int counterShaw = 0;
    int counterZk = 0;
    int counterZh = 0;

    private CheckBox muh13;
    private CheckBox muh14;
    private CheckBox muh15;
    private ProgressBar muhDW;

    private CheckBox saf13;
    private CheckBox saf14;
    private CheckBox saf15;
    private ProgressBar safDW;

    private CheckBox raw13;
    private CheckBox raw14;
    private CheckBox raw15;
    private ProgressBar rawDW;

    private CheckBox ras13;
    private CheckBox ras14;
    private CheckBox ras15;
    private ProgressBar rasDW;

    private CheckBox daw13;
    private CheckBox daw14;
    private CheckBox daw15;
    private ProgressBar dawDW;

    private CheckBox das13;
    private CheckBox das14;
    private CheckBox das15;
    private ProgressBar dasDW;

    private CheckBox raj13;
    private CheckBox raj14;
    private CheckBox raj15;
    private ProgressBar rajDW;

    private CheckBox shab13;
    private CheckBox shab14;
    private CheckBox shab15;
    private ProgressBar shabDW;

    private CheckBox shaw13;
    private CheckBox shaw14;
    private CheckBox shaw15;
    private ProgressBar shawDW;

    private CheckBox zk13;
    private CheckBox zk14;
    private CheckBox zk15;
    private ProgressBar zkDW;

    private CheckBox zh13;
    private CheckBox zh14;
    private CheckBox zh15;
    private ProgressBar zhDW;

    private Button reset;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = FragmentWhiteDaysSaumBinding.inflate(getLayoutInflater());
        sPreff = getActivity().getPreferences(Context.MODE_PRIVATE);

        muh13 = b.wd13muh;
        muh14 = b.wd14muh;
        muh15 = b.wd15muh;
        muhDW = b.progressWDMuh;

        saf13 = b.wd13saf;
        saf14 = b.wd14saf;
        saf15 = b.wd15saf;
        safDW = b.progressWDSaf;

        raw13 = b.wd13raw;
        raw14 = b.wd14raw;
        raw15 = b.wd15raw;
        rawDW = b.progressWDRaw;

        ras13 = b.wd13ras;
        ras14 = b.wd14ras;
        ras15 = b.wd15ras;
        rasDW = b.progressWDRas;

        daw13 = b.wd13daw;
        daw14 = b.wd14daw;
        daw15 = b.wd15daw;
        dawDW = b.progressWDDaw;

        das13 = b.wd13das;
        das14 = b.wd14das;
        das15 = b.wd15das;
        dasDW = b.progressWDDas;

        raj13 = b.wd13raj;
        raj14 = b.wd14raj;
        raj15 = b.wd15raj;
        rajDW = b.progressWDRaj;

        shab13 = b.wd13shab;
        shab14 = b.wd14shab;
        shab15 = b.wd15shab;
        shabDW = b.progressWDshab;

        shaw13 = b.wd13shaw;
        shaw14 = b.wd14shaw;
        shaw15 = b.wd15shaw;
        shawDW = b.progressWDShaw;

        zk13 = b.wd13zk;
        zk14 = b.wd14zk;
        zk15 = b.wd15zk;
        zkDW = b.progressWDzk;

        zh13 = b.wd13zh;
        zh14 = b.wd14zh;
        zh15 = b.wd15zh;
        zhDW = b.progressWDzh;

        reset = b.buttonResetS;
        reset.setOnClickListener(v -> {
            onAlert();
        });

        this.muh13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountMuh(isChecked));

        this.muh14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountMuh(isChecked));

        this.muh15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountMuh(isChecked));

        this.saf13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountSaf(isChecked));

        this.saf14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountSaf(isChecked));

        this.saf15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountSaf(isChecked));

        this.raw13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountRaw(isChecked));

        this.raw14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountRaw(isChecked));

        this.raw15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountRaw(isChecked));

        this.ras13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountRas(isChecked));

        this.ras14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountRas(isChecked));

        this.ras15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountRas(isChecked));

        this.daw13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountDaw(isChecked));

        this.daw14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountDaw(isChecked));

        this.daw15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountDaw(isChecked));

        this.das13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountDas(isChecked));

        this.das14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountDas(isChecked));

        this.das15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountDas(isChecked));

        this.raj13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountRaj(isChecked));

        this.raj14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountRaj(isChecked));

        this.raj15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountRaj(isChecked));

        this.shab13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountShab(isChecked));

        this.shab14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountShab(isChecked));

        this.shab15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountShab(isChecked));

        this.shaw13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountShaw(isChecked));

        this.shaw14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountShaw(isChecked));

        this.shaw15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountShaw(isChecked));

        this.zk13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountZk(isChecked));

        this.zk14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountZk(isChecked));

        this.zk15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountZk(isChecked));

        this.zh13.setOnCheckedChangeListener((buttonView, isChecked) -> onCountZh(isChecked));

        this.zh14.setOnCheckedChangeListener((buttonView, isChecked) -> onCountZh(isChecked));

        this.zh15.setOnCheckedChangeListener((buttonView, isChecked) -> onCountZh(isChecked));

        loadText();

        return b.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_reset:
                onAlert();
                break;
        }

    }

    public void onAlert(){
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Обновить счетчик дней?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    muh13.setChecked(false);
                    muh14.setChecked(false);
                    muh15.setChecked(false);
                    saf13.setChecked(false);
                    saf14.setChecked(false);
                    saf15.setChecked(false);
                    raw13.setChecked(false);
                    raw14.setChecked( false);
                    raw15.setChecked(false);
                    ras13.setChecked(false);
                    ras14.setChecked(false);
                    ras15.setChecked(false);
                    daw13.setChecked(false);
                    daw14.setChecked(false);
                    daw15.setChecked(false);
                    das13.setChecked(false);
                    das14.setChecked(false);
                    das15.setChecked(false);
                    raj13.setChecked(false);
                    raj14.setChecked(false);
                    raj15.setChecked(false);
                    shab13.setChecked(false);
                    shab14.setChecked(false);
                    shab15.setChecked(false);
                    shaw13.setChecked(false);
                    shaw14.setChecked(false);
                    shaw15.setChecked(false);
                    zk13.setChecked(false);
                    zk14.setChecked(false);
                    zk15.setChecked(false);
                    zh13.setChecked(false);
                    zh14.setChecked(false);
                    zh15.setChecked(false);

                    counterMuharram = 0;
                    counterSafar = 0;
                    counterRaw = 0;
                    counterRas = 0;
                    counterDaw = 0;
                    counterDas = 0;
                    counterRaj = 0;
                    counterShab = 0;
                    counterShaw = 0;
                    counterZk = 0;
                    counterZh = 0;

                    muhDW.setProgress(counterMuharram);
                    safDW.setProgress(counterSafar);
                    rawDW.setProgress(counterRaw);
                    rasDW.setProgress(counterRas);
                    dawDW.setProgress(counterDaw);
                    dasDW.setProgress(counterDas);
                    rajDW.setProgress(counterRaj);
                    shabDW.setProgress(counterShab);
                    shawDW.setProgress(counterShaw);
                    zkDW.setProgress(counterZk);
                    zhDW.setProgress(counterZh);


                    saveText();
                    loadText();
                })

                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }

    public void saveText() {
        sPreff = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPreff.edit();

        ed.putBoolean("muh13", muh13.isChecked());
        ed.putBoolean("muh14", muh14.isChecked());
        ed.putBoolean("muh15", muh15.isChecked());
        ed.putBoolean("saf13", saf13.isChecked());
        ed.putBoolean("saf14", saf14.isChecked());
        ed.putBoolean("saf15", saf15.isChecked());
        ed.putBoolean("raw13", raw13.isChecked());
        ed.putBoolean("raw14", raw14.isChecked());
        ed.putBoolean("raw15", raw15.isChecked());
        ed.putBoolean("ras13", ras13.isChecked());
        ed.putBoolean("ras14", ras14.isChecked());
        ed.putBoolean("ras15", ras15.isChecked());
        ed.putBoolean("daw13", daw13.isChecked());
        ed.putBoolean("daw14", daw14.isChecked());
        ed.putBoolean("daw15", daw15.isChecked());
        ed.putBoolean("das13", das13.isChecked());
        ed.putBoolean("das14", das14.isChecked());
        ed.putBoolean("das15", das15.isChecked());
        ed.putBoolean("raj13", raj13.isChecked());
        ed.putBoolean("raj14", raj14.isChecked());
        ed.putBoolean("raj15", raj15.isChecked());
        ed.putBoolean("shab13", shab13.isChecked());
        ed.putBoolean("shab14", shab14.isChecked());
        ed.putBoolean("shab15", shab15.isChecked());
        ed.putBoolean("shaw13", shaw13.isChecked());
        ed.putBoolean("shaw14", shaw14.isChecked());
        ed.putBoolean("shaw15", shaw15.isChecked());
        ed.putBoolean("zk13", zk13.isChecked());
        ed.putBoolean("zk14", zk14.isChecked());
        ed.putBoolean("zk15", zk15.isChecked());
        ed.putBoolean("zh13", zh13.isChecked());
        ed.putBoolean("zh14", zh14.isChecked());
        ed.putBoolean("zh15", zh15.isChecked());
        
        muhDW.setProgress(counterMuharram);
        safDW.setProgress(counterSafar);
        rawDW.setProgress(counterRaw);
        rasDW.setProgress(counterRas);
        dawDW.setProgress(counterDaw);
        dasDW.setProgress(counterDas);
        rajDW.setProgress(counterRaj);
        shabDW.setProgress(counterShab);
        shawDW.setProgress(counterShaw);
        zkDW.setProgress(counterZk);
        zhDW.setProgress(counterZh);
        
        ed.apply();
    }

    public void loadText() {
        muh13.setChecked(sPreff.getBoolean("muh13", false));
        muh14.setChecked(sPreff.getBoolean("muh14", false));
        muh15.setChecked(sPreff.getBoolean("muh15", false));
        saf13.setChecked(sPreff.getBoolean("saf13", false));
        saf14.setChecked(sPreff.getBoolean("saf14", false));
        saf15.setChecked(sPreff.getBoolean("saf15", false));
        raw13.setChecked(sPreff.getBoolean("raw13", false));
        raw14.setChecked(sPreff.getBoolean("raw14", false));
        raw15.setChecked(sPreff.getBoolean("raw15", false));
        ras13.setChecked(sPreff.getBoolean("ras13", false));
        ras14.setChecked(sPreff.getBoolean("ras14", false));
        ras15.setChecked(sPreff.getBoolean("ras15", false));
        daw13.setChecked(sPreff.getBoolean("daw13", false));
        daw14.setChecked(sPreff.getBoolean("daw14", false));
        daw15.setChecked(sPreff.getBoolean("daw15", false));
        das13.setChecked(sPreff.getBoolean("das13", false));
        das14.setChecked(sPreff.getBoolean("das14", false));
        das15.setChecked(sPreff.getBoolean("das15", false));
        raj13.setChecked(sPreff.getBoolean("raj13", false));
        raj14.setChecked(sPreff.getBoolean("raj14", false));
        raj15.setChecked(sPreff.getBoolean("raj15", false));
        shab13.setChecked(sPreff.getBoolean("shab13", false));
        shab14.setChecked(sPreff.getBoolean("shab14", false));
        shab15.setChecked(sPreff.getBoolean("shab15", false));
        shaw13.setChecked(sPreff.getBoolean("shaw13", false));
        shaw14.setChecked(sPreff.getBoolean("shaw14", false));
        shaw15.setChecked(sPreff.getBoolean("shaw15", false));
        zk13.setChecked(sPreff.getBoolean("zk13", false));
        zk14.setChecked(sPreff.getBoolean("zk14", false));
        zk15.setChecked(sPreff.getBoolean("zk15", false));
        zh13.setChecked(sPreff.getBoolean("zh13", false));
        zh14.setChecked(sPreff.getBoolean("zh14", false));
        zh15.setChecked(sPreff.getBoolean("zh15", false));

        muhDW.setProgress(counterMuharram);
        safDW.setProgress(counterSafar);
        rawDW.setProgress(counterRaw);
        rasDW.setProgress(counterRas);
        dawDW.setProgress(counterDaw);
        dasDW.setProgress(counterDas);
        rajDW.setProgress(counterRaj);
        shabDW.setProgress(counterShab);
        shawDW.setProgress(counterShaw);
        zkDW.setProgress(counterZk);
        zhDW.setProgress(counterZh);
    }

    public void onCountMuh(boolean isChecked) {
        if (isChecked) {
            counterMuharram++;
            muhDW.setProgress(counterMuharram);
        }
        if (!isChecked && counterMuharram != 0) {
            counterMuharram--;
            if (counterMuharram < 0) counterMuharram = 0;
            muhDW.setProgress(counterMuharram);
        }
    }

    public void onCountSaf(boolean isChecked) {
        if (isChecked) {
            counterSafar++;
            safDW.setProgress(counterSafar);
        }
        if (!isChecked && counterSafar != 0) {
            counterSafar--;
            if (counterSafar < 0) counterSafar = 0;
            safDW.setProgress(counterSafar);
        }
    }

    public void onCountRaw(boolean isChecked) {
        if (isChecked) {
            counterRaw++;
            rawDW.setProgress(counterRaw);
        }
        if (!isChecked && counterRaw != 0) {
            counterRaw--;
            if (counterRaw < 0) counterRaw = 0;
            rawDW.setProgress(counterRaw);
        }
    }

    public void onCountRas(boolean isChecked) {
        if (isChecked) {
            counterRas++;
            rasDW.setProgress(counterRas);
        }
        if (!isChecked && counterRas != 0) {
            counterRas--;
            if (counterRas < 0) counterRas = 0;
            rasDW.setProgress(counterRas);
        }
    }

    public void onCountDaw(boolean isChecked) {
        if (isChecked) {
            counterDaw++;
            dawDW.setProgress(counterDaw);
        }
        if (!isChecked && counterDaw != 0) {
            counterDaw--;
            if (counterDaw < 0) counterDaw = 0;
            dawDW.setProgress(counterDaw);
        }
    }

    public void onCountDas(boolean isChecked) {
        if (isChecked) {
            counterDas++;
            dasDW.setProgress(counterDas);
        }
        if (!isChecked && counterDas != 0) {
            counterDas--;
            if (counterDas < 0) counterDas = 0;
            dasDW.setProgress(counterDas);
        }
    }

    public void onCountRaj(boolean isChecked) {
        if (isChecked) {
            counterRaj++;
            rajDW.setProgress(counterRaj);
        }
        if (!isChecked && counterRaj != 0) {
            counterRaj--;
            if (counterRaj < 0) counterRaj = 0;
            rajDW.setProgress(counterRaj);
        }
    }

    public void onCountShab(boolean isChecked) {
        if (isChecked) {
            counterShab++;
            shabDW.setProgress(counterShab);
        }
        if (!isChecked && counterShab != 0) {
            counterShab--;
            if (counterShab < 0) counterShab = 0;
            shabDW.setProgress(counterShab);
        }
    }

    public void onCountShaw(boolean isChecked) {
        if (isChecked) {
            counterShaw++;
            shawDW.setProgress(counterShaw);
        }
        if (!isChecked && counterShaw != 0) {
            counterShaw--;
            if (counterShaw < 0) counterShaw = 0;
            shawDW.setProgress(counterShaw);
        }
    }

    public void onCountZk(boolean isChecked) {
        if (isChecked) {
            counterZk++;
            zkDW.setProgress(counterZk);
        }
        if (!isChecked && counterZk != 0) {
            counterZk--;
            if (counterZk < 0) counterZk = 0;
            zkDW.setProgress(counterZk);
        }
    }

    public void onCountZh(boolean isChecked) {
        if (isChecked) {
            counterZh++;
            zhDW.setProgress(counterZh);
        }
        if (!isChecked && counterZh != 0) {
            counterZh--;
            if (counterZh < 0) counterZh = 0;
            zhDW.setProgress(counterZh);
        }
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