package raf.tabiin.saum.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import raf.tabiin.saum.domain.models.ShaawalDay;

public class ShaawalDaysViewModel extends ViewModel {
    private List<ShaawalDay> ShaawalDaysList = new ArrayList<>();

    public List<ShaawalDay> getShaawalDaysList() {
        return ShaawalDaysList;
    }

    public void setShaawalDaysList(List<ShaawalDay> ShaawalDaysList) {
        this.ShaawalDaysList = ShaawalDaysList;
    }
}