package com.nkdroidsolutions.firedefence.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.model.allform.Form;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;

/**
 * Created by Sahil on 12-07-2016.
 */
public class MainList_Forms_Adapter extends RecyclerView.Adapter<MainList_Forms_Adapter.MainList_ViewHolder> implements AllObserver {

    OnClickedListner onClickListner;

    @Override
    public MainList_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, null);
        MainList_ViewHolder rcv = new MainList_ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MainList_ViewHolder holder, final int position) {

        Form response = observerAllForm.getAllForm().getResponse().getForm().get(position);

        holder.client_name.setText(response.getClientName());
        if (response.getFormType().equals("1")) {
            holder.form_type.setText("Form Type: Engineers Report");
        } else if (response.getFormType().equals("2")) {
            holder.form_type.setText("Form Type: Sprinkle Maintenance Checklist");
        } else if (response.getFormType().equals("3")) {
            holder.form_type.setText("Form Type: Fire Extinguisher Checklist");
        } else if (response.getFormType().equals("4")) {
            holder.form_type.setText("Form Type: Vehicle Checklist");
        }
        holder.notes.setText(response.getNotes());

        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListner.onItemClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return observerAllForm.getAllForm().getResponse().getForm().size();
    }

    class MainList_ViewHolder extends RecyclerView.ViewHolder {

        private TextView client_name, form_type, notes, open, sync, done;

        public MainList_ViewHolder(View itemView) {
            super(itemView);

            client_name = (TextView) itemView.findViewById(R.id.client_name);
            form_type = (TextView) itemView.findViewById(R.id.form_type);
            notes = (TextView) itemView.findViewById(R.id.notes);
            open = (TextView) itemView.findViewById(R.id.open);
            sync = (TextView) itemView.findViewById(R.id.sync);
            done = (TextView) itemView.findViewById(R.id.done);

        }
    }

    public void setOnItemClickListner(OnClickedListner onClickListner) {
        this.onClickListner = onClickListner;
    }

    public interface OnClickedListner {
        public void onItemClick(View view, int position);
    }

}
