package katienza.pork;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import katienza.pork.model.Sow;

/**
 * Created by katienza on 14/07/2017.
 */

public class SowRecyclerAdapter extends RecyclerView.Adapter<SowRecyclerAdapter.RecyclerViewHolder> {

    private List<Sow> sowList;
    private View.OnClickListener onClickListener;

    public SowRecyclerAdapter(List<Sow> sowList, View.OnClickListener onClickListener){
        this.sowList=sowList;
        this.onClickListener=onClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sow_record_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Sow s = sowList.get(position);
        holder.breed.setText(s.getBreed());
        holder.sowNo.setText(s.getSowNo());
        holder.origin.setText(s.getOrigin());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        holder.date.setText(sdf.format(s.getBirthDate()));
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return sowList.size();
    }

    public void addItems(List<Sow> sowList) {
        this.sowList=sowList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView sowNo;
        private TextView breed;
        private TextView origin;
        private TextView date;

        RecyclerViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.birth_date);
            sowNo = (TextView) view.findViewById(R.id.sow_no);
            breed = (TextView) view.findViewById(R.id.sow_breed);
            origin = (TextView) view.findViewById(R.id.sow_origin);
        }
    }
}
