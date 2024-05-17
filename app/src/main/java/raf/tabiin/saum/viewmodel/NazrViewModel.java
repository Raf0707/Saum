package raf.tabiin.saum.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import raf.tabiin.saum.domain.database.SaumDatabase;
import raf.tabiin.saum.domain.models.NazrItem;
import raf.tabiin.saum.domain.repository.NazrRepository;

public class NazrViewModel extends AndroidViewModel {
    private LiveData<List<NazrItem>> nazrlist;
    private NazrRepository nazrRepository;

    public MutableLiveData<NazrItem> currentNazr;

    public SaumDatabase saumDatabase = SaumDatabase.getInstance(getApplication());

    public NazrViewModel(@NonNull Application application) {
        super(application);
        nazrRepository = new NazrRepository(application);
        nazrlist = nazrRepository.getAllData();
    }

    public LiveData<List<NazrItem>> getNazrlistObserver() {
        return nazrlist;
    }

    public MutableLiveData<NazrItem> getCurrentNazr(MutableLiveData<NazrItem> currentSaum) {
        return currentSaum;
    }
    public List<NazrItem> findByNames(String title) {
        return nazrRepository.findByName(title);
    }
    /*public LiveData<List<NazrItem>> getAllNazrList() {
        return nazrRepository.getAllData();
    }*/

    public void insert(NazrItem nazrItem) {
        nazrRepository.insertData(nazrItem);
        getNazrlistObserver();
    }
    public void insert(String title, String text) {
        NazrItem nazrItem = new NazrItem(title, text);
        nazrRepository.insertData(nazrItem);
        getNazrlistObserver();
    }
    public void update(NazrItem nazrItem) {
        nazrRepository.updateData(nazrItem);
        getNazrlistObserver();
    }

    public void delete(NazrItem nazrItem) {
        nazrRepository.deleteData(nazrItem);
        getNazrlistObserver();
    }

}
