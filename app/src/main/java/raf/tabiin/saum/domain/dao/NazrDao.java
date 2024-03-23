package raf.tabiin.saum.domain.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import raf.tabiin.saum.domain.models.NazrItem;

@Dao
public interface NazrDao {
    @Query("SELECT * FROM nazr")
    LiveData<List<NazrItem>> getAllnazrs();

    @Query("SELECT * FROM nazr WHERE id IN (:userIds)")
    List<NazrItem> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM nazr WHERE titleNazr LIKE :title LIMIT 1")
    NazrItem findByTitle(String title);

    @Query("SELECT * FROM nazr WHERE textNazr LIKE :name LIMIT 1")
    NazrItem findByName(String name);

    @Query("SELECT * FROM nazr WHERE titleNazr LIKE :title")
    List<NazrItem> findByTitles(String title);

    @Query("SELECT * FROM nazr WHERE textNazr LIKE :name")
    List<NazrItem> findByNames(String name);

    @Query("SELECT * FROM nazr WHERE id = :id")
    List<NazrItem> getNazrIDS(long id);

    @Query("SELECT * FROM nazr WHERE id = :id")
    int getNazrID(long id);

    @Insert
    void insertAll(NazrItem...nazrItems);

    @Insert
    void insertNazr(NazrItem nazrItem);

    @Update
    void updateNazr(NazrItem nazrItem);

    @Delete
    void deleteNazr(NazrItem nazrItem);
}
