package raf.tabiin.saum.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import raf.tabiin.saum.domain.models.KaffaratDay;
import raf.tabiin.saum.domain.models.RamadanDay;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import raf.tabiin.saum.domain.models.RamadanDay;

public class KaffaratDaysViewModel extends ViewModel {
    private List<KaffaratDay> kaffaratDaysList = new ArrayList<>();

    public List<KaffaratDay> getKaffaratDaysList() {
        return kaffaratDaysList;
    }

    public void setRamadanDaysList(List<RamadanDay> ramadanDaysList) {
        this.kaffaratDaysList = kaffaratDaysList;
    }
}