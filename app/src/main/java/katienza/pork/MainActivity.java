package katienza.pork;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import katienza.pork.model.BreedingRecord;
import katienza.pork.model.Sow;
import katienza.pork.view_models.ListBreedingRecordViewModel;
import katienza.pork.view_models.ListSowsViewModel;

public class MainActivity extends LifecycleActivity {

    private ListBreedingRecordViewModel breedingRecordViewModel;
    private BreedingRecordRecyclerAdapter breedingRecordViewAdapter;
    private RecyclerView breedingRecords;

    private ListSowsViewModel sowsViewModel;
    private SowRecyclerAdapter sowRecyclerAdapter;
    private RecyclerView sows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button addRecord = (Button) findViewById(R.id.add_record);
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this,AddRecordActivity.class));
            }
        });
        breedingRecords = (RecyclerView) findViewById(R.id.breeding_records);
        breedingRecordViewAdapter = new BreedingRecordRecyclerAdapter(new ArrayList<BreedingRecord>());
        breedingRecords.setLayoutManager( new LinearLayoutManager(this));
        breedingRecords.setAdapter(breedingRecordViewAdapter);

        breedingRecordViewModel = ViewModelProviders.of(this).get(ListBreedingRecordViewModel.class);
        breedingRecordViewModel.getBreedingRecordList().observe(MainActivity.this, new Observer<List<BreedingRecord>>() {
            @Override
            public void onChanged(@Nullable List<BreedingRecord> breedingRecordList) {
                breedingRecordViewAdapter.addItems(breedingRecordList);
            }
        });

        Button addSow = (Button) findViewById(R.id.add_sows);
        addSow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddSowActivity.class));
            }
        });
        sows = (RecyclerView) findViewById(R.id.sow_record);
        sowRecyclerAdapter = new SowRecyclerAdapter(new ArrayList<Sow>());
        sows.setLayoutManager(new LinearLayoutManager(this));
        sows.setAdapter(sowRecyclerAdapter);

        sowsViewModel = ViewModelProviders.of(this).get(ListSowsViewModel.class);
        sowsViewModel.getSowList().observe(MainActivity.this, new Observer<List<Sow>>() {
            @Override
            public void onChanged(@Nullable List<Sow> sowList) {
                sowRecyclerAdapter.addItems(sowList);
                for(Sow s:sowList){
                    Log.w("Sows",s.getSowNo()+ " || "+s.getBreed()+ " || "+s.getOrigin()+ " || "+s.getBirthDate());
                }
            }
        });
    }
}
