package katienza.pork;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
    private List<Sow> sowList;
    private List<BreedingRecord> breedingRecordList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button addRecord = (Button) findViewById(R.id.add_record);
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,AddRecordActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList("sowList",new ArrayList<Parcelable>(sowList));
                i.putExtra("sowList",b);
                startActivity(i);
            }
        });
        breedingRecords = (RecyclerView) findViewById(R.id.breeding_records);
        breedingRecordViewAdapter = new BreedingRecordRecyclerAdapter(new ArrayList<BreedingRecord>(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos =breedingRecords.getChildLayoutPosition(v);
                Intent i =new Intent(MainActivity.this,AddRecordActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("editRecord",breedingRecordList.get(pos));
                i.putExtra("editRecord",b);
                startActivity(i);
            }
        });
        breedingRecords.setLayoutManager( new LinearLayoutManager(this));
        breedingRecords.setAdapter(breedingRecordViewAdapter);

        breedingRecordViewModel = ViewModelProviders.of(this).get(ListBreedingRecordViewModel.class);
        breedingRecordViewModel.getBreedingRecordList().observe(MainActivity.this, new Observer<List<BreedingRecord>>() {
            @Override
            public void onChanged(@Nullable List<BreedingRecord> breedingRecordList) {
                breedingRecordViewAdapter.addItems(breedingRecordList);
                setBreedingRecordList(breedingRecordList);

            }
        });

        Button addSow = (Button) findViewById(R.id.add_sows);
        addSow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,AddSowActivity.class);
                startActivity(i);
            }
        });
        sows = (RecyclerView) findViewById(R.id.sow_record);
        sowRecyclerAdapter = new SowRecyclerAdapter(new ArrayList<Sow>(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int pos =sows.getChildLayoutPosition(v);
                Intent i =new Intent(MainActivity.this,AddSowActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("editSow",sowList.get(pos));
                i.putExtra("editSow",b);
                startActivity(i);
            }
        });
        sows.setLayoutManager(new LinearLayoutManager(this));
        sows.setAdapter(sowRecyclerAdapter);

        sowsViewModel = ViewModelProviders.of(this).get(ListSowsViewModel.class);
        sowsViewModel.getSowList().observe(MainActivity.this, new Observer<List<Sow>>() {
            @Override
            public void onChanged(@Nullable List<Sow> sowList) {

                sowRecyclerAdapter.addItems(sowList);
                setSowList(sowList);
                for(Sow s:sowList){
                    Log.w("Sows",s.getSowNo()+ " || "+s.getBreed()+ " || "+s.getOrigin()+ " || "+s.getBirthDate());
                }
            }
        });
    }

    private List<Sow> getSowList(){
        return sowList;
    }

    private void setSowList(List<Sow> sowList){
        this.sowList=sowList;
    }

    public List<BreedingRecord> getBreedingRecordList() {
        return breedingRecordList;
    }

    public void setBreedingRecordList(List<BreedingRecord> breedingRecordList) {
        this.breedingRecordList = breedingRecordList;
    }
}
