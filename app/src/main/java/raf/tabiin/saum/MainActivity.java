package raf.tabiin.saum;

import static android.text.TextUtils.indexOf;
import static android.text.TextUtils.lastIndexOf;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.icu.util.IslamicCalendar;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import raf.tabiin.saum.databinding.ActivityMainBinding;
import raf.tabiin.saum.ui.about_app.AppAboutFragment;
import raf.tabiin.saum.ui.dua.DuaFragment;
import raf.tabiin.saum.ui.saum.MainSaumFragment;
import raf.tabiin.saum.ui.saum.RamadanSaumFragment;
import raf.tabiin.saum.util.FileUtils;
import raf.tabiin.saum.util.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    private ActivityMainBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (SharedPreferencesUtils.getBoolean(this, "useDynamicColors"))
            DynamicColors.applyToActivityIfAvailable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        copyJsonFromAssetsIfNeeded();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.bottomAppBar, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        Calendar gregorianCalendar = Calendar.getInstance();
        String gregorianDate;
        if (Calendar.MONTH < 10) {
            gregorianDate = gregorianCalendar.get(Calendar.DAY_OF_MONTH) + ".0"
                    + (gregorianCalendar.get(Calendar.MONTH) + 1) + "."
                    + gregorianCalendar.get(Calendar.YEAR);
        } else {
            gregorianDate = gregorianCalendar.get(Calendar.DAY_OF_MONTH) + "."
                    + (gregorianCalendar.get(Calendar.MONTH) + 1) + "."
                    + gregorianCalendar.get(Calendar.YEAR);
        }
        binding.dateTextViewGr.setText(gregorianDate);

        IslamicCalendar islamicCalendar = new IslamicCalendar(gregorianCalendar.getTime());
        String hijriDate = islamicCalendar.getDateTimeFormat(DateFormat.SHORT, DateFormat.SHORT,
                new Locale("ru")).format(new Date()).substring(0, 10);
        binding.dateTextViewIcShort.setText(hijriDate);

        String hijriDateFull = islamicCalendar.getDateTimeFormat(DateFormat.FULL, DateFormat.FULL,
                new Locale("ru")).format(new Date());
        hijriDateFull = hijriDateFull.substring(0, hijriDateFull.lastIndexOf('.')) + ".";
        binding.dateTextViewIcFull.setText(hijriDateFull);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.settingsFragment2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void copyJsonFromAssetsIfNeeded() {
        File internalDir = getFilesDir();

        copyAssetIfNeeded("ramadan_days.json", internalDir);
        copyAssetIfNeeded("shaawal_days.json", internalDir);
        copyAssetIfNeeded("muharram_days.json", internalDir);
        copyAssetIfNeeded("zulhija_days.json", internalDir);
    }

    private void copyAssetIfNeeded(String fileName, File destinationDir) {
        File destinationFile = new File(destinationDir, fileName);

        if (!destinationFile.exists()) {
            try {
                FileUtils.copyAssetToFile(getAssets(), fileName, destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}