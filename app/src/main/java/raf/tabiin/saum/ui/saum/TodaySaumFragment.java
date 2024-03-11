package raf.tabiin.saum.ui.saum;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.FragmentTodaySaumBinding;

public class TodaySaumFragment extends Fragment {
    FragmentTodaySaumBinding b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = FragmentTodaySaumBinding.inflate(getLayoutInflater());



        return b.getRoot();
    }
}