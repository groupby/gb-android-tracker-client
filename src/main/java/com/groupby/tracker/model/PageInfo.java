package com.groupby.tracker.model;

public class PageInfo {
    private long recordStart;
    private long recordEnd;

    public PageInfo() {
    }

    public PageInfo(long recordStart, long recordEnd) {
        this.recordStart = recordStart;
        this.recordEnd = recordEnd;
    }

    public long getRecordStart() {
        return recordStart;
    }

    public void setRecordStart(long recordStart) {
        this.recordStart = recordStart;
    }

    public long getRecordEnd() {
        return recordEnd;
    }

    public void setRecordEnd(long recordEnd) {
        this.recordEnd = recordEnd;
    }
}
