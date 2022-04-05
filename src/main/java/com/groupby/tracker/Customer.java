
package com.groupby.tracker;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * customer component
 * <p>
 * Contains data about the GroupBy customer sending the beacons.
 * 
 */
class Customer implements Parcelable
{

    /**
     * The customer's customer ID. Must be alphanumeric and must start with an alphabetic character.
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * The customer's area.
     * (Required)
     * 
     */
    @SerializedName("area")
    @Expose
    private String area;
    public final static Creator<Customer> CREATOR = new Creator<Customer>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Customer createFromParcel(android.os.Parcel in) {
            return new Customer(in);
        }

        public Customer[] newArray(int size) {
            return (new Customer[size]);
        }

    }
    ;

    protected Customer(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.area = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Customer() {
    }

    /**
     * 
     * @param area
     * @param id
     */
    public Customer(String id, String area) {
        super();
        this.id = id;
        this.area = area;
    }

    /**
     * The customer's customer ID. Must be alphanumeric and must start with an alphabetic character.
     * (Required)
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * The customer's customer ID. Must be alphanumeric and must start with an alphabetic character.
     * (Required)
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The customer's area.
     * (Required)
     * 
     */
    public String getArea() {
        return area;
    }

    /**
     * The customer's area.
     * (Required)
     * 
     */
    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Customer.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("area");
        sb.append('=');
        sb.append(((this.area == null)?"<null>":this.area));
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
        result = ((result* 31)+((this.area == null)? 0 :this.area.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Customer) == false) {
            return false;
        }
        Customer rhs = ((Customer) other);
        return (((this.area == rhs.area)||((this.area!= null)&&this.area.equals(rhs.area)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(area);
    }

    public int describeContents() {
        return  0;
    }

}
