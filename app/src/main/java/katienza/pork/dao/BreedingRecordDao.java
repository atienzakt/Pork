package katienza.pork.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

import katienza.pork.model.BreedingRecord;
import katienza.pork.model.util.DateConverter;

/**
 * Created by katienza on 13/07/2017.
 */
@Dao
@TypeConverters(DateConverter.class)
public interface BreedingRecordDao {

    @Query("select * from BreedingRecord")
    LiveData<List<BreedingRecord>> getAllBreedingRecord();

    @Insert
    void addRecord(BreedingRecord breedingRecord);

    @Update
    void updateRecord(BreedingRecord breedingRecord);
}
