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
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import raf.tabiin.saum.R;
import raf.tabiin.saum.adapters.CalendarAdapter;
import raf.tabiin.saum.databinding.FragmentDawoodSaumBinding;

public class DawoodSaumFragment extends Fragment {
    private FragmentDawoodSaumBinding b;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentDawoodSaumBinding.inflate(inflater, container, false);



        return b.getRoot();
    }


}