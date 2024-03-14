package raf.tabiin.saum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.splashscreen.SplashScreen;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.google.android.material.color.DynamicColors;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import raf.tabiin.saum.databinding.ActivityMainBinding;
import raf.tabiin.saum.ui.about_app.AppAboutFragment;
import raf.tabiin.saum.ui.dua.DuaFragment;
import raf.tabiin.saum.ui.saum.MainSaumFragment;
import raf.tabiin.saum.ui.saum.RamadanSaumFragment;
import raf.tabiin.saum.util.FileUtils;
import raf.tabiin.saum.util.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    AppAboutFragment appAboutFragment;
    RamadanSaumFragment ramadanSaumFragment;
    Boolean flag = false;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int nightIcon = SharedPreferencesUtils.getInteger(this, "nightIcon", R.drawable.vectornightpress);

        App.instance.setNightMode();

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        view = findViewById(R.id.view);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new MainSaumFragment())
                    .commit();
        }

        copyJsonFromAssetsIfNeeded();

        if (SharedPreferencesUtils.getBoolean(this, "useDynamicColors"))
            DynamicColors.applyToActivityIfAvailable(this);

        if (SharedPreferencesUtils.getBoolean(this, "addFollowSystemIcon"))
            flag = true;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) {
            if (!flag) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    binding.themeBtn.setIcon(getDrawable(nightIcon));
                } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                    binding.themeBtn.setIcon(getDrawable(R.drawable.vectorlight_press));
                }
            } else if (flag) {
                binding.themeBtn.setIcon(getDrawable(R.drawable.follow_system));
            }
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.themeBtn.setIcon(getDrawable(nightIcon));
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            binding.themeBtn.setIcon(getDrawable(R.drawable.vectorlight_press));
        }

        appAboutFragment = new AppAboutFragment();
        ramadanSaumFragment = new RamadanSaumFragment();

        binding.themeBtn.setOnClickListener(v -> {

            if (!flag) {

                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) {
                    binding.themeBtn.setIcon(getDrawable(R.drawable.vectornightpress));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "checkedButton", R.id.setNightTheme);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightMode", 3);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightIcon", R.drawable.vectornightpress);

                } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                    binding.themeBtn.setIcon(getDrawable(R.drawable.vectornightpress));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "checkedButton", R.id.setNightTheme);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightMode", 3);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightIcon", R.drawable.vectornightpress);
                } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    binding.themeBtn.setIcon(getDrawable(R.drawable.vectorlight_press));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "checkedButton", R.id.setLightTheme);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightMode", 2);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightIcon", R.drawable.vectorlight_press);
                }
            } else if (flag) {

                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    /*
                     */
                    binding.themeBtn.setIcon(getDrawable(R.drawable.follow_system));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "checkedButton", R.id.setFollowSystemTheme);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightMode", 1);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightIcon", R.drawable.follow_system);

                } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) {
                    binding.themeBtn.setIcon(getDrawable(R.drawable.vectorlight_press));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "checkedButton", R.id.setLightTheme);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightMode", 2);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightIcon", R.drawable.vectorlight_press);

                } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                    binding.themeBtn.setIcon(getDrawable(R.drawable.vectornightpress));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "checkedButton", R.id.setNightTheme);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightMode", 3);
                    SharedPreferencesUtils.saveInteger(getApplicationContext(), "nightIcon", R.drawable.vectornightpress);
                }
            }

            recreate();
        });

        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.post:

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new MainSaumFragment())
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

    private void copyJsonFromAssetsIfNeeded() {
        File internalDir = getFilesDir();
        File ramadanDaysFile = new File(internalDir, "ramadan_days.json");

        if (!ramadanDaysFile.exists()) {
            try {
                FileUtils.copyAssetToFile(getAssets(), "ramadan_days.json", ramadanDaysFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void animSelectTheme() {

    }
}