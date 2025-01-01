package raf.tabiin.saum.ui.saum;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.FragmentHalalAndHaramDaysSaumBinding;
import raf.tabiin.saum.util.CustomTabUtil;


public class HalalAndHaramDaysSaumFragment extends Fragment {
    FragmentHalalAndHaramDaysSaumBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentHalalAndHaramDaysSaumBinding.inflate(getLayoutInflater());

        b.sourseSt.setOnClickListener(v -> new CustomTabUtil()
                .openCustomTab(getActivity(),
                        "https://azan.ru/maqalat/read/zhelatelnyie-postyi-v-techenie-goda-10066",
                        R.color.purple_300));

        return b.getRoot();
    }
}