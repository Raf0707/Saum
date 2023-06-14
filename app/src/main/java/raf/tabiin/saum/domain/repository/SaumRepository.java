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
        saumDao = saumDatabase.counterDao();
        saumsList = (LiveData<List<SaumItem>>) saumDao.getAllSaums();
    }

    public void insertData(SaumItem saumItem) {
        new InsertTask(saumDao).execute(saumItem);
    }
    public void updateData(SaumItem saumItem) {
        new UpdateTask(saumDao).execute(saumItem);
    }
    public void deleteData(SaumItem saumItem) {
        new DeleteTask(saumDao).execute(saumItem);
    }
    public LiveData<List<SaumItem>> getAllData() {
        return saumsList;
    }
    public List<SaumItem> findByName(String title) {
        return saumDao.findByNames(title);
    }

    private static class InsertTask extends AsyncTask<SaumItem, Void, Void> {
        private SaumDao сounterDao;

        public InsertTask(SaumDao сounterDao) {
            this.сounterDao = сounterDao;
        }

        @Override
        protected Void doInBackground(SaumItem... saumItems) {
            сounterDao.insertSaum(saumItems[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<SaumItem, Void, Void> {
        private SaumDao сounterDao;

        public UpdateTask(SaumDao сounterDao) {
            this.сounterDao = сounterDao;
        }

        @Override
        protected Void doInBackground(SaumItem... saumItems) {
            сounterDao.updateSaum(saumItems[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<SaumItem, Void, Void> {
        private SaumDao сounterDao;

        public DeleteTask(SaumDao сounterDao) {
            this.сounterDao = сounterDao;
        }

        @Override
        protected Void doInBackground(SaumItem... saumItems) {
            сounterDao.deleteSaum(saumItems[0]);
            return null;
        }
    }
}
