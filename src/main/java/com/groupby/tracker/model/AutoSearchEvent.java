
package com.groupby.tracker.model;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * autoSearch event component
 * <p>
 * The event data for an autoSearch event. Note that this is the only event type that doesn't have a "googleAttributionToken" property. This is because the "searchId" acts as a token that allows GroupBy to tie this event together with the search that powered the shopper's experience leading up to the event, replacing the "googleAttributionToken" property.
 */
public class AutoSearchEvent implements Parcelable {

    /**
     * The ID of the search performed with the GroupBy search engine API. This ID is returned in each HTTP response from the API and must be included in this event.We're using the UUID format because the current implementations of GroupBy search engines all use v4 UUIDs as the search ID. We're using maxLength in addition to the UUID format because we think it might help applications perform validation more efficiently. It makes sense that it would be a declarative rule that would help validator implementations do a quick check on string length before checking for patterns.
     * (Required)
     */
    @SerializedName("searchId")
    @Expose
    private UUID searchId;
    /**
     * The context in which the search was performed. Acceptable values are "search" (used when a search query is used with the API), "sayt" (used when GroupBy's SAYT search engine API is used instead of its regular search engine API, for search-as-you-type use cases), and "navigation" (used when no search query is used because the search engine is being used to power a PLP consisting of a category of products, often after a shopper has selected a facet).The list of acceptable values in pre-v3 versions of the beacon API had more values. For example, it had values like "autoSearch" and "recommendation". We don't understand what this was for, so we're excluding it for now. A non-breaking change can be made to v3 of this API by adding more acceptable values in the future if we want to. TODO: $ref
     * (Required)
     */
    @SerializedName("origin")
    @Expose
    private AutoSearchEvent.Origin origin;
    public final static Creator<AutoSearchEvent> CREATOR = new Creator<AutoSearchEvent>() {

        public AutoSearchEvent createFromParcel(android.os.Parcel in) {
            return new AutoSearchEvent(in);
        }

        public AutoSearchEvent[] newArray(int size) {
            return (new AutoSearchEvent[size]);
        }

    };

    protected AutoSearchEvent(android.os.Parcel in) {
        this.searchId = ((UUID) in.readValue((UUID.class.getClassLoader())));
        this.origin = ((AutoSearchEvent.Origin) in.readValue((Origin.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public AutoSearchEvent() {
    }

    /**
     * @param searchId
     * @param origin
     */
    public AutoSearchEvent(UUID searchId, AutoSearchEvent.Origin origin) {
        super();
        this.searchId = searchId;
        this.origin = origin;
    }

    /**
     * The ID of the search performed with the GroupBy search engine API. This ID is returned in each HTTP response from the API and must be included in this event.We're using the UUID format because the current implementations of GroupBy search engines all use v4 UUIDs as the search ID. We're using maxLength in addition to the UUID format because we think it might help applications perform validation more efficiently. It makes sense that it would be a declarative rule that would help validator implementations do a quick check on string length before checking for patterns.
     * (Required)
     */
    public UUID getSearchId() {
        return searchId;
    }

    /**
     * The ID of the search performed with the GroupBy search engine API. This ID is returned in each HTTP response from the API and must be included in this event.We're using the UUID format because the current implementations of GroupBy search engines all use v4 UUIDs as the search ID. We're using maxLength in addition to the UUID format because we think it might help applications perform validation more efficiently. It makes sense that it would be a declarative rule that would help validator implementations do a quick check on string length before checking for patterns.
     * (Required)
     */
    public void setSearchId(UUID searchId) {
        this.searchId = searchId;
    }

    /**
     * The context in which the search was performed. Acceptable values are "search" (used when a search query is used with the API), "sayt" (used when GroupBy's SAYT search engine API is used instead of its regular search engine API, for search-as-you-type use cases), and "navigation" (used when no search query is used because the search engine is being used to power a PLP consisting of a category of products, often after a shopper has selected a facet).The list of acceptable values in pre-v3 versions of the beacon API had more values. For example, it had values like "autoSearch" and "recommendation". We don't understand what this was for, so we're excluding it for now. A non-breaking change can be made to v3 of this API by adding more acceptable values in the future if we want to. TODO: $ref
     * (Required)
     */
    public AutoSearchEvent.Origin getOrigin() {
        return origin;
    }

    /**
     * The context in which the search was performed. Acceptable values are "search" (used when a search query is used with the API), "sayt" (used when GroupBy's SAYT search engine API is used instead of its regular search engine API, for search-as-you-type use cases), and "navigation" (used when no search query is used because the search engine is being used to power a PLP consisting of a category of products, often after a shopper has selected a facet).The list of acceptable values in pre-v3 versions of the beacon API had more values. For example, it had values like "autoSearch" and "recommendation". We don't understand what this was for, so we're excluding it for now. A non-breaking change can be made to v3 of this API by adding more acceptable values in the future if we want to. TODO: $ref
     * (Required)
     */
    public void setOrigin(AutoSearchEvent.Origin origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AutoSearchEvent.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("searchId");
        sb.append('=');
        sb.append(((this.searchId == null) ? "<null>" : this.searchId));
        sb.append(',');
        sb.append("origin");
        sb.append('=');
        sb.append(((this.origin == null) ? "<null>" : this.origin));
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
        result = ((result * 31) + ((this.searchId == null) ? 0 : this.searchId.hashCode()));
        result = ((result * 31) + ((this.origin == null) ? 0 : this.origin.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AutoSearchEvent) == false) {
            return false;
        }
        AutoSearchEvent rhs = ((AutoSearchEvent) other);
        return (((this.searchId == rhs.searchId) || ((this.searchId != null) && this.searchId.equals(rhs.searchId))) && ((this.origin == rhs.origin) || ((this.origin != null) && this.origin.equals(rhs.origin))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(searchId);
        dest.writeValue(origin);
    }

    public int describeContents() {
        return 0;
    }


    /**
     * The context in which the search was performed. Acceptable values are "search" (used when a search query is used with the API), "sayt" (used when GroupBy's SAYT search engine API is used instead of its regular search engine API, for search-as-you-type use cases), and "navigation" (used when no search query is used because the search engine is being used to power a PLP consisting of a category of products, often after a shopper has selected a facet).The list of acceptable values in pre-v3 versions of the beacon API had more values. For example, it had values like "autoSearch" and "recommendation". We don't understand what this was for, so we're excluding it for now. A non-breaking change can be made to v3 of this API by adding more acceptable values in the future if we want to. TODO: $ref
     */
    public enum Origin {

        @SerializedName("search")
        SEARCH("search"),
        @SerializedName("sayt")
        SAYT("sayt"),
        @SerializedName("navigation")
        NAVIGATION("navigation"),
        @SerializedName("conversation")
        CONVERSATION("conversation");
        private final String value;
        private final static Map<String, AutoSearchEvent.Origin> CONSTANTS = new HashMap<String, AutoSearchEvent.Origin>();

        static {
            for (AutoSearchEvent.Origin c : values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Origin(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static AutoSearchEvent.Origin fromValue(String value) {
            AutoSearchEvent.Origin constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
