package katienza.pork;

import android.app.DatePickerDialog;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import katienza.pork.model.Sow;
import katienza.pork.view_models.AddSowViewModel;
import katienza.pork.view_models.ListSowsViewModel;

public class AddSowActivity extends LifecycleActivity {

    private EditText sowNo;
    private EditText breed;
    private EditText origin;
    private Button add;
    private Button birthDateButton;
    private DatePickerDialog selectBirthDate;
    private Date birthDate;
    private AddSowViewModel addSowViewModel;
    private ListSowsViewModel listSowsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sow);
        addSowViewModel = ViewModelProviders.of(this).get(AddSowViewModel.class);
        listSowsViewModel = ViewModelProviders.of(this).get(ListSowsViewModel.class);
        sowNo = (EditText) findViewById(R.id.sow_no);
        breed = (EditText) findViewById(R.id.sow_breed);
        origin = (EditText) findViewById(R.id.sow_origin);
        add = (Button) findViewById(R.id.add_sows);
        birthDateButton = (Button) findViewById(R.id.select_birth_date);
        selectBirthDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year,month,dayOfMonth);
                birthDate = c.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                birthDateButton.setText(sdf.format(birthDate));
            }
        }, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE));
        birthDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBirthDate.show();
            }
        });





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Sow s = new Sow(sowNo.getText().toString());
                    s.setBirthDate(birthDate);
                    s.setBreed(breed.getText().toString());
                    s.setOrigin(origin.getText().toString());
                    addSowViewModel.addSow(s);
                    finish();
                }
                catch(Exception e){
                    Log.w("SqL",e.toString());
                }

              // List<Sow> sow = listSowsViewModel.isIDTaken("Name");

//                new AsyncTask<Void, Void, Void>() {
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        List<Sow> sow = listSowsViewModel.isIDTaken("Name");
//                        //Log.w("Test",listSowsViewModel.getSowList().getValue()+ " || ");
//                        //.makeText(getBaseContext(),listSowsViewModel.getSowList().getValue()+ " || ",1).show();
//                        //Toast.makeText(getBaseContext(),sow+ " || ",1).show();
//                        Log.w("Test",sow.get(0).getSowNo()+ " || ");
//                        listSowsViewModel.getSowList().getValue();
//
//                        return null;
//                    }
//                }.execute();

            }
        });
    }

}
