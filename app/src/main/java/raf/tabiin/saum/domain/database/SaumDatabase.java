package raf.tabiin.saum.domain.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import raf.tabiin.saum.domain.dao.NazrDao;
import raf.tabiin.saum.domain.dao.SaumDao;
import raf.tabiin.saum.domain.models.NazrItem;
import raf.tabiin.saum.domain.models.SaumItem;


@Database(entities = {SaumItem.class, NazrItem.class}, version = 2)
public abstract class SaumDatabase extends RoomDatabase {
    public abstract SaumDao saumDao();
    public abstract NazrDao nazrDao();
    private static SaumDatabase INSTANCE;
    public static synchronized SaumDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaumDatabase.class, "saum_database")
                    .allowMainThreadQueries()
                    //.setJournalMode(JournalMode.AUTOMATIC)
                    //.fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        if (INSTANCE.isOpen()) INSTANCE.close();
        INSTANCE = null;
    }

}

