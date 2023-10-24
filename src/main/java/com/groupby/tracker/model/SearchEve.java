package com.groupby.tracker.model;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchEve implements Parcelable {
    protected SearchEve(android.os.Parcel in) {
        this.search = ((Search) in.readValue((Search.class.getClassLoader())));
        this.googleAttributionToken = ((String) in.readValue((String.class.getClassLoader())));
    }
    public SearchEve(){

    }
    @SerializedName("googleAttributionToken")
    @Expose
    private String googleAttributionToken;
    @SerializedName("search")
    @Expose
    private Search search;

    public void setgoogleAttributionToken(String googleAttributionToken) {
        this.googleAttributionToken = googleAttributionToken;
    }

    public void SetSearch(Search search) {
        this.search = search;
    }

    public static final Creator<SearchEve> CREATOR = new Creator<SearchEve>() {
        @Override
        public SearchEve createFromParcel(Parcel in) {
            return new SearchEve(in);
        }

        @Override
        public SearchEve[] newArray(int size) {
            return new SearchEve[size];
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
