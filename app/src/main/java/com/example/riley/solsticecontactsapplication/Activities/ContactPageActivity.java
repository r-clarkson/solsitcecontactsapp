package com.example.riley.solsticecontactsapplication.Activities;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.riley.solsticecontactsapplication.Adapters.ContactAdapter;
import com.example.riley.solsticecontactsapplication.Model.Contact;
import com.example.riley.solsticecontactsapplication.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* Activity for handling single contact view
**/
public class ContactPageActivity extends ListActivity {
    private Contact contact;
    private ArrayList<Contact> contacts;

    /**
     * First, initialize contact as the saved parcelable from other activity
     * Sets the content view to full_page (see layout folder in res)
     * Sets up favorite and back buttons, as well as img & name/company
     * Rest of info that is not guaranteed for each contact to have is put into adapter (see below)
     * Sets adapter
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contacts = getIntent().getParcelableArrayListExtra("contacts");
        contact = (Contact) getIntent().getParcelableExtra("contact");
        setContentView(R.layout.full_page);

        Button backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToContacts();
            }
        });

        /** a little extra for favorite button so icon & contact object changes on click */
        final Button favbutton = (Button) findViewById(R.id.favoritebutton);
        if (contact.isFavorite()){
            favbutton.setText(new String(Character.toChars(0x2764)));
        }
        else{
            favbutton.setText(new String(Character.toChars(0x1F494)));
        }
        favbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact.isFavorite()){
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

        /**
         * adapter which takes in String[] (there are only 3 elements needed, so I chose a little array)
         * adds info from the getters in the model classes
         * */
        List<String[]> information = new ArrayList<String[]>();
        for (String[] s : contact.getPhoneArray()){
            information.add(s);
        }
        information.add(contact.getEmailArray());
        information.add(contact.getAddressArray());
        information.add(contact.getBirthdateArray());

        ContactAdapter itemsAdapter = new ContactAdapter(getApplicationContext(),information);
        setListAdapter(itemsAdapter);
    }

    public void returnToContacts(){
        Intent intent = new Intent(this, ContactsPageActivity.class);
        intent.putExtra("contact",contact);
        intent.putParcelableArrayListExtra("contacts",contacts);
        ContactPageActivity.this.startActivity(intent);
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
