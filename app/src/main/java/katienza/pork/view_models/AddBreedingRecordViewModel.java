package katienza.pork.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import katienza.pork.database.AppDatabase;
import katienza.pork.model.BreedingRecord;

/**
 * Created by katienza on 13/07/2017.
 */

public class AddBreedingRecordViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddBreedingRecordViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void addRecord(final BreedingRecord breedingRecord){
        new addBreedingRecordAsyncTask(appDatabase).execute(breedingRecord);
    }

    private static class addBreedingRecordAsyncTask extends AsyncTask<BreedingRecord,Void, Void>{
        private AppDatabase db;

        public addBreedingRecordAsyncTask(AppDatabase appDatabase){
            db=appDatabase;
        }

        @Override
        protected Void doInBackground(BreedingRecord... params) {
            db.breedingRecordModel().addRecord(params[0]);
            return null;
        }
    }
}
