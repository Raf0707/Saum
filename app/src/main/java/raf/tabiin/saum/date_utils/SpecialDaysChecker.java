package raf.tabiin.saum.date_utils;

import android.content.Context;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import com.aminography.primecalendar.PrimeCalendar;
import com.aminography.primedatepicker.calendarview.PrimeCalendarView;
import com.aminography.primedatepicker.calendarview.*;
import com.aminography.primedatepicker.picker.callback.MultipleDaysPickCallback;
//import com.aminography.primedatepicker.picker.theme.base.BaseThemeFactory;
//import com.aminography.primedatepicker.picker.theme.light.LightThemeFactory;
import com.aminography.primedatepicker.picker.theme.*;
import com.aminography.primedatepicker.picker.PrimeDatePicker;
import com.aminography.primecalendar.hijri.HijriCalendar;
import com.aminography.primedatepicker.picker.theme.base.NormalThemeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import raf.tabiin.saum.MainActivity;
import raf.tabiin.saum.R;

public class SpecialDaysChecker {

    /*private Context context;

    public SpecialDaysChecker(Context context) {
        this.context = context;
    }

    private void showSpecialDays(List<PrimeCalendar> specialDays, String tag) {
        PrimeCalendar today = new HijriCalendar();
        MultipleDaysPickCallback callback = new MultipleDaysPickCallback() {
            @Override
            public void onMultipleDaysPicked(List<PrimeCalendar> days) {
                // Handle picked days
            }
        };

        LightThemeFactory themeFactory = new LightThemeFactory();

        PrimeDatePicker datePicker = PrimeDatePicker.Companion.dialogWith(today)
                .pickMultipleDays(callback)
                .initiallyPickedMultipleDays(specialDays)
                .applyTheme(themeFactory)
                .build();

        datePicker.show(((MainActivity) context).getSupportFragmentManager(), tag);
    }

    public void showMonThirSaum() {
        List<PrimeCalendar> specialDays = new ArrayList<>();
        PrimeCalendar calendar = new HijriCalendar();
        calendar.set(2024, PrimeCalendar.MONDAY, 1);

        while (calendar.get(PrimeCalendar.YEAR) == 2024) {
            if (calendar.get(PrimeCalendar.DAY_OF_WEEK) == PrimeCalendar.MONDAY ||
                    calendar.get(PrimeCalendar.DAY_OF_WEEK) == PrimeCalendar.THURSDAY) {
                specialDays.add(calendar.clone());
            }
            calendar.add(PrimeCalendar.DAY_OF_MONTH, 1);
        }

        showSpecialDays(specialDays, "MonThirSaum");
    }

    public void showWhiteDays() {
        List<PrimeCalendar> specialDays = new ArrayList<>();
        PrimeCalendar calendar = new HijriCalendar();
        calendar.set(2024, PrimeCalendar.MONDAY, 1);

        while (calendar.get(PrimeCalendar.YEAR) == 2024) {
            int day = calendar.get(PrimeCalendar.DAY_OF_MONTH);
            if (day == 13 || day == 14 || day == 15) {
                specialDays.add(calendar.clone());
            }
            calendar.add(PrimeCalendar.DAY_OF_MONTH, 1);
        }

        showSpecialDays(specialDays, "WhiteDays");
    }

    public void showRamadan() {
        List<PrimeCalendar> specialDays = new ArrayList<>();
        PrimeCalendar calendar = new HijriCalendar();
        calendar.set(2024, 8, 1); // 8th month is Ramadan in Hijri calendar

        for (int i = 0; i < 30; i++) {
            specialDays.add(calendar.clone());
            calendar.add(PrimeCalendar.DAY_OF_MONTH, 1);
        }

        showSpecialDays(specialDays, "Ramadan");
    }

    public void showShaawal() {
        List<PrimeCalendar> specialDays = new ArrayList<>();
        PrimeCalendar calendar = new HijriCalendar();
        calendar.set(2024, 9, 2); // 2nd day of Shawwal

        for (int i = 0; i < 6; i++) {
            specialDays.add(calendar.clone());
            calendar.add(PrimeCalendar.DAY_OF_MONTH, 1);
        }

        showSpecialDays(specialDays, "Shaawal");
    }

    public void showMuharramAshura() {
        List<PrimeCalendar> specialDays = new ArrayList<>();
        PrimeCalendar calendar = new HijriCalendar();
        calendar.set(2024, 1, 9); // 9th day of Muharram

        for (int i = 0; i < 3; i++) {
            specialDays.add(calendar.clone());
            calendar.add(PrimeCalendar.DAY_OF_MONTH, 1);
        }

        showSpecialDays(specialDays, "MuharramAshura");
    }

    public void showZulHija() {
        List<PrimeCalendar> specialDays = new ArrayList<>();
        PrimeCalendar calendar = new HijriCalendar();
        calendar.set(2024, 12, 1); // 1st day of Zul-Hijjah

        for (int i = 0; i < 9; i++) {
            if (i == 8) { // 9th day of Zul-Hijjah
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you in Hajj?");
                builder.setMessage("If you are in Hajj, you should not fast on this day.");
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    Toast.makeText(context, "You cannot fast on this day.", Toast.LENGTH_LONG).show();
                });
                builder.setNegativeButton("No", (dialog, which) -> {
                    specialDays.add(calendar.clone());
                    showSpecialDays(specialDays, "ZulHija");
                });
                builder.show();
            } else {
                specialDays.add(calendar.clone());
            }
            calendar.add(PrimeCalendar.DAY_OF_MONTH, 1);
        }

        showSpecialDays(specialDays, "ZulHija");
    }*/
}