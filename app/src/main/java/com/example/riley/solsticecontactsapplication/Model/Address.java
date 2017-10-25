package com.example.riley.solsticecontactsapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * All strings of the components of an address....
 * Two constructors: one default, one for parcelables
 * Functions for returning needed info in String[] are for displaying info in adapter
 */
public class Address implements Parcelable {
    String street;
    String country;
    String state;
    String zipCode;
    String city;
    public Address(){

    }

    public Address(Parcel in){
        street = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
        zipCode = in.readString();
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }
    /** combines each given string */
    public String toString(){
        return street +" "+  city +" "+ state +" "+ country +" "+ zipCode;
    }

    /** returns String[] for full contact page row information */
    public String[] getAddressArray(){
        String[] addressArray = {"Address:",toString()};
        return addressArray;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(zipCode);
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

}

