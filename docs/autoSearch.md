# autoSearch

After performing a search using a GroupBy search API, this is used for sending details of the search to GroupBy's beacon API. The details are sent from the web browser using this event instead of being retrieved internally by GroupBy so that client tracking works correctly and aligns with the rest of the event types which must be sent from the client.

The search request could be a request to GroupBy's search API directly, or through a proxy.

Note that in this example, it is blocking because the example data is stored in memory, but in your app, it may be non-blocking.

## Example

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
event.setSearchId(UUID.fromString(results.searchId));
event.setOrigin(AutoSearchEvent.Origin.SEARCH);

// Prepare beacon for request
AutoSearchBeacon beacon = new AutoSearchBeacon();
beacon.setEvent(event);
beacon.setMetadata(null);
beacon.setExperiments(null);

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

In the real world, you should re-use your tracker instance across the lifetime of your app, not create a new instance each time you want to send a beacon. These code examples create new tracker instances each time for demonstration purposes.

## Properties

AutoSearchEvent:

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| searchId | The ID of the search performed with the GroupBy search engine API. This ID is returned in each HTTP response from the API and must be included in this event. | `UUID` | Yes | n/a | n/a | n/a |
| origin | The context in which the search was performed. Acceptable values are \"search\" (used when a search query is used with the API), \"sayt\" (used when GroupBy's SAYT search engine API is used instead of its regular search engine API, for search-as-you-type use cases), and \"navigation\" (used when no search query is used because the search engine is being used to power a PLP consisting of a category of products, often after a shopper has selected a facet). | `AutoSearchEvent.Origin` enum value | Yes | n/a | n/a | n/a |

AutoSearchBeacon:

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| event | The event data for the beacon. | `AutoSearchEvent` | Yes | n/a | n/a | n/a |
| experiments | The A/B testing experiments related to the event. | `List<Experiments>` | No | 1 | 20 | n/a |
| metadata | The metadata for the event. | `List<Metadata>` | No | 1 | 20 | n/a |

## Additional schemas

See [Experiments](experiments.md) for the schema of the experiments component.

See [Metadata](metadata.md) for the schema of the metadata component.
