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

/*
* Adapter for contact page, takes in String[] arrays for its elements
*/
public class ContactAdapter extends ArrayAdapter<String[]> {
    Context context;
    private List<String[]> info;

    /**
     * String[] initialized as info
     * @param context
     * @param info
     */
    public ContactAdapter(Context context, List<String[]> info) {
        super(context, R.layout.contact_row, info);
        this.context = context;
        this.info = info;
    }

    /**
     * Uses layout inflater to inflate each row... see comments below
     * This is messier than I would have liked
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.full_page_row, parent, false);

        /** each view is defined, category is initially hidden as only phone numbers have it */
        TextView infoType = (TextView) rowView.findViewById(R.id.infoType);
        TextView information = (TextView) rowView.findViewById(R.id.info);
        TextView category = (TextView) rowView.findViewById(R.id.infoCategory);
        category.setVisibility(View.INVISIBLE);

        /** only make the row if there are two pieces of info (required) to define/present */
        if (info.get(position)[0] != null && info.get(position)[1] != null){
            infoType.setText(info.get(position)[0]);
            information.setText(info.get(position)[1]);
            /** if there is a third piece of info (for phone number type) set it too */
            if (info.get(position).length == 3 && info.get(position)[2] != null){
                category.setText(info.get(position)[2]);
                category.setVisibility(View.VISIBLE);
            }
        }
        return rowView;
    }
}
