
package com.groupby.tracker.model;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * viewProduct event component
 * <p>
 * The event data for a viewProduct event.
 */
public class ViewProductEvent implements Parcelable {

    /**
     * product component
     * <p>
     * Contains data about one of the GroupBy customer's products. Used by all event types that relate to product data.The old beacon models had the property margin part of them. Here, that property has been removed because it probably doesn't make sense. Our customers probably don't want to transmit information about the profit they earn on each sale publicly (ex. Chrome Dev Tools). In the future, if we want to add this feature back in, we can create from-client and from-server variants of this submodel so that only the from-server variant can have a property the customer can use to tell us how much profit they earned. Right now, products always require prices. If a customer ever has a use case where they need to send a recImpression event without a price, we can do a refactor where we have an "impressioned product" submodel with optional price, and that should be a backwards compatible change.
     * (Required)
     */
    @SerializedName("product")
    @Expose
    private Product product;
    /**
     * Google attribution token
     * <p>
     * The Google attribution token as described in Google Cloud Platform's documentation for Cloud Retail Solutions (https://cloud.google.com/retail/docs/attribution-tokens). Instructions for implementing this are evolving over time. If you use GroupBy's Google-powered platform, reach out to your Customer Success rep to find out whether you need to implement this property and if so, how you should do it.Because Google decides what these values are, we don't know the max length. We're using 100 right now just to protect our system and we can increase or decrease it in the future if we need to without it being a breaking change because customers are instructed to use the token value as is.
     */
    @SerializedName("googleAttributionToken")
    @Expose
    private String googleAttributionToken;
    public final static Creator<ViewProductEvent> CREATOR = new Creator<ViewProductEvent>() {

        public ViewProductEvent createFromParcel(android.os.Parcel in) {
            return new ViewProductEvent(in);
        }

        public ViewProductEvent[] newArray(int size) {
            return (new ViewProductEvent[size]);
        }

    };

    protected ViewProductEvent(android.os.Parcel in) {
        this.product = ((Product) in.readValue((Product.class.getClassLoader())));
        this.googleAttributionToken = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public ViewProductEvent() {
    }

    public ViewProductEvent(Product product) {
        this.product = product;
    }

    public ViewProductEvent(Product product, String googleAttributionToken) {
        super();
        this.product = product;
        this.googleAttributionToken = googleAttributionToken;
    }

    /**
     * product component
     * <p>
     * Contains data about one of the GroupBy customer's products. Used by all event types that relate to product data.The old beacon models had the property margin part of them. Here, that property has been removed because it probably doesn't make sense. Our customers probably don't want to transmit information about the profit they earn on each sale publicly (ex. Chrome Dev Tools). In the future, if we want to add this feature back in, we can create from-client and from-server variants of this submodel so that only the from-server variant can have a property the customer can use to tell us how much profit they earned. Right now, products always require prices. If a customer ever has a use case where they need to send a recImpression event without a price, we can do a refactor where we have an "impressioned product" submodel with optional price, and that should be a backwards compatible change.
     * (Required)
     */
    public Product getProduct() {
        return product;
    }

    /**
     * product component
     * <p>
     * Contains data about one of the GroupBy customer's products. Used by all event types that relate to product data.The old beacon models had the property margin part of them. Here, that property has been removed because it probably doesn't make sense. Our customers probably don't want to transmit information about the profit they earn on each sale publicly (ex. Chrome Dev Tools). In the future, if we want to add this feature back in, we can create from-client and from-server variants of this submodel so that only the from-server variant can have a property the customer can use to tell us how much profit they earned. Right now, products always require prices. If a customer ever has a use case where they need to send a recImpression event without a price, we can do a refactor where we have an "impressioned product" submodel with optional price, and that should be a backwards compatible change.
     * (Required)
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Google attribution token
     * <p>
     * The Google attribution token as described in Google Cloud Platform's documentation for Cloud Retail Solutions (https://cloud.google.com/retail/docs/attribution-tokens). Instructions for implementing this are evolving over time. If you use GroupBy's Google-powered platform, reach out to your Customer Success rep to find out whether you need to implement this property and if so, how you should do it.Because Google decides what these values are, we don't know the max length. We're using 100 right now just to protect our system and we can increase or decrease it in the future if we need to without it being a breaking change because customers are instructed to use the token value as is.
     */
    public String getGoogleAttributionToken() {
        return googleAttributionToken;
    }

    /**
     * Google attribution token
     * <p>
     * The Google attribution token as described in Google Cloud Platform's documentation for Cloud Retail Solutions (https://cloud.google.com/retail/docs/attribution-tokens). Instructions for implementing this are evolving over time. If you use GroupBy's Google-powered platform, reach out to your Customer Success rep to find out whether you need to implement this property and if so, how you should do it.Because Google decides what these values are, we don't know the max length. We're using 100 right now just to protect our system and we can increase or decrease it in the future if we need to without it being a breaking change because customers are instructed to use the token value as is.
     */
    public void setGoogleAttributionToken(String googleAttributionToken) {
        this.googleAttributionToken = googleAttributionToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ViewProductEvent.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("product");
        sb.append('=');
        sb.append(((this.product == null) ? "<null>" : this.product));
        sb.append(',');
        sb.append("googleAttributionToken");
        sb.append('=');
        sb.append(((this.googleAttributionToken == null) ? "<null>" : this.googleAttributionToken));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.googleAttributionToken == null) ? 0 : this.googleAttributionToken.hashCode()));
        result = ((result * 31) + ((this.product == null) ? 0 : this.product.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ViewProductEvent) == false) {
            return false;
        }
        ViewProductEvent rhs = ((ViewProductEvent) other);
        return (((this.googleAttributionToken == rhs.googleAttributionToken) || ((this.googleAttributionToken != null) && this.googleAttributionToken.equals(rhs.googleAttributionToken))) && ((this.product == rhs.product) || ((this.product != null) && this.product.equals(rhs.product))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(product);
        dest.writeValue(googleAttributionToken);
    }

    public int describeContents() {
        return 0;
    }

}
