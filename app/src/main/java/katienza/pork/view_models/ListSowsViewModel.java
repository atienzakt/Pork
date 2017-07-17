package katienza.pork.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import katienza.pork.database.AppDatabase;
import katienza.pork.model.Sow;

/**
 * Created by katienza on 14/07/2017.
 */

public class ListSowsViewModel extends AndroidViewModel {

    private final LiveData<List<Sow>> sowList;

    private AppDatabase appDatabase;

    public ListSowsViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        sowList = appDatabase.sowDaoModel().getAllSows();
    }

    public LiveData<List<Sow>> getSowList() {
        return sowList;
    }


}
