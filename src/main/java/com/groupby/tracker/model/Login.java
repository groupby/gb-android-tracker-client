
package com.groupby.tracker.model;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Data about the shopper's logged in/logged out status.This complex object is used instead of an optional string to help us avoid problems we've had in the past where a customer thinks that when the shopper is logged out, they're supposed to put a string value like "anonymous" in the optional string property. We think this will help us avoid this confusion.
 * 
 */
public class Login implements Parcelable
{
    /**
     * Whether the shopper was logged in the event in this beacon occured.
     * (Required)
     *
     */
    @SerializedName("loggedIn")
    @Expose
    private Boolean loggedIn;
    /**
     * The shopper's username in the GroupBy customer's system.
     * (Required)
     *
     */
    @SerializedName("username")
    @Expose
    private String username;
    public final static Creator<Login> CREATOR = new Creator<Login>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Login createFromParcel(android.os.Parcel in) {
            return new Login(in);
        }

        public Login[] newArray(int size) {
            return (new Login[size]);
        }

    }
    ;

    protected Login(android.os.Parcel in) {
        this.loggedIn = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Login() {
    }

    /**
     *
     * @param loggedIn
     * @param username
     */
    public Login(Boolean loggedIn, String username) {
        super();
        this.loggedIn = loggedIn;
        this.username = username;
    }

    /**
     * login component
     * <p>
     * Whether the shopper was logged in the event in this beacon occured.
     * (Required)
     *
     */
    public Boolean getLoggedIn() {
        return loggedIn;
    }

    /**
     * login component
     * <p>
     * Whether the shopper was logged in the event in this beacon occured.
     * (Required)
     *
     */
    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * The shopper's username in the GroupBy customer's system.
     * (Required)
     *
     */
    public String getUsername() {
        return username;
    }

    /**
     * The shopper's username in the GroupBy customer's system.
     * (Required)
     *
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Login.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("loggedIn");
        sb.append('=');
        sb.append(((this.loggedIn == null)?"<null>":this.loggedIn));
        sb.append(',');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.loggedIn == null)? 0 :this.loggedIn.hashCode()));
        result = ((result* 31)+((this.username == null)? 0 :this.username.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Login) == false) {
            return false;
        }
        Login rhs = ((Login) other);
        return (((this.loggedIn == rhs.loggedIn)||((this.loggedIn!= null)&&this.loggedIn.equals(rhs.loggedIn)))&&((this.username == rhs.username)||((this.username!= null)&&this.username.equals(rhs.username))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(loggedIn);
        dest.writeValue(username);
    }

    public int describeContents() {
        return  0;
    }

}
