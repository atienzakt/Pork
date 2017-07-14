package katienza.pork;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import katienza.pork.database.AppDatabase;
import katienza.pork.model.BreedingRecord;

/**
 * Created by katienza on 13/07/2017.
 */

public class ListBreedingRecordViewModel extends AndroidViewModel {

    private final LiveData<List<BreedingRecord>> breedingRecordList;

    private AppDatabase appDatabase;

    public ListBreedingRecordViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());

        breedingRecordList = appDatabase.breedingRecordModel().getAllBreedingRecord();
    }

    public LiveData<List<BreedingRecord>> getBreedingRecordList(){
        return breedingRecordList;
    }
}
