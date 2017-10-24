package com.example.riley.solsticecontactsapplication;


public class Contact {

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

    private boolean editable;

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

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWork() {
        return phone.getWork();
    }

    public String getHome(){
        return phone.getHome();
    }

    public String getMobile(){
        return phone.getMobile();
    }

    public void setPhoneNumber(PhoneNumbers phoneNumbers) {
        this.phone = phoneNumbers;
    }

    public String getBirthdate() {
        return birthdate;
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

