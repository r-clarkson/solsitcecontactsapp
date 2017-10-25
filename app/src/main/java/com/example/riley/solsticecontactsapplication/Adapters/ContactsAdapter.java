package com.example.riley.solsticecontactsapplication.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.riley.solsticecontactsapplication.Model.Contact;
import com.example.riley.solsticecontactsapplication.R;

import java.io.InputStream;
import java.util.List;

/**
 * Created by riley on 10/24/17.
 */

public class ContactsAdapter extends ArrayAdapter<Contact> {
    private final Context context;
    private final List<Contact> contacts;


    public ContactsAdapter(Context context, List<Contact> contacts) {
        super(context, R.layout.contact_row, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = null;
        if (contacts.get(position) != null) {
            String star = new String(Character.toChars(0x2B50));
            rowView = inflater.inflate(R.layout.contact_row, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.contactName);
            TextView company = (TextView) rowView.findViewById(R.id.companyName);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.contactIcon);
            new DownloadImageTask(imageView).execute(contacts.get(position).getSmallImageURL());
            company.setText(contacts.get(position).getCompanyName());
            if (contacts.get(position).isFavorite()) {
                textView.setText(star + contacts.get(position).getName());
                return rowView;
            }
            else{
                textView.setText(contacts.get(position).getName());
                return rowView;
            }
        }
        else{
            if(position==0){
                rowView = inflater.inflate(R.layout.contact_header, parent, false);
                TextView textHeader = (TextView) rowView.findViewById(R.id.regContacts);
                textHeader.setText("Favorite Contacts");
            }
            else {
                rowView = inflater.inflate(R.layout.contact_header, parent, false);
                TextView textHeader = (TextView) rowView.findViewById(R.id.regContacts);
                textHeader.setText("Other Contacts");
            }
        }
        return rowView;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}




