package raf.tabiin.saum.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import raf.tabiin.saum.domain.models.ZulhijaDay;

public class ZulhijaDaysViewModel extends ViewModel {
    private List<ZulhijaDay> ZulhijaDaysList = new ArrayList<>();

    public List<ZulhijaDay> getZulhijaDaysList() {
        return ZulhijaDaysList;
    }

    public void setZulhijaDaysList(List<ZulhijaDay> ZulhijaDaysList) {
        this.ZulhijaDaysList = ZulhijaDaysList;
    }
}