package com.example.riley.solsticecontactsapplication.Activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.riley.solsticecontactsapplication.Adapters.ContactsAdapter;
import com.example.riley.solsticecontactsapplication.Model.Contact;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import static com.example.riley.solsticecontactsapplication.R.layout.contact_page;

/**
 * Activity for handling view of all contacts
 */
public class ContactsPageActivity extends ListActivity {
    /** private list of contacts and the url for json endpoint */
    private ArrayList<Contact> contacts;
    private String url;

    /**
     * Initializes contact list, sets view, hands url off to asynctask for getting JSON objects
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contact_page);
        url ="https://s3.amazonaws.com/technical-challenge/v3/contacts.json";
        new GetJSONObjects(contacts).execute(url);
    }

    /**
     * gets contact at given position and passes it to displayContact()
     * which uses intent to switch activities
     * @param l
     * @param v
     * @param position
     * @param id
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        Contact contact = (Contact) getListAdapter().getItem(position);
        displayContact(contact);
    }

    /**
     * given a contact, makes an intent and adds contact as extra (they are parcelable),
     * then switches activities
     * @param contact
     */
    public void displayContact(Contact contact){
        Intent intent = new Intent(this, ContactPageActivity.class);
        intent.putExtra("contact",contact);
        intent.putParcelableArrayListExtra("contacts",contacts);
        ContactsPageActivity.this.startActivity(intent);
    }

    /**
     * Async task due to network access
     * Task is passed a Contact list (the one from above) which it defines using
     * Jackson API used to parse JSON array and map to Contact object
     * Then it sorts the list and sets adapter... see below comments
     */
    private class GetJSONObjects extends AsyncTask<String, Void, ArrayList<Contact>> {
        ArrayList<Contact> contactsList;

        public GetJSONObjects(ArrayList<Contact> contactsList) {
            this.contactsList = contactsList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * If activity is starting (not resuming), takes url and passes it to an ObjectMapper (jackson api)
         * Object Mapper results in a list of contacts (magic), and c (the contacts list) is
         * defined as that result
         * Also handles updating favorite contacts
         * @param url
         * @return contactsList
         */
        @Override
        protected ArrayList<Contact> doInBackground(String... url) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String contactObjectsJSON = null;

            try{
                /** gets url, uses mapper to parse it and then set the resulting list as c */
                if (!getIntent().hasExtra("contacts")) {
                    contactObjectsJSON = new Scanner(new URL(url[0]).openStream(), "UTF-8").useDelimiter("\\A").next();
                    contactsList = mapper.readValue(contactObjectsJSON, new TypeReference<List<Contact>>() {
                    });
                }

                /** if the current intent has an extra, will replace that contact's JSON object with the updated one */
                else if (getIntent().hasExtra("contacts")){
                    contactsList = getIntent().getParcelableArrayListExtra("contacts");
                }

                if (getIntent().hasExtra("contact")){
                    contactsList = replaceContact( (Contact) getIntent().getParcelableExtra("contact"), contactsList);
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return contactsList;
        }
        /* *
        * After execution, sorts the list and then sets the adapter (custom
        * adapter which takes in list of contacts- see ContactsAdapter)
        * */
        @Override
        protected void onPostExecute(ArrayList<Contact> contactsList) {
            super.onPostExecute(contactsList);
            setContacts(contactsList);
            if(contactsList != null){
                contactsList = sortList(contactsList);
                ContactsAdapter adapter = new ContactsAdapter(getApplicationContext(),contactsList);
                setListAdapter(adapter);
            } else {
                System.out.println("Error loading contacts adapter");
            }
        }

        /**
         * Given a list, this does a couple things
         * 1. separates lists by favorite/regular contact
         * 2. sorts the favorite and regular lists alphabetically
         * 3. combines lists, and inserts null objects at the
         * beginning and middle of the two favorite/regular contact lists (to signal when to inflate a header)
         * @param fullList
         * @return
         */
        public ArrayList<Contact> sortList(ArrayList<Contact> fullList) {

            ArrayList<Contact> favoritesList = new ArrayList<Contact>();
            ArrayList<Contact> regularList = new ArrayList<Contact>();
            ArrayList<Contact> list = new ArrayList<Contact>();

            list.add(null);
            for (Contact contact : fullList) {
                if (contact.isFavorite()){
                    favoritesList.add(contact);
                }
            }
            Collections.sort(favoritesList, new SampleComparator());
            list.addAll(favoritesList);
            list.add(null);
            for (Contact contact : fullList){
                if (!contact.isFavorite()){
                    regularList.add(contact);
                }
            }
            Collections.sort(regularList,new SampleComparator());
            list.addAll(regularList);
            return list;
        }

        /**
         * replaces contact in the list if the parcelable was given back
         * @param contact
         * @param contactsList
         * @return
         */
        public ArrayList<Contact> replaceContact(Contact contact, ArrayList<Contact> contactsList){
            for (Contact c : contactsList){
                if (c.getName().equals(contact.getName())){
                    contactsList.remove(c);
                    contactsList.add(contact);
                }
            }
            return contactsList;
        }

    }

    /**
     * custom comparator for alphabetical order thank you: https://stackoverflow.com/questions/19561067/sort-an-array-of-objects-alphabetically
     */
    class SampleComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {

            return o1.getName().compareTo(o2.getName());
        }
    }

    public void setContacts(ArrayList<Contact> list){
        contacts = list;
    }

}
