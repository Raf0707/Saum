package raf.tabiin.saum.ui.details;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.jakewharton.threetenabp.AndroidThreeTen;
import org.threeten.bp.LocalDate;
import org.threeten.bp.chrono.HijrahChronology;
import org.threeten.bp.chrono.HijrahDate;
import java.util.HashSet;
import java.util.Set;

public class CustomDatePickerDialog extends DatePickerDialog {

    private final Set<LocalDate> fastingDays = new HashSet<>();

    public CustomDatePickerDialog(@NonNull Context context, OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);
        AndroidThreeTen.init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTitle(getDatePicker().getYear(), getDatePicker().getMonth(), getDatePicker().getDayOfMonth());

        getDatePicker().setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) ->
                updateTitle(year, monthOfYear, dayOfMonth));

        getDatePicker().setOnLongClickListener(v -> {
            int year = getDatePicker().getYear();
            int month = getDatePicker().getMonth();
            int day = getDatePicker().getDayOfMonth();
            LocalDate selectedDate = LocalDate.of(year, month + 1, day);

            toggleFastingDays(selectedDate);
            return true;
        });
    }

    private void updateTitle(int year, int month, int dayOfMonth) {
        LocalDate date = LocalDate.of(year, month + 1, dayOfMonth);
        HijrahDate hijriDate = HijrahDate.from(date);

        setTitle("Gregorian: " + date.toString() + " | Hijri: " + hijriDate.toString());
    }

    private void toggleFastingDays(LocalDate startDate) {
        fastingDays.clear();
        LocalDate date = startDate;
        boolean fasting = true;

        while (date.getMonth() == startDate.getMonth()) {
            if (fasting) {
                fastingDays.add(date);
            }
            date = date.plusDays(1);
            fasting = !fasting;
        }

        Toast.makeText(getContext(), "Fasting days updated", Toast.LENGTH_SHORT).show();
        // Here you can also highlight these days in the DatePicker if needed
    }

    public Set<LocalDate> getFastingDays() {
        return fastingDays;
    }
}

