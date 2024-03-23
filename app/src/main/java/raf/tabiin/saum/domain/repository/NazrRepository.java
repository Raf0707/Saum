package raf.tabiin.saum.domain.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import raf.tabiin.saum.domain.dao.NazrDao;
import raf.tabiin.saum.domain.database.SaumDatabase;
import raf.tabiin.saum.domain.models.NazrItem;

public class NazrRepository {
    private NazrDao nazrDao;
    private LiveData<List<NazrItem>> nazrsList;

    public NazrRepository(Application application) {
        SaumDatabase saumDatabase = SaumDatabase.getInstance(application);
        nazrDao = saumDatabase.nazrDao();
        nazrsList = (LiveData<List<NazrItem>>) nazrDao.getAllnazrs();
    }

    public void insertData(NazrItem nazrItem) {
        new InsertTask(nazrDao).execute(nazrItem);
    }
    public void updateData(NazrItem nazrItem) {
        new UpdateTask(nazrDao).execute(nazrItem);
    }
    public void deleteData(NazrItem nazrItem) {
        new DeleteTask(nazrDao).execute(nazrItem);
    }
    public LiveData<List<NazrItem>> getAllData() {
        return nazrsList;
    }
    public List<NazrItem> findByName(String title) {
        return nazrDao.findByNames(title);
    }

    private static class InsertTask extends AsyncTask<NazrItem, Void, Void> {
        private NazrDao nazrDao;

        public InsertTask(NazrDao nazrDao) {
            this.nazrDao = nazrDao;
        }

        @Override
        protected Void doInBackground(NazrItem...nazrItems) {
            nazrDao.insertNazr(nazrItems[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<NazrItem, Void, Void> {
        private NazrDao nazrDao;

        public UpdateTask(NazrDao nazrDao) {
            this.nazrDao = nazrDao;
        }

        @Override
        protected Void doInBackground(NazrItem...nazrItems) {
            nazrDao.updateNazr(nazrItems[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<NazrItem, Void, Void> {
        private NazrDao nazrDao;

        public DeleteTask(NazrDao nazrDao) {
            this.nazrDao = nazrDao;
        }

        @Override
        protected Void doInBackground(NazrItem...nazrItems) {
            nazrDao.deleteNazr(nazrItems[0]);
            return null;
        }
    }
}
