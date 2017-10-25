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
 * Contacts adapter for the big list... takes in a list of contact objects
 */
public class ContactsAdapter extends ArrayAdapter<Contact> {
    private final Context context;
    private final List<Contact> contacts;
    private String star;

    /**
     * Initializes contacts list variable, super, etc...
     * @param context
     * @param contacts
     */
    public ContactsAdapter(Context context, List<Contact> contacts) {
        super(context, R.layout.contact_row, contacts);
        this.context = context;
        this.contacts = contacts;
        star = new String(Character.toChars(0x2B50));
    }

    /**
     * Sets name based on whether object at position is favorite or not
     * Puts company name below person name
     * Defines small icon... see details below
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = null;

        /** only defines contact row if object is not null */
        if (contacts.get(position) != null) {

            /** inflate the row, set elements from row layout */
            rowView = inflater.inflate(R.layout.contact_row, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.contactName);
            TextView company = (TextView) rowView.findViewById(R.id.companyName);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.contactIcon);

            /** download the image & set company name */
            new DownloadImageTask(imageView).execute(contacts.get(position).getSmallImageURL());
            company.setText(contacts.get(position).getCompanyName());

            /* add star to person name if they're a favorite */
            if (contacts.get(position).isFavorite()) {
                textView.setText(star + contacts.get(position).getName());
                return rowView;
            }
            else{
                textView.setText(contacts.get(position).getName());
                return rowView;
            }
        }
        /**
         * When the object is null, take it as signal to inflate header
         * Favorites header if position is 0 (favs first)
         * Else do other contacts
         */
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

    /**
     * Credit to https://stackoverflow.com/questions/5776851/load-image-from-url
     * Downloads image from url and sets given imageview as such
     * I would've liked to download images to the res folder but didn't have time to look into it
     */
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




