package raf.tabiin.saum.ui.saum;

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

        });

        binding.ramadanSaumCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainSaumFragment_to_ramadanSaumFragment);
        });

        binding.shaawalSaumCard.setOnClickListener(v -> {
            /* getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new ShaawalSaumFragment())
                    .commit(); */
        });

        binding.zulHijaSaumCard.setOnClickListener(v -> {
            /* getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new ZulHijaSaumFragment())
                    .commit(); */
        });

        binding.monThursSaumCard.setOnClickListener(v -> {
            /* getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new MTFragment())
                    .commit(); */
        });

        binding.wdSaumCard.setOnClickListener(v -> {
            /* getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new WhiteDaysSaumFragment())
                    .commit(); */
        });

        binding.muharramSaumCard.setOnClickListener(v -> {
            /* getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new MuharramSaumFragment())
                    .commit(); */
        });
    }

}