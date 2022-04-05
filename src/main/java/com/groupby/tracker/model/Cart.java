
package com.groupby.tracker.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * cart component
 * <p>
 * Contains data about the cart in an event involving interacting with a cart.
 * 
 */
public class Cart implements Parcelable
{

    /**
     * The cart items in the cart.We require at least 1 because an empty cart makes no sense. If a customer comes to us with a use case for this, we can consider relaxing the constraint. We disallow more than 1000 per cart because we feel this is a reasonable value. We can relax this if a customer says they can have more items than this in a cart and we think it won't negatively impact our system.
     * (Required)
     * 
     */
    @SerializedName("items")
    @Expose
    private List<CartItem> items = new ArrayList<CartItem>();
    /**
     * The cart's type. A freeform text field that can be used by the GroupBy customer to indicate what kind of cart products were added to, removed from, or ordered from. Example: "wedding registry".
     * 
     */
    @SerializedName("type")
    @Expose
    private String type;
    public final static Creator<Cart> CREATOR = new Creator<Cart>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Cart createFromParcel(android.os.Parcel in) {
            return new Cart(in);
        }

        public Cart[] newArray(int size) {
            return (new Cart[size]);
        }

    }
    ;

    protected Cart(android.os.Parcel in) {
        in.readList(this.items, (CartItem.class.getClassLoader()));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Cart() {
    }

    /**
     * 
     * @param type
     * @param items
     */
    public Cart(List<CartItem> items, String type) {
        super();
        this.items = items;
        this.type = type;
    }

    /**
     * The cart items in the cart.We require at least 1 because an empty cart makes no sense. If a customer comes to us with a use case for this, we can consider relaxing the constraint. We disallow more than 1000 per cart because we feel this is a reasonable value. We can relax this if a customer says they can have more items than this in a cart and we think it won't negatively impact our system.
     * (Required)
     * 
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * The cart items in the cart.We require at least 1 because an empty cart makes no sense. If a customer comes to us with a use case for this, we can consider relaxing the constraint. We disallow more than 1000 per cart because we feel this is a reasonable value. We can relax this if a customer says they can have more items than this in a cart and we think it won't negatively impact our system.
     * (Required)
     * 
     */
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    /**
     * The cart's type. A freeform text field that can be used by the GroupBy customer to indicate what kind of cart products were added to, removed from, or ordered from. Example: "wedding registry".
     * 
     */
    public String getType() {
        return type;
    }

    /**
     * The cart's type. A freeform text field that can be used by the GroupBy customer to indicate what kind of cart products were added to, removed from, or ordered from. Example: "wedding registry".
     * 
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Cart.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("items");
        sb.append('=');
        sb.append(((this.items == null)?"<null>":this.items));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
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
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.items == null)? 0 :this.items.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Cart) == false) {
            return false;
        }
        Cart rhs = ((Cart) other);
        return (((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type)))&&((this.items == rhs.items)||((this.items!= null)&&this.items.equals(rhs.items))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(items);
        dest.writeValue(type);
    }

    public int describeContents() {
        return  0;
    }

}
