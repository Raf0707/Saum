package raf.tabiin.saum.ui.saum;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.Objects;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.FragmentMainSaumBinding;
import raf.tabiin.saum.domain.database.SaumDatabase;
import raf.tabiin.saum.domain.database.SaumDatabase_Impl;
import raf.tabiin.saum.domain.repository.SaumRepository;
import raf.tabiin.saum.ui.saum.monday_thursday.MTFragment;
import raf.tabiin.saum.ui.saum.monday_thursday.SaumViewModel;

// android:enableOnBackInvokedCallback="true"

public class MainSaumFragment extends Fragment {
    FragmentMainSaumBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentMainSaumBinding.inflate(getLayoutInflater());

        return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        b.todaySaumCard.setOnClickListener(v -> {

        });

        b.ramadanSaumCard.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new RamadanSaumFragment())
                    .commit();
        });

        b.shaawalSaumCard.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new ShaawalSaumFragment())
                    .commit();
        });

        b.zulHijaSaumCard.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new ZulHijaSaumFragment())
                    .commit();
        });

        b.monThursSaumCard.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new MTFragment())
                    .commit();
        });

        b.wdSaumCard.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new WhiteDaysSaumFragment())
                    .commit();
        });

        b.muharramSaumCard.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new MuharramSaumFragment())
                    .commit();
        });

        b.daudSaumCard.setOnClickListener(v -> {

        });

        b.nazarSaumCard.setOnClickListener(v -> {

        });

        b.kafaratSaumCard.setOnClickListener(v -> {

        });

        b.saumInfoCard.setOnClickListener(v -> {

        });

    }

}