package com.groupby.tracker.model;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManualSearchEvent implements Parcelable {
    protected ManualSearchEvent(android.os.Parcel in) {
        this.search = ((Search) in.readValue((Search.class.getClassLoader())));
        this.googleAttributionToken = ((String) in.readValue((String.class.getClassLoader())));
    }
    public ManualSearchEvent(){

    }
    @SerializedName("googleAttributionToken")
    @Expose
    private String googleAttributionToken;
    @SerializedName("search")
    @Expose
    private Search search;

    public void setGoogleAttributionToken(String googleAttributionToken) {
        this.googleAttributionToken = googleAttributionToken;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public static final Creator<ManualSearchEvent> CREATOR = new Creator<ManualSearchEvent>() {
        @Override
        public ManualSearchEvent createFromParcel(Parcel in) {
            return new ManualSearchEvent(in);
        }

        @Override
        public ManualSearchEvent[] newArray(int size) {
            return new ManualSearchEvent[size];
        }
    };

    public int describeContents() {
        return  0;
    }
    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(search);
        dest.writeValue(googleAttributionToken);
    }
}
