package com.example.riley.solsticecontactsapplication;

/**
 * Created by riley on 10/23/17.
 */

public class Contact {

    private String name;

    private Address address;

    private String companyName;

    private int idNumber;

    private String smallImageURL;
    private String largeImageURL;

    private String email;

    private PhoneNumbers phoneNumbers;

    private String birthday;

    private boolean isfavorite;

    private boolean editable;

    public Contact(){
        name = null;
        address = null;
        email = null;
        phoneNumbers = null;
        isfavorite = false;
        editable = false;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
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

    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWork() {
        return phoneNumbers.getWork();
    }

    public String getHome(){
        return phoneNumbers.getHome();
    }

    public String getMobile(){
        return phoneNumbers.getMobile();
    }

    public void setPhoneNumber(PhoneNumbers phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isIsfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(boolean isfavorite) {
        this.isfavorite = isfavorite;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    private class Address{
        String street;
        String country;
        String state;
        String zipCode;
        String city;

        public Address(){
            street = null;
            country = null;
            state = null;
            zipCode = null;
            city = null;
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

    }

    private class PhoneNumbers{
        String home;
        String mobile;
        String work;

        public PhoneNumbers(){
            home = null;
            mobile = null;
            work = null;
        }

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }
    }
}

