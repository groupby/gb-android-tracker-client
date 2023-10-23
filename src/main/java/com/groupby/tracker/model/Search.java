package com.groupby.tracker.model;
import java.util.List;

public class Search {
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

    public static class PageInfo {
        private long recordStart;
        private long recordEnd;

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

    public static class Record {
        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class SelectedNavigation {
        // Define the structure of SelectedNavigation here if available in your schema.
        // You can create a similar class as above to represent its structure.
    }
}
