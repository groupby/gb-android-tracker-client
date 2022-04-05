
package com.groupby.tracker.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * A metadata item.
 * 
 */
public class Metadata implements Parcelable
{

    /**
     * The metadata item's key.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    private String key;
    /**
     * The metadata item's value.
     * (Required)
     * 
     */
    @SerializedName("value")
    @Expose
    private String value;
    public final static Creator<Metadata> CREATOR = new Creator<Metadata>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Metadata createFromParcel(android.os.Parcel in) {
            return new Metadata(in);
        }

        public Metadata[] newArray(int size) {
            return (new Metadata[size]);
        }

    }
    ;

    protected Metadata(android.os.Parcel in) {
        this.key = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Metadata() {
    }

    /**
     * 
     * @param value
     * @param key
     */
    public Metadata(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    /**
     * The metadata item's key.
     * (Required)
     * 
     */
    public String getKey() {
        return key;
    }

    /**
     * The metadata item's key.
     * (Required)
     * 
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * The metadata item's value.
     * (Required)
     * 
     */
    public String getValue() {
        return value;
    }

    /**
     * The metadata item's value.
     * (Required)
     * 
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Metadata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("key");
        sb.append('=');
        sb.append(((this.key == null)?"<null>":this.key));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
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
        result = ((result* 31)+((this.value == null)? 0 :this.value.hashCode()));
        result = ((result* 31)+((this.key == null)? 0 :this.key.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Metadata) == false) {
            return false;
        }
        Metadata rhs = ((Metadata) other);
        return (((this.value == rhs.value)||((this.value!= null)&&this.value.equals(rhs.value)))&&((this.key == rhs.key)||((this.key!= null)&&this.key.equals(rhs.key))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(key);
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
