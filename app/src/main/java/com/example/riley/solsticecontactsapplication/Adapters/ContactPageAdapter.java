package com.example.riley.solsticecontactsapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.riley.solsticecontactsapplication.Model.Contact;
import com.example.riley.solsticecontactsapplication.R;

import java.util.List;

/**
 * Created by riley on 10/24/17.
 */

public class ContactPageAdapter extends ArrayAdapter<String[]> {
    Context context;
    List<String[]> info;

    public ContactPageAdapter(Context context, List<String[]> info) {
        super(context, R.layout.contact_row, info);
        this.context = context;
        this.info = info;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.full_page_row, parent, false);

        TextView infoType = (TextView) rowView.findViewById(R.id.infoType);
        TextView information = (TextView) rowView.findViewById(R.id.info);
        TextView category = (TextView) rowView.findViewById(R.id.infoCategory);
        category.setVisibility(View.INVISIBLE);
        if (info.get(position)[0] != null){
            if (info.get(position)[1] != null) {
                infoType.setText(info.get(position)[0]);
                information.setText(info.get(position)[1]);
                if (info.get(position).length == 3 && info.get(position)[2] != null){
                    System.out.println(info.get(position)[2]);
                    category.setText(info.get(position)[2]);
                    category.setVisibility(View.VISIBLE);
                }
            }
        }

        return rowView;
    }
}
