package katienza.pork.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

import katienza.pork.model.Sow;
import katienza.pork.model.util.DateConverter;

/**
 * Created by katienza on 14/07/2017.
 */
@Dao
@TypeConverters(DateConverter.class)
public interface SowDao {

    @Query("select * from Sow")
    LiveData<List<Sow>> getAllSows();

    @Insert
    void addSow(Sow sow);

    @Update
    void updateSow(Sow sow);
}
