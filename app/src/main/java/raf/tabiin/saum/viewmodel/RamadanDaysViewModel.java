package raf.tabiin.saum.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import raf.tabiin.saum.domain.models.RamadanDay;

public class RamadanDaysViewModel extends ViewModel {
    private List<RamadanDay> ramadanDaysList = new ArrayList<>();

    public List<RamadanDay> getRamadanDaysList() {
        return ramadanDaysList;
    }

    public void setRamadanDaysList(List<RamadanDay> ramadanDaysList) {
        this.ramadanDaysList = ramadanDaysList;
    }
}