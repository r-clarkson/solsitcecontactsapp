package com.example.riley.solsticecontactsapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Similar to Address and Contact, is parcelable and contains two constructors as well as a function to return
 * as String[] for the full_contact_row information placed in ContactAdapter
 */
public class PhoneNumbers implements Parcelable {
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

