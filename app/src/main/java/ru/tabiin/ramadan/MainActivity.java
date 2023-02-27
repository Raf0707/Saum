package ru.tabiin.ramadan;

import static ru.tabiin.ramadan.util.UtilFragment.changeFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.color.DynamicColors;

import ru.tabiin.ramadan.databinding.ActivityMainBinding;
import ru.tabiin.ramadan.ui.about_app.AppAboutFragment;
import ru.tabiin.ramadan.ui.post.PostRamadanFragment;
import ru.tabiin.ramadan.ui.post.RamadanPostFragment;
import ru.tabiin.ramadan.util.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    AppAboutFragment appAboutFragment;
    RamadanPostFragment ramadanPostFragment;
    PostRamadanFragment postRamadanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SharedPreferencesUtils.getBoolean(this, "useDynamicColors"))
            DynamicColors.applyToActivityIfAvailable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appAboutFragment = new AppAboutFragment();
        ramadanPostFragment = new RamadanPostFragment();
        postRamadanFragment = new PostRamadanFragment();

        changeFragment(this,
                ramadanPostFragment,
                R.id.containerFragment,
                savedInstanceState);

        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.post:
                    changeFragment(this,
                            postRamadanFragment,
                            R.id.containerFragment,
                            savedInstanceState);

                    return true;

                case R.id.about_app:
                    changeFragment(this,
                            appAboutFragment,
                            R.id.containerFragment,
                            savedInstanceState);

                    return true;
            }
            return false;
        });
    }
}