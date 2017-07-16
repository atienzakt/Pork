package katienza.pork.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.util.Log;

import katienza.pork.database.AppDatabase;
import katienza.pork.model.Sow;

/**
 * Created by katienza on 14/07/2017.
 */

public class AddSowViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddSowViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void addSow(final Sow sow){
        new addSowAsyncTask(appDatabase).execute(sow);
    }

    private static class addSowAsyncTask extends AsyncTask<Sow,Void,Void>{

        private AppDatabase db;

        public addSowAsyncTask(AppDatabase db){
            this.db=db;
        }

        @Override
        protected Void doInBackground(Sow... params) {
            try {
                db.sowDaoModel().addSow(params[0]);
            }
            catch (Exception e){
                Log.w("test",e.toString());
            }
                return null;
        }
    }
}
