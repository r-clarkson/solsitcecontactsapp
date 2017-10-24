package com.example.riley.solsticecontactsapplication;

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

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

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
        if (contacts.get(position)!= null) {
            View rowView = inflater.inflate(R.layout.contact_row, parent,false);
            TextView textView = (TextView) rowView.findViewById(R.id.contactName);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.contactIcon);
            textView.setText(contacts.get(position).getName());
            new DownloadImageTask(imageView).execute(contacts.get(position).getSmallImageURL());
            return rowView;
        }
        else{
            View rowView = inflater.inflate(R.layout.contact_header,parent,false);
            TextView textView = (TextView) rowView.findViewById(R.id.regContacts);
            textView.setText("Other Contacts");
            return rowView;
        }
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




