package katienza.pork;


import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import katienza.pork.model.BreedingRecord;
import katienza.pork.view_models.AddBreedingRecordViewModel;

public class AddRecordActivity extends AppCompatActivity {

    private EditText name;
    private Button date;
    private EditText parity;
    private AddBreedingRecordViewModel addBreedingRecordViewModel;
    private Date selectedDate;
    private DatePickerDialog dateWeanedPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addBreedingRecordViewModel = ViewModelProviders.of(this).get(AddBreedingRecordViewModel.class);

        name = (EditText) findViewById(R.id.name);
        date = (Button) findViewById(R.id.date);
        parity = (EditText) findViewById(R.id.parity);
        dateWeanedPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year,month,dayOfMonth);
                selectedDate = c.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                date.setText(sdf.format(selectedDate));
            }
        }, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE));
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateWeanedPickerDialog.show();
            }
        });
        Button b = (Button) findViewById(R.id.add_breeding_record);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Clicked",Toast.LENGTH_SHORT).show();
                BreedingRecord b = new BreedingRecord(selectedDate);
                b.setTestName(name.getText().toString());
                b.setParity(Integer.parseInt(parity.getText().toString()));

                addBreedingRecordViewModel.addRecord(b);
                finish();

            }
        });

    }


}
