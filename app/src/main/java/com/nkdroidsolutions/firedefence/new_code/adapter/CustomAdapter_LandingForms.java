package com.nkdroidsolutions.firedefence.new_code.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nkdroidsolutions.firedefence.R;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Sahil on 7/4/2016.
 */
public class CustomAdapter_LandingForms extends ArrayAdapter<HashMap<String, String>> {

    private final Activity context;
    private final LinkedList<HashMap<String, String>> allData;
    String msg;

    public CustomAdapter_LandingForms(Activity context, LinkedList<HashMap<String, String>> list) {
        super(context, R.layout.item_category, list);
        this.context = context;
        this.allData = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        view = null;
        if (view == null) {

            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.item_category, null);
            final ViewHolder viewHolder = new ViewHolder();
            initAll(view, viewHolder,position);
            view.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        fillAll(holder, position);



        return view;
    }

    static class ViewHolder {
        TextView client_name,form_type;
    }

    public void initAll(View view, ViewHolder viewHolder, final int pos) {
        viewHolder.client_name = (TextView) view.findViewById(R.id.client_name);
        viewHolder.form_type = (TextView) view.findViewById(R.id.form_type);
    }

    public void fillAll(final ViewHolder holder, final int position) {
        holder.client_name.setText(allData.get(position).get("client_name"));
        holder.form_type.setText(allData.get(position).get("form_type"));
    }

}
