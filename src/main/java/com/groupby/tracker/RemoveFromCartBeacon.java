
package com.groupby.tracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.groupby.tracker.model.RemoveFromCartEvent;
import com.groupby.tracker.model.Experiments;
import com.groupby.tracker.model.Metadata;


/**
 * removeFromCart event sent directly from a native app client
 * <p>
 * A removeFromCart event for the native app client type (as opposed to other client types like web browsers) sent directly from the client (as opposed to sent from a server serving the client). This event is used when the shopper removes a product from their cart. Note that the only client officially supported for sending GroupBy this data is the official GroupBy native app SDK for the respective platform (Android, iOS, etc). A backwards incompatible change may be made to a major version of this schema if the change would not be backwards incompatible with respect to correct usage of the corresponding major version of the native app SDK.
 * 
 */
public class RemoveFromCartBeacon implements Parcelable
{

    /**
     * removeFromCart event component
     * <p>
     * The event data for a removeFromCart event.
     * (Required)
     * 
     */
    @SerializedName("event")
    @Expose
    private RemoveFromCartEvent event;
    /**
     * metadata component
     * <p>
     * The metadata component. Miscellaneous data that a GroupBy customer may want to include in their beacons. This is an advanced feature that is only used when the customer is talking to their Customer Success rep, who has instructed them to add certain metadata to their beacons. It is used in all event types.
     * Omit if there are no metadata items you want to send in the beacon. Limit of 20 metadata items per beacon.
     * 
     */
    @SerializedName("metadata")
    @Expose
    private List<Metadata> metadata = new ArrayList<Metadata>();
    /**
     * experiments component
     * <p>
     * Used when a GroupBy customer is performing an A/B test. Can be used for A/B testing different configurations of GroupBy services but can also be used for A/B testing different configurations for anything the customer has.
     * Omit the field if you are not running any A/B tests or if you are running A/B tests but do not wish to beacon them to GroupBy to explore them in GroupBy Analytics.
     * 
     */
    @SerializedName("experiments")
    @Expose
    private List<Experiments> experiments = new ArrayList<Experiments>();
    /**
     * time component
     * <p>
     * The time the event occured, in RFC3339 format. Preferably, with at least millisecond accuracy, but any RFC3339 format string is valid.
     * GroupBy collects this from the client instead of the server to allow us to differentiate between the time an event occurred and the time we eventually receive it.
     * (Required)
     * 
     */
    @SerializedName("time")
    @Expose
    private Date time;
    /**
     * customer component
     * <p>
     * Contains data about the GroupBy customer sending the beacons.
     * (Required)
     * 
     */
    @SerializedName("customer")
    @Expose
    private Customer customer;
    /**
     * shopper tracking component
     * <p>
     * The data used to anonymously track a shopper. See property descriptions for details on how this is anonymous tracking as opposed to tracking that infringes on the privacy of the shopper.
     * (Required)
     * 
     */
    @SerializedName("shopper")
    @Expose
    private ShopperTracking shopper;
    /**
     * Native app client component
     * <p>
     * Contains data about the client sending the beacon when the client is a native app.
     * (Required)
     * 
     */
    @SerializedName("client")
    @Expose
    private NativeAppClient client;
    public final static Creator<RemoveFromCartBeacon> CREATOR = new Creator<RemoveFromCartBeacon>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RemoveFromCartBeacon createFromParcel(android.os.Parcel in) {
            return new RemoveFromCartBeacon(in);
        }

