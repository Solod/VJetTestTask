package ua.com.solodilov.evgen.vjettesttask.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private String id;
    private String firstName, lastName;
    private String email;
    private String birthday;
    private Uri avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Uri getAvatar() {
        return avatar;
    }

    public void setAvatar(Uri avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.birthday);
        dest.writeParcelable(this.avatar, flags);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.id = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.birthday = in.readString();
        this.avatar = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
