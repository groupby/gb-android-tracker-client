
package com.groupby.tracker.model;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;


/**
 * The event data. This can be any JSON object. GroupBy will provide instructions for what to serialize into this JSON object if you are instructed to implement this event.This is intentionally minimalist. Because Wisdom doesn't understand what parts of this beacon should be filled in, this allows the customer to send us any data. We can assist the customer with custom instructions as we discover what needs they have, adding parsing code to the handler alongside the instructions we give the customer. These updates can be made without additional work for the GroupBy contractor creating and maintaining our native app SDKs. This property should be implemented in the native app SDKs using whichever data type allows the customer to send us data that the SDK's JSON implementation would transform to a JSON object (ex. a map data structure).
 * 
 */
public class Event implements Parcelable
{

    public final static Creator<Event> CREATOR = new Creator<Event>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Event createFromParcel(android.os.Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return (new Event[size]);
        }

    }
    ;

    protected Event(android.os.Parcel in) {
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Event() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Event.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Event) == false) {
            return false;
        }
        Event rhs = ((Event) other);
        return true;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
    }

    public int describeContents() {
        return  0;
    }

}