        public RemoveFromCartBeacon[] newArray(int size) {
            return (new RemoveFromCartBeacon[size]);
        }

    }
    ;

    protected RemoveFromCartBeacon(android.os.Parcel in) {
        this.event = ((RemoveFromCartEvent) in.readValue((RemoveFromCartEvent.class.getClassLoader())));
        in.readList(this.metadata, (Metadata.class.getClassLoader()));
        in.readList(this.experiments, (Experiments.class.getClassLoader()));
        this.time = ((Date) in.readValue((Date.class.getClassLoader())));
        this.customer = ((Customer) in.readValue((Customer.class.getClassLoader())));
        this.shopper = ((ShopperTracking) in.readValue((ShopperTracking.class.getClassLoader())));
        this.client = ((NativeAppClient) in.readValue((NativeAppClient.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public RemoveFromCartBeacon() {
    }

    /**
     * 
     * @param metadata
     * @param experiments
     * @param event
     */
    public RemoveFromCartBeacon(RemoveFromCartEvent event, List<Metadata> metadata, List<Experiments> experiments) {
        super();
        this.event = event;
        this.metadata = metadata;
        this.experiments = experiments;
    }

    /**
     * removeFromCart event component
     * <p>
     * The event data for a removeFromCart event.
     * (Required)
     * 
     */
    public RemoveFromCartEvent getEvent() {
        return event;
    }

    /**
     * removeFromCart event component
     * <p>
     * The event data for a removeFromCart event.
     * (Required)
     * 
     */
    public void setEvent(RemoveFromCartEvent event) {
        this.event = event;
    }

    /**
     * metadata component
     * <p>
     * The metadata component. Miscellaneous data that a GroupBy customer may want to include in their beacons. This is an advanced feature that is only used when the customer is talking to their Customer Success rep, who has instructed them to add certain metadata to their beacons. It is used in all event types.
     * Omit if there are no metadata items you want to send in the beacon. Limit of 20 metadata items per beacon.
     * 
     */
    public List<Metadata> getMetadata() {
        return metadata;
    }

    /**
     * metadata component
     * <p>
     * The metadata component. Miscellaneous data that a GroupBy customer may want to include in their beacons. This is an advanced feature that is only used when the customer is talking to their Customer Success rep, who has instructed them to add certain metadata to their beacons. It is used in all event types.
     * Omit if there are no metadata items you want to send in the beacon. Limit of 20 metadata items per beacon.
     * 
     */
    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }

    /**
     * experiments component
     * <p>
     * Used when a GroupBy customer is performing an A/B test. Can be used for A/B testing different configurations of GroupBy services but can also be used for A/B testing different configurations for anything the customer has.
     * Omit the field if you are not running any A/B tests or if you are running A/B tests but do not wish to beacon them to GroupBy to explore them in GroupBy Analytics.
     * 
     */
    public List<Experiments> getExperiments() {
        return experiments;
    }

    /**
     * experiments component
     * <p>
     * Used when a GroupBy customer is performing an A/B test. Can be used for A/B testing different configurations of GroupBy services but can also be used for A/B testing different configurations for anything the customer has.
     * Omit the field if you are not running any A/B tests or if you are running A/B tests but do not wish to beacon them to GroupBy to explore them in GroupBy Analytics.
     * 
     */
    public void setExperiments(List<Experiments> experiments) {
        this.experiments = experiments;
    }

    /**
     * time component
     * <p>
     * The time the event occured, in RFC3339 format. Preferably, with at least millisecond accuracy, but any RFC3339 format string is valid.
     * GroupBy collects this from the client instead of the server to allow us to differentiate between the time an event occurred and the time we eventually receive it.
     * (Required)
     * 
     */
    protected Date getTime() {
        return time;
    }

    /**
     * time component
     * <p>
     * The time the event occured, in RFC3339 format. Preferably, with at least millisecond accuracy, but any RFC3339 format string is valid.
     * GroupBy collects this from the client instead of the server to allow us to differentiate between the time an event occurred and the time we eventually receive it.
     * (Required)
     * 
     */
    protected void setTime(Date time) {
        this.time = time;
    }

    /**
     * customer component
     * <p>
     * Contains data about the GroupBy customer sending the beacons.
     * (Required)
     * 
     */
    protected Customer getCustomer() {
        return customer;
    }

    /**
     * customer component
     * <p>
     * Contains data about the GroupBy customer sending the beacons.
     * (Required)
     * 
     */
    protected void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * shopper tracking component
     * <p>
     * The data used to anonymously track a shopper. See property descriptions for details on how this is anonymous tracking as opposed to tracking that infringes on the privacy of the shopper.
     * (Required)
     * 
     */
    protected ShopperTracking getShopper() {
        return shopper;
    }

    /**
     * shopper tracking component
     * <p>
     * The data used to anonymously track a shopper. See property descriptions for details on how this is anonymous tracking as opposed to tracking that infringes on the privacy of the shopper.
     * (Required)
     * 
     */
    protected void setShopper(ShopperTracking shopper) {
        this.shopper = shopper;
    }

    /**
     * Native app client component
     * <p>
     * Contains data about the client sending the beacon when the client is a native app.
     * (Required)
     * 
     */
    protected NativeAppClient getClient() {
        return client;
    }

    /**
     * Native app client component
     * <p>
     * Contains data about the client sending the beacon when the client is a native app.
     * (Required)
     * 
     */
    protected void setClient(NativeAppClient client) {
        this.client = client;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RemoveFromCartBeacon.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("event");
        sb.append('=');
        sb.append(((this.event == null)?"<null>":this.event));
        sb.append(',');
        sb.append("metadata");
        sb.append('=');
        sb.append(((this.metadata == null)?"<null>":this.metadata));
        sb.append(',');
        sb.append("experiments");
        sb.append('=');
        sb.append(((this.experiments == null)?"<null>":this.experiments));
        sb.append(',');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null)?"<null>":this.time));
        sb.append(',');
        sb.append("customer");
        sb.append('=');
        sb.append(((this.customer == null)?"<null>":this.customer));
        sb.append(',');
        sb.append("shopper");
        sb.append('=');
        sb.append(((this.shopper == null)?"<null>":this.shopper));
        sb.append(',');
        sb.append("client");
        sb.append('=');
        sb.append(((this.client == null)?"<null>":this.client));
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
        result = ((result* 31)+((this.metadata == null)? 0 :this.metadata.hashCode()));
        result = ((result* 31)+((this.shopper == null)? 0 :this.shopper.hashCode()));
        result = ((result* 31)+((this.experiments == null)? 0 :this.experiments.hashCode()));
        result = ((result* 31)+((this.client == null)? 0 :this.client.hashCode()));
        result = ((result* 31)+((this.time == null)? 0 :this.time.hashCode()));
        result = ((result* 31)+((this.event == null)? 0 :this.event.hashCode()));
        result = ((result* 31)+((this.customer == null)? 0 :this.customer.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RemoveFromCartBeacon) == false) {
            return false;
        }
        RemoveFromCartBeacon rhs = ((RemoveFromCartBeacon) other);
        return ((((((((this.metadata == rhs.metadata)||((this.metadata!= null)&&this.metadata.equals(rhs.metadata)))&&((this.shopper == rhs.shopper)||((this.shopper!= null)&&this.shopper.equals(rhs.shopper))))&&((this.experiments == rhs.experiments)||((this.experiments!= null)&&this.experiments.equals(rhs.experiments))))&&((this.client == rhs.client)||((this.client!= null)&&this.client.equals(rhs.client))))&&((this.time == rhs.time)||((this.time!= null)&&this.time.equals(rhs.time))))&&((this.event == rhs.event)||((this.event!= null)&&this.event.equals(rhs.event))))&&((this.customer == rhs.customer)||((this.customer!= null)&&this.customer.equals(rhs.customer))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(event);
        dest.writeList(metadata);
        dest.writeList(experiments);
        dest.writeValue(time);
        dest.writeValue(customer);
        dest.writeValue(shopper);
        dest.writeValue(client);
    }

    public int describeContents() {
        return  0;
    }

}
