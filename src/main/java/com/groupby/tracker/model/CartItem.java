
package com.groupby.tracker.model;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * cart item component
 * <p>
 * Contains data about one of the items in a shopper's cart. used for all event types involving carts.The old beacon models had separate submodels for product and cart item, where the cart item submodel was the data from the product submodel with a quantity property added. Here, we're reusing the new product submodel to avoid repeating schema code.
 */
public class CartItem implements Parcelable {

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
     * The quantity of this product added to the cart, removed from the cart, or ordered, depending on the event type. Note that this should not be the number of items in the cart after the operation the shopper is performing is finished. For example, if the event type is addToCart, and the shopper started with 2 items in the cart, then added 1 more, the quantity in the addToCart event should be set to 1, not 3.Maximum of 2,147,483,647 is to allow any positive 32-bit signed number.
     * (Required)
     */
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    public final static Creator<CartItem> CREATOR = new Creator<CartItem>() {

        public CartItem createFromParcel(android.os.Parcel in) {
            return new CartItem(in);
        }

        public CartItem[] newArray(int size) {
            return (new CartItem[size]);
        }

    };

    protected CartItem(android.os.Parcel in) {
        this.product = ((Product) in.readValue((Product.class.getClassLoader())));
        this.quantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CartItem() {
    }

    /**
     * @param product
     * @param quantity
     */
    public CartItem(Product product, Integer quantity) {
        super();
        this.product = product;
        this.quantity = quantity;
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
     * The quantity of this product added to the cart, removed from the cart, or ordered, depending on the event type. Note that this should not be the number of items in the cart after the operation the shopper is performing is finished. For example, if the event type is addToCart, and the shopper started with 2 items in the cart, then added 1 more, the quantity in the addToCart event should be set to 1, not 3.Maximum of 2,147,483,647 is to allow any positive 32-bit signed number.
     * (Required)
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * The quantity of this product added to the cart, removed from the cart, or ordered, depending on the event type. Note that this should not be the number of items in the cart after the operation the shopper is performing is finished. For example, if the event type is addToCart, and the shopper started with 2 items in the cart, then added 1 more, the quantity in the addToCart event should be set to 1, not 3.Maximum of 2,147,483,647 is to allow any positive 32-bit signed number.
     * (Required)
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CartItem.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("product");
        sb.append('=');
        sb.append(((this.product == null) ? "<null>" : this.product));
        sb.append(',');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null) ? "<null>" : this.quantity));
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
        result = ((result * 31) + ((this.product == null) ? 0 : this.product.hashCode()));
        result = ((result * 31) + ((this.quantity == null) ? 0 : this.quantity.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CartItem) == false) {
            return false;
        }
        CartItem rhs = ((CartItem) other);
        return (((this.product == rhs.product) || ((this.product != null) && this.product.equals(rhs.product))) && ((this.quantity == rhs.quantity) || ((this.quantity != null) && this.quantity.equals(rhs.quantity))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(product);
        dest.writeValue(quantity);
    }

    public int describeContents() {
        return 0;
    }

}
