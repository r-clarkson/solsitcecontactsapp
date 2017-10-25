package com.example.riley.solsticecontactsapplication;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.riley.solsticecontactsapplication.Adapters.ContactPageAdapter;
import com.example.riley.solsticecontactsapplication.Model.Contact;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.riley.solsticecontactsapplication.R.id.contactName;

/**
 * Created by riley on 10/24/17.
 */

public class ContactPageActivity extends ListActivity {
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact = (Contact) getIntent().getParcelableExtra("contact");

        setContentView(R.layout.full_page);
        Button backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToContacts();
            }
        });

        final Button favbutton = (Button) findViewById(R.id.favoritebutton);
        final boolean isfav = contact.isFavorite() ? true : false;
        if (isfav){
            favbutton.setText(new String(Character.toChars(0x2764)));
        }
        else{
            favbutton.setText(new String(Character.toChars(0x1F494)));
        }
        favbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isfav){
                    contact.setIsFavorite(false);
                    favbutton.setText(new String(Character.toChars(0x1F494)));
                }
                else{
                    contact.setIsFavorite(true);
                    favbutton.setText(new String(Character.toChars(0x2764)));
                }
            }
        });
        TextView contactName = (TextView) findViewById(R.id.contactNameFullPage);
        contactName.setText(contact.getName());
        TextView contactCompany = (TextView) findViewById(R.id.companyNameFullPage);
        contactCompany.setText(contact.getCompanyName());
        ImageView largeIcon = (ImageView) findViewById(R.id.largeIcon);
        new DownloadImageTask(largeIcon).execute(contact.getLargeImageURL());

        List<String[]> information = new ArrayList<String[]>();
        for (String[] s : contact.getPhoneArray()){
            information.add(s);
        }
        information.add(contact.getEmailArray());
        information.add(contact.getAddressArray());
        information.add(contact.getBirthdateArray());
        ContactPageAdapter itemsAdapter = new ContactPageAdapter(getApplicationContext(),information);
        setListAdapter(itemsAdapter);
    }

    public void returnToContacts(){
        Intent intent = new Intent(this, ContactsPageActivity.class);
        intent.putExtra("contact",contact);
        ContactPageActivity.this.startActivity(intent);
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
