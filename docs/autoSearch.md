# autoSearch

After performing a search using a GroupBy search API, this is used for sending details of the search to GroupBy's beacon API. The details are sent from the web browser using this event instead of being retrieved internally by GroupBy so that client tracking works correctly and aligns with the rest of the event types which must be sent from the client.

The search request could be a request to GroupBy's search API directly, or through a proxy.

Note that in this example, it is blocking because the example data is stored in memory, but in your app, it may be non-blocking.

Example data, retrieved by calling a method:

```java
private static class ExampleSearchResults {
    List<String> records;
    String searchId;

    ExampleSearchResults(List<String> records, String searchId) {
        this.records = records;
        this.searchId = searchId;
    }
}
```

```java
/**
 * Example of performing an HTTP request to GroupBy's search API. In the real world, the data
 * returned would include whichever records matched the search query and a UUID v4 as the search
 * ID, which is meant to be included in autoSearch beacons sent related to the request.
 *
 * @return The search results.
 */
private ExampleSearchResults exampleSearchRequest() {
    return new ExampleSearchResults(
            Arrays.asList("record 1", "record 2"),
            "c8a16b67-d3dd-49a8-b49c-68ed18febc3f"
    );
}
```

Sending the beacon:

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
AutoSearchEvent event = new AutoSearchEvent();
event.setSearchId(UUID.fromString(results.searchId)); // required, string in UUID min length 1
event.setOrigin(AutoSearchEvent.Origin.SEARCH); // required, must be one of AutoSearchEvent.Origin enum values

// Prepare beacon for request
AutoSearchBeacon beacon = new AutoSearchBeacon();
beacon.setEvent(event);
beacon.setMetadata(null); // optional
beacon.setExperiments(null); // optional

// Use tracker instance to send beacon
tracker.sendAutoSearchEvent(beacon, new GbCallback() {
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

See [Metadata](metadata.md) for the schema of the metadata component.

See [Experiments](experiments.md) for the schema of the experiments component.

In the real world, you should re-use your tracker instance across the lifetime of your app, not create a new instance each time you want to send a beacon. These code examples create new tracker instances each time for demonstration purposes.
