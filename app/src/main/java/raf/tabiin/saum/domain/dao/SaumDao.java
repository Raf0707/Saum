package raf.tabiin.saum.domain.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import raf.tabiin.saum.domain.models.SaumItem;

@Dao
public interface SaumDao {
    @Query("SELECT * FROM saums")
    LiveData<List<SaumItem>> getAllSaums();

    @Query("SELECT * FROM saums WHERE id IN (:userIds)")
    List<SaumItem> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM saums WHERE day LIKE :title LIMIT 1")
    SaumItem findByName(String title);

    @Query("SELECT * FROM saums WHERE day LIKE :title")
    List<SaumItem> findByNames(String title);

    @Query("SELECT * FROM saums WHERE id = :id")
    List<SaumItem> getCounterID(long id);

    @Insert
    void insertAll(SaumItem... saumItems);

    @Insert
    void insertSaum(SaumItem saumItem);

    @Update
    void updateSaum(SaumItem saumItem);

    @Delete
    void deleteSaum(SaumItem saumItem);
}
