package katienza.pork;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import katienza.pork.model.BreedingRecord;

public class AddRecordActivity extends AppCompatActivity {

    private EditText name;
    private CalendarView date;
    private EditText parity;
    private AddBreedingRecordViewModel addBreedingRecordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addBreedingRecordViewModel = ViewModelProviders.of(this).get(AddBreedingRecordViewModel.class);

        name = (EditText) findViewById(R.id.name);
        date = (CalendarView) findViewById(R.id.date);
        parity = (EditText) findViewById(R.id.parity);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Clicked",1);
                BreedingRecord b = new BreedingRecord(new Date(date.getDate()));
                b.setTestName(name.getText().toString());
                b.setParity(Integer.parseInt(parity.getText().toString()));

                addBreedingRecordViewModel.addRecord(b);

            }
        });

    }


}
