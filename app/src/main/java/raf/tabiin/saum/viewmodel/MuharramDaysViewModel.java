package raf.tabiin.saum.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import raf.tabiin.saum.domain.models.MuharramDay;

public class MuharramDaysViewModel extends ViewModel {
    private List<MuharramDay> MuharramDaysList = new ArrayList<>();

    public List<MuharramDay> getMuharramDaysList() {
        return MuharramDaysList;
    }

    public void setMuharramDaysList(List<MuharramDay> MuharramDaysList) {
        this.MuharramDaysList = MuharramDaysList;
    }
}