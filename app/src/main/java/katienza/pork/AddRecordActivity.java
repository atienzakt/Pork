package katienza.pork;


import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import katienza.pork.model.BreedingRecord;
import katienza.pork.model.Sow;
import katienza.pork.view_models.AddBreedingRecordViewModel;

public class AddRecordActivity extends AppCompatActivity {

    private Button date;
    private EditText parity;
    private AddBreedingRecordViewModel addBreedingRecordViewModel;
    private Date selectedDate;
    private DatePickerDialog dateWeanedPickerDialog;
    private  Spinner sowSpinner;
    private SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addBreedingRecordViewModel = ViewModelProviders.of(this).get(AddBreedingRecordViewModel.class);

        sowSpinner = (Spinner) findViewById(R.id.select_pig);

        List<Sow> sowList = getIntent().getBundleExtra("sowList").getParcelableArrayList("sowList");
        for(Sow s:sowList){
            Log.w("Test",s.getSowNo());
        }
        ArrayAdapter<Sow> spinnerSowAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sowList);
        sowSpinner.setAdapter(spinnerSowAdapter);
        spinnerSowAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sowSpinner.setAdapter(spinnerSowAdapter);
        date = (Button) findViewById(R.id.date);
        parity = (EditText) findViewById(R.id.parity);
        dateWeanedPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year,month,dayOfMonth);
                selectedDate = c.getTime();

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


        if(getIntent().getBundleExtra("editRecord").getParcelable("editRecord")!=null){
            BreedingRecord br = getIntent().getBundleExtra("editRecord").getParcelable("editRecord");
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(br.getDateBreed().getTime());
            dateWeanedPickerDialog.getDatePicker().updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
            parity.setText(br.getParity());
            sowSpinner.setSelection(sowList.indexOf(br.getSow()));
            selectedDate=c.getTime();
            date.setText(sdf.format(selectedDate));
        }


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().getBundleExtra("editRecord").getParcelable("editRecord")!=null){
                    BreedingRecord b = getIntent().getBundleExtra("editRecord").getParcelable("editRecord");
                    b.setSow((Sow) sowSpinner.getSelectedItem());
                    b.setParity(Integer.parseInt(parity.getText().toString()));
                    addBreedingRecordViewModel.updateRecord(b);
                    finish();
                }
                else {
                    Toast.makeText(getBaseContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    BreedingRecord b = new BreedingRecord(selectedDate);
                    b.setSow((Sow) sowSpinner.getSelectedItem());
                    b.setParity(Integer.parseInt(parity.getText().toString()));
                    addBreedingRecordViewModel.addRecord(b);
                    finish();
                }
            }
        });

    }


}
