# manualSearch

Event type is used in case of:
- Sends details of the search in case other than GroupBy search API is used.
- For performing an A/B test between client's existing search engine and GroupBy Search.

## Example

```java
// Create instance of tracker
String customerId = "<your-customer-id>";
String area = "<your-area>";
// Represents a shopper who is not logged in
Login login = new Login();
login.setLoggedIn(false);
login.setUsername(null);
GbTracker tracker = GbTracker.getInstance(customerId, area, login);

// Code below assumes a tracker has been created called "tracker"

// Perform search request
ExampleSearchResults results = exampleSearchRequest();

// Prepare event for beacon
Record record1 = new Record();
record1.setId("id1");
record1.setTitle("title1");
Record record2 = new Record();
record2.setId("id2");
record2.setTitle("title2");

PageInfo pageInfo = new PageInfo();
pageInfo.setRecordStart(1);
pageInfo.setRecordEnd(2);

SelectedNavigation navi = new SelectedNavigation();
navi.setName("refined 1");
navi.setValue("val1");

Search search = new Search();
search.setQuery("query");
search.setTotalRecordCount(2);
search.setRecords(List.of(record1, record2));
search.setPageInfo(pageInfo);
search.setSelectedNavigation(List.of(navi));

ManualSearchEvent event = new ManualSearchEvent();
event.setSearch(search);
event.setGoogleAttributionToken("token_value");

// Prepare beacon for request
ManualSearchBeacon beacon = new ManualSearchBeacon();
beacon.setEvent(event);
beacon.setMetadata(null);
beacon.setExperiments(null);

// Use tracker instance to send beacon
tracker.sendManualSearchEvent(beacon, new GbCallback() {
    @Override
    public void onFailure(GbException e, int statusCode) {
        String msg = "Failed to send beacon: " + e.getMessage();
        if (statusCode == 400  && e.getError() != null) {
            List<String> validationErrors = e.getError().getJsonSchemaValidationErrors();
            msg = msg + "; validation errors: " + validationErrors;
        }
        Log.e("TEST", msg, e);
    }

    @Override
    public void onSuccess() {
        String msg = "Sent beacon successfully.";
        Log.i("TEST", msg);
    }
});
```

In the real world, you should re-use your tracker instance across the lifetime of your app, not create a new instance each time you want to send a beacon. These code examples create new tracker instances each time for demonstration purposes.

## Properties

Record:

| Property | Description       | Java type | Required? | Min | Max | String format |
|----------|-------------------|-----------|-----------| --- |-----| ------------- |
| id       | The product ID    | `String`  | Yes       | 1 | n/a | n/a |
| title    | The product title | `String`  | No        | n/a | n/a | n/a |

PageInfo:

| Property    | Description                                        | Java type | Required? | Min | Max | String format |
|-------------|----------------------------------------------------|-----------|-----------|-----|-----| ------------- |
| recordStart | The first record in the search results (1-indexed) | `Long`    | Yes       | n/a | n/a | n/a |
| recordEnd   | The last record in the search results (1-indexed)  | `Long`    | Yes        | n/a | n/a | n/a |

Selected navigation:

| Property | Description                                                       | Java type | Required? | Min | Max | String format |
|----------|-------------------------------------------------------------------|-----------|-----------|-----|-----| ------------- |
| name     | The navigation field address                                      | `String`  | Yes       | n/a | n/a | n/a |
| value    | The value that was applied as a filter for the Search navigation  | `String`  | Yes        | n/a | n/a | n/a |


Search:

| Property           | Description                                                                                                                                                                                                                  | Java type                  | Required? | Min | Max | String format |
|--------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------|-----------| --- | --- | ------------- |
| query              | The search query or term for the event. Use an empty string if it was a browse event that had no search query or term.                                                                                                       | `String`                   | Yes       | n/a | n/a | n/a |
| totalRecordCount   | The total number of products in the search results                                                                                                                                                                           | `Long`                     | Yes       | n/a | n/a | n/a |
| records            | The results in a record set. Every item in the records property is an object with one property.                                                                                                                              | `List<Record>`             | Yes       | n/a | n/a | n/a |
| pageInfo           | The index number of the first and last record (1-indexed) within the response. If the page displays more records per page than were in the search results, use the number of records in the search results for this property | `PageInfo`                 | Yes       | n/a | n/a | n/a |
| selectedNavigation | The values that were refined. Required if applicable to the values of what refinements were selected.                                                                                                                        | `List<SelectedNavigation>` | No        | n/a | n/a | n/a |

ManualSearchEvent:

| Property               | Description | Java type | Required? | Min | Max | String format |
|------------------------| ----------- |-----------| --------- |-----|-----| ------------- |
| googleAttributionToken | The Google attribution token as described in Google Cloud Platform's [documentation for Cloud Retail Solutions](https://cloud.google.com/retail/docs/attribution-tokens). Instructions for implementing this are evolving over time. If you use GroupBy's Google-powered platform, reach out to your Customer Success rep to find out whether you need to implement this property and if so, how you should do it. | `String`  | No | n/a | 255 | n/a |
| search                 | The cart related to the event. | `Search`  | Yes | n/a | n/a | n/a |

ManualSearchBeacon:

| Property | Description | Java type          | Required? | Min | Max | String format |
| -------- | ----------- |--------------------| --------- |-----| --- | ------------- |
| event | The event data for the beacon. | `ManualSearchEvent` | Yes | n/a | n/a | n/a |
| experiments | The A/B testing experiments related to the event | `List<Experiments>` | No | 1   | 20 | n/a |
| metadata | The metadata for the event. | `List<Metadata>`   | No | n/a | 20 | n/a |

## Additional schemas

See [Experiments](experiments.md) for the schema of the experiments component.

See [Metadata](metadata.md) for the schema of the metadata component.
