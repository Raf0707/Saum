package raf.tabiin.saum.domain.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import raf.tabiin.saum.domain.dao.SaumDao;
import raf.tabiin.saum.domain.database.SaumDatabase;
import raf.tabiin.saum.domain.models.SaumItem;

public class SaumRepository {
    private SaumDao saumDao;
    private LiveData<List<SaumItem>> saumsList;

    public SaumRepository(Application application) {
        SaumDatabase saumDatabase = SaumDatabase.getInstance(application);
        saumDao = saumDatabase.saumDao();
        saumsList = saumDao.getAllSaums();
    }

    public void insertData(SaumItem saumItem) {
        new InsertTask(saumDao, this).execute(saumItem);
    }

    public void updateData(SaumItem saumItem) {
        new UpdateTask(saumDao, this).execute(saumItem);
    }

    public void deleteData(SaumItem saumItem) {
        new DeleteTask(saumDao, this).execute(saumItem);
    }

    public LiveData<List<SaumItem>> getAllData() {
        return saumsList;
    }

    public List<SaumItem> findByName(String title) {
        return saumDao.findByNames(title);
    }

    private static class InsertTask extends AsyncTask<SaumItem, Void, Void> {
        private SaumDao saumDao;
        private SaumRepository repository;

        public InsertTask(SaumDao saumDao, SaumRepository repository) {
            this.saumDao = saumDao;
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(SaumItem... saumItems) {
            saumDao.insertSaum(saumItems[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            repository.saumsList = saumDao.getAllSaums();
        }
    }

    private static class UpdateTask extends AsyncTask<SaumItem, Void, Void> {
        private SaumDao saumDao;
        private SaumRepository repository;

        public UpdateTask(SaumDao saumDao, SaumRepository repository) {
            this.saumDao = saumDao;
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(SaumItem... saumItems) {
            saumDao.updateSaum(saumItems[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            repository.saumsList = saumDao.getAllSaums();
        }
    }

    private static class DeleteTask extends AsyncTask<SaumItem, Void, Void> {
        private SaumDao saumDao;
        private SaumRepository repository;

        public DeleteTask(SaumDao saumDao, SaumRepository repository) {
            this.saumDao = saumDao;
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(SaumItem... saumItems) {
            saumDao.deleteSaum(saumItems[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            repository.saumsList = saumDao.getAllSaums();
        }
    }
}
