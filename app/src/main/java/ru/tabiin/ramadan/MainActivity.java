package ru.tabiin.ramadan;

import static ru.tabiin.ramadan.util.UtilFragment.changeFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.color.DynamicColors;

import ru.tabiin.ramadan.databinding.ActivityMainBinding;
import ru.tabiin.ramadan.ui.about_app.AppAboutFragment;
import ru.tabiin.ramadan.ui.dua.DuaFragment;
import ru.tabiin.ramadan.ui.post.PostRamadanFragment;
import ru.tabiin.ramadan.util.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    AppAboutFragment appAboutFragment;
    PostRamadanFragment postRamadanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new PostRamadanFragment())
                    .commit();
        }

        if (SharedPreferencesUtils.getBoolean(this, "useDynamicColors"))
            DynamicColors.applyToActivityIfAvailable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appAboutFragment = new AppAboutFragment();
        postRamadanFragment = new PostRamadanFragment();

        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.post:

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new PostRamadanFragment())
                            .commit();

                    return true;

                case R.id.dua:

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new DuaFragment())
                            .commit();

                    return true;

                case R.id.about_app:

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new AppAboutFragment())
                            .commit();
                    return true;
            }
            return false;
        });
    }
}