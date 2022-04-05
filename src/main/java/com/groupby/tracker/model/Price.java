
package com.groupby.tracker.model;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * price component
 * <p>
 * Contains data about the price of a product in event types that involve products.
 *
 */
public class Price implements Parcelable
{

    /**
     *
     * (Required)
     *
     */
    @SerializedName("onSale")
    @Expose
    private Boolean onSale;
    /**
     * The regular price of the product (when it is not on sale).
     *
     */
    @SerializedName("regular")
    @Expose
    private String regular;
    /**
     * The price the customer would pay (if viewing) or paid (for order events) for the product.
     * (Required)
     *
     */
    @SerializedName("actual")
    @Expose
    private String actual;
    /**
     * The ISO 4217 code of the currency for the product.Instead of patterns here for the ISO 4217 code, we're allowing any three character string that way customer using any currency can be onboarded even if we aren't aware of the currency code as we develop this. This property is intentionally part of this submodel instead of the price submodel so we can minimize repeated code in the price submodel.
     * (Required)
     *
     */
    @SerializedName("currency")
    @Expose
    private String currency;
    public final static Creator<Price> CREATOR = new Creator<Price>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Price createFromParcel(android.os.Parcel in) {
            return new Price(in);
        }

        public Price[] newArray(int size) {
            return (new Price[size]);
        }

    }
            ;

    protected Price(android.os.Parcel in) {
        this.onSale = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.regular = ((String) in.readValue((String.class.getClassLoader())));
        this.actual = ((String) in.readValue((String.class.getClassLoader())));
        this.currency = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Price() {
    }

    /**
     *
     * @param actual
     * @param onSale
     * @param currency
     * @param regular
     */
    public Price(Boolean onSale, String regular, String actual, String currency) {
        super();
        this.onSale = onSale;
        this.regular = regular;
        this.actual = actual;
        this.currency = currency;
    }

    /**
     *
     * (Required)
     *
     */
    public Boolean getOnSale() {
        return onSale;
    }

    /**
     *
     * (Required)
     *
     */
    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    /**
     * The regular price of the product (when it is not on sale).
     *
     */
    public String getRegular() {
        return regular;
    }

    /**
     * The regular price of the product (when it is not on sale).
     *
     */
    public void setRegular(String regular) {
        this.regular = regular;
    }

    /**
     * The price the customer would pay (if viewing) or paid (for order events) for the product.
     * (Required)
     *
     */
    public String getActual() {
        return actual;
    }

    /**
     * The price the customer would pay (if viewing) or paid (for order events) for the product.
     * (Required)
     *
     */
    public void setActual(String actual) {
        this.actual = actual;
    }

    /**
     * The ISO 4217 code of the currency for the product.Instead of patterns here for the ISO 4217 code, we're allowing any three character string that way customer using any currency can be onboarded even if we aren't aware of the currency code as we develop this. This property is intentionally part of this submodel instead of the price submodel so we can minimize repeated code in the price submodel.
     * (Required)
     *
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * The ISO 4217 code of the currency for the product.Instead of patterns here for the ISO 4217 code, we're allowing any three character string that way customer using any currency can be onboarded even if we aren't aware of the currency code as we develop this. This property is intentionally part of this submodel instead of the price submodel so we can minimize repeated code in the price submodel.
     * (Required)
     *
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Price.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("onSale");
        sb.append('=');
        sb.append(((this.onSale == null)?"<null>":this.onSale));
        sb.append(',');
        sb.append("regular");
        sb.append('=');
        sb.append(((this.regular == null)?"<null>":this.regular));
        sb.append(',');
        sb.append("actual");
        sb.append('=');
        sb.append(((this.actual == null)?"<null>":this.actual));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
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
        result = ((result* 31)+((this.onSale == null)? 0 :this.onSale.hashCode()));
        result = ((result* 31)+((this.actual == null)? 0 :this.actual.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.regular == null)? 0 :this.regular.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Price) == false) {
            return false;
        }
        Price rhs = ((Price) other);
        return (((((this.onSale == rhs.onSale)||((this.onSale!= null)&&this.onSale.equals(rhs.onSale)))&&((this.actual == rhs.actual)||((this.actual!= null)&&this.actual.equals(rhs.actual))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.regular == rhs.regular)||((this.regular!= null)&&this.regular.equals(rhs.regular))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(onSale);
        dest.writeValue(regular);
        dest.writeValue(actual);
        dest.writeValue(currency);
    }

    public int describeContents() {
        return  0;
    }

}
