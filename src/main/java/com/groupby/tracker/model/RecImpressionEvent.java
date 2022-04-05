
package com.groupby.tracker.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * recImpression event component
 * <p>
 * The event data for a recImpression event.
 * 
 */
public class RecImpressionEvent implements Parcelable
{

    /**
     * The products the shopper witnessed during the event. It's valid for it to be just one product (ex. a prompt for them to add a particular product to their cart) and also multiple products (ex. a carousel of product recommendations displayed as they scroll down a PLP).The max value of 50 items is something we think will fit most customers' recommendation use cases. They probably only want to display a few products at a time in a carousel. We can increase this in the future if a customer presents a use case for it and if we think it won't negatively affect our systems.
     * (Required)
     * 
     */
    @SerializedName("products")
    @Expose
    private List<Product> products = new ArrayList<Product>();
    /**
     * Google attribution token
     * <p>
     * The Google attribution token as described in Google Cloud Platform's documentation for Cloud Retail Solutions (https://cloud.google.com/retail/docs/attribution-tokens). Instructions for implementing this are evolving over time. If you use GroupBy's Google-powered platform, reach out to your Customer Success rep to find out whether you need to implement this property and if so, how you should do it.Because Google decides what these values are, we don't know the max length. We're using 100 right now just to protect our system and we can increase or decrease it in the future if we need to without it being a breaking change because customers are instructed to use the token value as is.
     * 
     */
    @SerializedName("googleAttributionToken")
    @Expose
    private String googleAttributionToken;
    public final static Creator<RecImpressionEvent> CREATOR = new Creator<RecImpressionEvent>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RecImpressionEvent createFromParcel(android.os.Parcel in) {
            return new RecImpressionEvent(in);
        }

        public RecImpressionEvent[] newArray(int size) {
            return (new RecImpressionEvent[size]);
        }

    }
    ;

    protected RecImpressionEvent(android.os.Parcel in) {
        in.readList(this.products, (Product.class.getClassLoader()));
        this.googleAttributionToken = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public RecImpressionEvent() {
    }

    /**
     * 
     * @param googleAttributionToken
     * @param products
     */
    public RecImpressionEvent(List<Product> products, String googleAttributionToken) {
        super();
        this.products = products;
        this.googleAttributionToken = googleAttributionToken;
    }

    /**
     * The products the shopper witnessed during the event. It's valid for it to be just one product (ex. a prompt for them to add a particular product to their cart) and also multiple products (ex. a carousel of product recommendations displayed as they scroll down a PLP).The max value of 50 items is something we think will fit most customers' recommendation use cases. They probably only want to display a few products at a time in a carousel. We can increase this in the future if a customer presents a use case for it and if we think it won't negatively affect our systems.
     * (Required)
     * 
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * The products the shopper witnessed during the event. It's valid for it to be just one product (ex. a prompt for them to add a particular product to their cart) and also multiple products (ex. a carousel of product recommendations displayed as they scroll down a PLP).The max value of 50 items is something we think will fit most customers' recommendation use cases. They probably only want to display a few products at a time in a carousel. We can increase this in the future if a customer presents a use case for it and if we think it won't negatively affect our systems.
     * (Required)
     * 
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Google attribution token
     * <p>
     * The Google attribution token as described in Google Cloud Platform's documentation for Cloud Retail Solutions (https://cloud.google.com/retail/docs/attribution-tokens). Instructions for implementing this are evolving over time. If you use GroupBy's Google-powered platform, reach out to your Customer Success rep to find out whether you need to implement this property and if so, how you should do it.Because Google decides what these values are, we don't know the max length. We're using 100 right now just to protect our system and we can increase or decrease it in the future if we need to without it being a breaking change because customers are instructed to use the token value as is.
     * 
     */
    public String getGoogleAttributionToken() {
        return googleAttributionToken;
    }

    /**
     * Google attribution token
     * <p>
     * The Google attribution token as described in Google Cloud Platform's documentation for Cloud Retail Solutions (https://cloud.google.com/retail/docs/attribution-tokens). Instructions for implementing this are evolving over time. If you use GroupBy's Google-powered platform, reach out to your Customer Success rep to find out whether you need to implement this property and if so, how you should do it.Because Google decides what these values are, we don't know the max length. We're using 100 right now just to protect our system and we can increase or decrease it in the future if we need to without it being a breaking change because customers are instructed to use the token value as is.
     * 
     */
    public void setGoogleAttributionToken(String googleAttributionToken) {
        this.googleAttributionToken = googleAttributionToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RecImpressionEvent.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("products");
        sb.append('=');
        sb.append(((this.products == null)?"<null>":this.products));
        sb.append(',');
        sb.append("googleAttributionToken");
        sb.append('=');
        sb.append(((this.googleAttributionToken == null)?"<null>":this.googleAttributionToken));
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
        result = ((result* 31)+((this.googleAttributionToken == null)? 0 :this.googleAttributionToken.hashCode()));
        result = ((result* 31)+((this.products == null)? 0 :this.products.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RecImpressionEvent) == false) {
            return false;
        }
        RecImpressionEvent rhs = ((RecImpressionEvent) other);
        return (((this.googleAttributionToken == rhs.googleAttributionToken)||((this.googleAttributionToken!= null)&&this.googleAttributionToken.equals(rhs.googleAttributionToken)))&&((this.products == rhs.products)||((this.products!= null)&&this.products.equals(rhs.products))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(products);
        dest.writeValue(googleAttributionToken);
    }

    public int describeContents() {
        return  0;
    }

}
