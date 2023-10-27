package com.groupby.tracker.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search implements Parcelable {

    protected Search(Parcel in) {
        this.query = in.readString();
        this.totalRecordCount = in.readLong();
        this.pageInfo = ((PageInfo) in.readValue((PageInfo.class.getClassLoader())));
        in.readList(this.records, (Record.class.getClassLoader()));
        in.readList(this.selectedNavigation, (SelectedNavigation.class.getClassLoader()));
    }

    public static final Creator<Search> CREATOR = new Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel in) {
            return new Search(in);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };

    public Search createFromParcel(android.os.Parcel in) {
        return new Search(in);
    }
    public Search(){

    }
    private String query;
    private long totalRecordCount;
    private PageInfo pageInfo;
    private List<Record> records;
    private List<SelectedNavigation> selectedNavigation;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<SelectedNavigation> getSelectedNavigation() {
        return selectedNavigation;
    }

    public void setSelectedNavigation(List<SelectedNavigation> selectedNavigation) {
        this.selectedNavigation = selectedNavigation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(query);
        dest.writeLong(totalRecordCount);
    }


}
