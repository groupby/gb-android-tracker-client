
package com.groupby.tracker.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * product component
 * <p>
 * Contains data about one of the GroupBy customer's products. Used by all event types that relate to product data.The old beacon models had the property margin part of them. Here, that property has been removed because it probably doesn't make sense. Our customers probably don't want to transmit information about the profit they earn on each sale publicly (ex. Chrome Dev Tools). In the future, if we want to add this feature back in, we can create from-client and from-server variants of this submodel so that only the from-server variant can have a property the customer can use to tell us how much profit they earned. Right now, products always require prices. If a customer ever has a use case where they need to send a recImpression event without a price, we can do a refactor where we have an "impressioned product" submodel with optional price, and that should be a backwards compatible change.
 * 
 */
public class Product implements Parcelable
{

    /**
     * The product's ID in the GroupBy customer's system.Max length of 36 is to allow customers to use v4 UUIDs for their IDs, but strings no longer than that.
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * The product's SKU in the GroupBy customer's system.Max length of 73 is to allow customers to use v4 UUIDs for their IDs and SKUs, with the SKUs being the ID and SKU concatenated together with no more than one character, but strings no longer than that.
     * 
     */
    @SerializedName("sku")
    @Expose
    private String sku;
    /**
     * The product's title. This is used in GroupBy UIs that render information about the product.
     * (Required)
     * 
     */
    @SerializedName("title")
    @Expose
    private String title;
    /**
     * The collection the product belongs to in GroupBy's systems after it has been uploaded to GroupBy.
     * 
     */
    @SerializedName("collection")
    @Expose
    private String collection = "default";
    /**
     * The category of the product in GroupBy's systems after it has been uploaded to GroupBy.
     * 
     */
    @SerializedName("category")
    @Expose
    private String category;
    /**
     * price component
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("price")
    @Expose
    private Price price;
    public final static Creator<Product> CREATOR = new Creator<Product>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Product createFromParcel(android.os.Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return (new Product[size]);
        }

    }
    ;

    protected Product(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.sku = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.collection = ((String) in.readValue((String.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Price) in.readValue((Price.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Product() {
    }

    /**
     * 
     * @param price
     * @param id
     * @param collection
     * @param sku
     * @param title
     * @param category
     */
    public Product(String id, String sku, String title, String collection, String category, Price price) {
        super();
        this.id = id;
        this.sku = sku;
        this.title = title;
        this.collection = collection;
        this.category = category;
        this.price = price;
    }

    /**
     * The product's ID in the GroupBy customer's system.Max length of 36 is to allow customers to use v4 UUIDs for their IDs, but strings no longer than that.
     * (Required)
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * The product's ID in the GroupBy customer's system.Max length of 36 is to allow customers to use v4 UUIDs for their IDs, but strings no longer than that.
     * (Required)
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The product's SKU in the GroupBy customer's system.Max length of 73 is to allow customers to use v4 UUIDs for their IDs and SKUs, with the SKUs being the ID and SKU concatenated together with no more than one character, but strings no longer than that.
     * 
     */
    public String getSku() {
        return sku;
    }

    /**
     * The product's SKU in the GroupBy customer's system.Max length of 73 is to allow customers to use v4 UUIDs for their IDs and SKUs, with the SKUs being the ID and SKU concatenated together with no more than one character, but strings no longer than that.
     * 
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * The product's title. This is used in GroupBy UIs that render information about the product.
     * (Required)
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * The product's title. This is used in GroupBy UIs that render information about the product.
     * (Required)
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The collection the product belongs to in GroupBy's systems after it has been uploaded to GroupBy.
     * 
     */
    public String getCollection() {
        return collection;
    }

    /**
     * The collection the product belongs to in GroupBy's systems after it has been uploaded to GroupBy.
     * 
     */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    /**
     * The category of the product in GroupBy's systems after it has been uploaded to GroupBy.
     * 
     */
    public String getCategory() {
        return category;
    }

    /**
     * The category of the product in GroupBy's systems after it has been uploaded to GroupBy.
     * 
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * price component
     * <p>
     * 
     * (Required)
     * 
     */
    public Price getPrice() {
        return price;
    }

    /**
     * price component
     * <p>
     * 
     * (Required)
     * 
     */
    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Product.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("sku");
        sb.append('=');
        sb.append(((this.sku == null)?"<null>":this.sku));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("collection");
        sb.append('=');
        sb.append(((this.collection == null)?"<null>":this.collection));
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null)?"<null>":this.category));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
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
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.collection == null)? 0 :this.collection.hashCode()));
        result = ((result* 31)+((this.sku == null)? 0 :this.sku.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.category == null)? 0 :this.category.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Product) == false) {
            return false;
        }
        Product rhs = ((Product) other);
        return (((((((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.collection == rhs.collection)||((this.collection!= null)&&this.collection.equals(rhs.collection))))&&((this.sku == rhs.sku)||((this.sku!= null)&&this.sku.equals(rhs.sku))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.category == rhs.category)||((this.category!= null)&&this.category.equals(rhs.category))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(sku);
        dest.writeValue(title);
        dest.writeValue(collection);
        dest.writeValue(category);
        dest.writeValue(price);
    }

    public int describeContents() {
        return  0;
    }

}
