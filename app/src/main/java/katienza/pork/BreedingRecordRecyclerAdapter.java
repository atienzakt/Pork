package katienza.pork;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import katienza.pork.model.BreedingRecord;

/**
 * Created by katienza on 13/07/2017.
 */

public class BreedingRecordRecyclerAdapter extends RecyclerView.Adapter<BreedingRecordRecyclerAdapter.RecyclerViewHolder> {

    private List<BreedingRecord> breedingRecordList;

    public BreedingRecordRecyclerAdapter(List<BreedingRecord> breedingRecordList){
        this.breedingRecordList = breedingRecordList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.breeding_record_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        BreedingRecord breedingRecord = breedingRecordList.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        holder.date.setText(sdf.format(breedingRecord.getDateBreed()));
        holder.name.setText(breedingRecord.getTestName());
        holder.parity.setText(breedingRecord.getParity()+"");
    }

    @Override
    public int getItemCount() {
        return breedingRecordList.size();
    }

    public void addItems(List<BreedingRecord> breedingRecordList) {
        this.breedingRecordList=breedingRecordList;
        notifyDataSetChanged();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView name;
        private TextView parity;

        RecyclerViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            name = (TextView) view.findViewById(R.id.name);
            parity = (TextView) view.findViewById(R.id.parity);
        }
    }

}
