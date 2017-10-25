package com.example.riley.solsticecontactsapplication.Model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public Contact(){

    }

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

    public String[] getWorkArray() { return phone.getWorkArray();}

    public String getHome(){
        return phone.getHome();
    }

    public String[] getHomeArray() { return phone.getHomeArray();}

    public String getMobile(){
        return phone.getMobile();
    }

    public String[] getMobileArray() { return phone.getMobileArray();}

    public void setPhoneNumber(PhoneNumbers phoneNumbers) {
        this.phone = phoneNumbers;
    }

    public String getBirthdate() {
        return birthdate;
    }

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


    public static class Address implements Parcelable{
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

        public String toString(){
            return street +" "+  city +" "+ state +" "+ country +" "+ zipCode;
        }

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

    public static class PhoneNumbers implements Parcelable{
        String home;
        String mobile;
        String work;
        public PhoneNumbers(){

        }

        public PhoneNumbers(Parcel in){
            home = in.readString();
            mobile = in.readString();
            work = in.readString();
        }

        public String getHome() {
            return home;
        }

        public String[] getHomeArray(){
            String[] homeArray = {"Phone:",home,"Home"};
            return homeArray;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getMobile() {
            return mobile;
        }

        public String[] getMobileArray(){
            String[] mobileArray = {"Phone:",mobile,"Mobile"};
            return mobileArray;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWork() {
            return work;
        }

        public String[] getWorkArray(){
            String[] workArray = {"Phone:",work,"Work"};
            return workArray;
        }

        public void setWork(String work) {
            this.work = work;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(home);
            dest.writeString(mobile);
            dest.writeString(work);
        }
        public static final Parcelable.Creator<PhoneNumbers> CREATOR = new Parcelable.Creator<PhoneNumbers>() {
            public PhoneNumbers createFromParcel(Parcel in) {
                return new PhoneNumbers(in);
            }

            public PhoneNumbers[] newArray(int size) {
                return new PhoneNumbers[size];
            }
        };
    }
}

