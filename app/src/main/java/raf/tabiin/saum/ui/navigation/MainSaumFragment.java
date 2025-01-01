package raf.tabiin.saum.ui.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.FragmentMainSaumBinding;

public class MainSaumFragment extends Fragment {
    FragmentMainSaumBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainSaumBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        binding.todaySaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_todaySaumFragment);
        });

        binding.ramadanSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_ramadanSaumFragment);
        });

        binding.shaawalSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_shaawalSaumFragment);
        });

        binding.zulHijaSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_zulHijaSaumFragment);
        });

        binding.monThursSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_MTFragment);
        });

        binding.wdSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_whiteDaysSaumFragment);
        });

        binding.muharramSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_muharramSaumFragment);
        });

        binding.nazarSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_nazrFragment);
        });

        binding.kafaratSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_kafaratSaumFragment);
        });

        binding.daudSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_dawoodSaumFragment);
        });

        binding.saumInfoCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_halalAndHaramDaysSaumFragment);
        });
    }

}