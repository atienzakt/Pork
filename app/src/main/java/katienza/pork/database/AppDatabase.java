package katienza.pork.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import katienza.pork.dao.BreedingRecordDao;
import katienza.pork.model.BreedingRecord;

/**
 * Created by katienza on 13/07/2017.
 */

@Database(entities = {BreedingRecord.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "pork_db")
                            .build();
        }
        return INSTANCE;
    }

    public abstract BreedingRecordDao breedingRecordModel();

}
