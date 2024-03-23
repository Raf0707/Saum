package raf.tabiin.saum.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import raf.tabiin.saum.domain.database.SaumDatabase;
import raf.tabiin.saum.domain.models.SaumItem;
import raf.tabiin.saum.domain.repository.SaumRepository;

public class SaumViewModel extends AndroidViewModel {
    private LiveData<List<SaumItem>> saumlist;
    private SaumRepository saumRepository;

    public MutableLiveData<SaumItem> currentSaum;

    public SaumDatabase saumDatabase = SaumDatabase.getInstance(getApplication());

    public SaumViewModel(@NonNull Application application) {
        super(application);
        saumRepository = new SaumRepository(application);
        saumlist = saumRepository.getAllData();
    }

    public LiveData<List<SaumItem>> getSaumlistObserver() {
        return saumlist;
    }

    public MutableLiveData<SaumItem> getCurrentSaum(MutableLiveData<SaumItem> currentSaum) {
        return currentSaum;
    }
    public List<SaumItem> findByNames(String day) {
        return saumRepository.findByName(day);
    }
    public LiveData<List<SaumItem>> getAllSaumList() {
        return saumRepository.getAllData();
    }

    public void insert(SaumItem saumItem) {
        saumRepository.insertData(saumItem);
        getAllSaumList();
    }
    public void insert(String day, String month) {
        SaumItem saumItem = new SaumItem(day, month, 0, false);
        saumRepository.insertData(saumItem);
        getAllSaumList();
    }
    public void update(SaumItem saumItem) {
        saumRepository.updateData(saumItem);
        getAllSaumList();
    }

    public void delete(SaumItem saumItem) {
        saumRepository.deleteData(saumItem);
        getAllSaumList();
    }

}
