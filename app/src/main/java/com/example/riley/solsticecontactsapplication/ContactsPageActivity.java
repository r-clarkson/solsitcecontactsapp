package com.example.riley.solsticecontactsapplication;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static android.R.id.list;
import static com.example.riley.solsticecontactsapplication.R.layout.contact_page;

public class ContactsPageActivity extends ListActivity {
    /** private list of contacts and the url for json endpoint */
    private List<Contact> contacts;
    private String url;

    /**
     * parses info using further methods and then displays it in list view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contacts = new LinkedList<Contact>();
        setContentView(contact_page);
        url ="https://s3.amazonaws.com/technical-challenge/v3/contacts.json";
        new GetJSONObjects(contacts).execute(url);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        Contact contact = (Contact) getListAdapter().getItem(position);
        displayContact(contact);
    }

    public void displayContact(Contact contact){
        Intent intent = new Intent(this, ContactPageActivity.class);
        intent.putExtra("contact",contact);
        ContactsPageActivity.this.startActivity(intent);
    }

    /**
     * Async task due to network access
     */
    private class GetJSONObjects extends AsyncTask<String, Void, List<Contact>> {
        List<Contact> c;

        public GetJSONObjects(List<Contact> c) {
            this.c = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Contact> doInBackground(String... url) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String contactObjectsJSON = null;

            try{
                contactObjectsJSON = new Scanner(new URL(url[0]).openStream(),"UTF-8").useDelimiter("\\A").next();
                c = mapper.readValue(contactObjectsJSON,new TypeReference<List<Contact>>(){});
                if (getIntent().hasExtra("contact")){
                    c = replaceContact( (Contact) getIntent().getParcelableExtra("contact"),c);
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return c;
        }
        @Override
        protected void onPostExecute(List<Contact> c2) {
            if(c2 != null){
               c2 = sortList(c2);
                System.out.println(c2.size());
               ContactsAdapter adapter = new ContactsAdapter(getApplicationContext(),c2);
               setListAdapter(adapter);
            } else {
                System.out.println("Error loading contacts adapter");
            }
        }

        public List<Contact> sortList(List<Contact> fullList) {
            LinkedList<Contact> favoritesList = new LinkedList<Contact>();
            LinkedList<Contact> regularList = new LinkedList<Contact>();
            LinkedList<Contact> list = new LinkedList<Contact>();

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

        public List<Contact> replaceContact(Contact contact, List<Contact> contactsList){
            for (Contact c : contactsList){
                if (c.getName().equals(contact.getName())){
                    contactsList.remove(c);
                    contactsList.add(contact);
                }
            }
            return contactsList;
        }

    }

    class SampleComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {

            return o1.getName().compareTo(o2.getName());
        }
    }

}
