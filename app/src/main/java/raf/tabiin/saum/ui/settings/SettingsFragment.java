package raf.tabiin.saum.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.switchmaterial.SwitchMaterial;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.FragmentSettingsBinding;
import raf.tabiin.saum.ui.about_app.AppAboutFragment;
import raf.tabiin.saum.util.SharedPreferencesUtils;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    private SwitchMaterial switchMaterial;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container,false);
        switchMaterial = (SwitchMaterial) binding.dynamicColorsSwitch;
        binding.appThemeRadioGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "checkedButton", R.id.setFollowSystemTheme));
        binding.dynamicColorsSwitch.setEnabled(DynamicColors.isDynamicColorAvailable());
        switchMaterial.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "useDynamicColors"));

        //int[] setNightModeDescription = {R.string.auto_theme_description, R.string.system_theme_description, R.string.light_theme_description, R.string.night_theme_description};
        //binding.themeDescription.setText(setNightModeDescription[SharedPreferencesUtils.getInteger(requireContext(), "nightMode", 1)]);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.appThemeRadioGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "checkedButton", R.id.setFollowSystemTheme));
        binding.appThemeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.setFollowSystemTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setFollowSystemTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 1);
                    requireActivity().recreate();
                    break;
                case R.id.setLightTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setLightTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 2);
                    requireActivity().recreate();
                    break;
                case R.id.setNightTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setNightTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 3);
                    requireActivity().recreate();
                    break;
            }
        });

        switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            DynamicColors.applyToActivitiesIfAvailable(getActivity().getApplication());
            DynamicColors.applyToActivitiesIfAvailable(getActivity().getApplication(),
                    R.style.Theme_Ramadan);
            SharedPreferencesUtils.saveBoolean(requireContext(), "useDynamicColors", isChecked);
            requireActivity().recreate();
        });



        binding.backFromSettingsFragment.setOnClickListener(v -> {
            /* getChildFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new AppAboutFragment())
                    .commit(); */
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}