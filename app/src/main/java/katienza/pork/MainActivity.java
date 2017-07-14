package katienza.pork;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import katienza.pork.model.BreedingRecord;

public class MainActivity extends LifecycleActivity {

    private ListBreedingRecordViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this,AddRecordActivity.class));
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<BreedingRecord>(),null);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(ListBreedingRecordViewModel.class);
        viewModel.getBreedingRecordList().observe(MainActivity.this, new Observer<List<BreedingRecord>>() {
            @Override
            public void onChanged(@Nullable List<BreedingRecord> breedingRecordList) {
                recyclerViewAdapter.addItems(breedingRecordList);
            }
        });

    }
}
