package com.example.riley.solsticecontactsapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact class is parecelable and
 * contains Address and PhoneNumbers objects.
 * Two constructors: one default, one for parcelables
 * Functions for returning needed info in String[] are for displaying info in adapter
 */
public class Contact implements Parcelable {

    private String name;
    private Address address;
    private String companyName;
    private int id;
    private String smallImageURL;
    private String largeImageURL;
    private String emailAddress;
    private PhoneNumbers phone;
    private String birthdate;
    private boolean isFavorite;

    /** default constructor used for parsing JSON */
    public Contact(){
    }
    /** parcelable constructor */
    public Contact(Parcel in) {
        address = in.readParcelable(Address.class.getClassLoader());
        phone = in.readParcelable(PhoneNumbers.class.getClassLoader());
        name = in.readString();
        companyName = in.readString();
        id = in.readInt();
        smallImageURL = in.readString();
        largeImageURL = in.readString();
        emailAddress = in.readString();
        birthdate = in.readString();
        isFavorite = in.readByte() != 0;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setIdNumber(int idNumber) {
        this.id = id;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public PhoneNumbers getPhone() {
        return phone;
    }

    /** returns String[] for full contact page row information */
    public List<String[]> getPhoneArray(){
        List<String[]> numbers = new ArrayList<String[]>();
        if (phone.getMobile()!=null){
            numbers.add(phone.getMobileArray());
        }
        if (phone.getHome()!=null){
            numbers.add(phone.getHomeArray());
        }
        if (phone.getWork()!=null){
            numbers.add(phone.getWorkArray());
        }
        return numbers;
    }

    public void setPhone(PhoneNumbers phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address.toString();
    }

    public String[] getAddressArray() {return address.getAddressArray(); }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    /** returns String[] for full contact page row information */
    public String[] getEmailArray(){
        String[] emailArray = {"Email:",emailAddress};
        return emailArray;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWork() {
        return phone.getWork();
    }

    /** returns String[] for full contact page row information */
    public String[] getWorkArray() { return phone.getWorkArray();}

    public String getHome(){
        return phone.getHome();
    }

    /** returns String[] for full contact page row information */
    public String[] getHomeArray() { return phone.getHomeArray();}

    public String getMobile(){
        return phone.getMobile();
    }

    /** returns String[] for full contact page row information */
    public String[] getMobileArray() { return phone.getMobileArray();}

    public void setPhoneNumber(PhoneNumbers phoneNumbers) {
        this.phone = phoneNumbers;
    }

    public String getBirthdate() {
        return birthdate;
    }

    /** returns String[] for full contact page row information */
    public String[] getBirthdateArray() {
        String[] birthdateArray = {"Birthdate:",birthdate};
        return birthdateArray;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    /** parcelable overrides & creator*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(address,0);
        dest.writeParcelable(phone,0);
        dest.writeString(name);
        dest.writeString(companyName);
        dest.writeInt(id);
        dest.writeString(smallImageURL);
        dest.writeString(largeImageURL);
        dest.writeString(emailAddress);
        dest.writeString(birthdate);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}

