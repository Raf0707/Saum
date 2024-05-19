package raf.tabiin.saum.ui.saum;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import raf.tabiin.saum.R;
import raf.tabiin.saum.adapters.CalendarAdapter;
import raf.tabiin.saum.databinding.FragmentDawoodSaumBinding;

public class DawoodSaumFragment extends Fragment implements CalendarAdapter.OnItemListener {
    private LocalDate selectedDate;
    private FragmentDawoodSaumBinding b;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentDawoodSaumBinding.inflate(getLayoutInflater(), container, false);
        selectedDate = LocalDate.now();
        setMonthView();
        return b.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        b.monthYearTxt.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = dayInMonthArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        b.calendarRecycleView.setLayoutManager(layoutManager);
        b.calendarRecycleView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> dayInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = date.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        // Adjust dayOfWeek to align with the calendar
        dayOfWeek = (dayOfWeek == 7) ? 0 : dayOfWeek;

        for (int i = 1; i <= 42; ++i) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }

        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @Override
    public void onItemClick(int position, String dayText) {
        //
    }

    @Override
    public void onItemLongClick(int position, String dayText) {

    }

    @Override
    public void onItemDoubleClick(int position, String dayText) {

    }
}