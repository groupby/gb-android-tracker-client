# autoSearch

Sends details of a search event.

The autoSearch event is meant to be used after you've performed a request to GroupBy's search API, so for this example, here is a method that returns mock search results. In your app, this would be whichever code calls the GroupBy search API. Note that in this example, it is blocking, but in your app, it may be non-blocking:

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

Sending the event in a beacon:

```java
// Tag for logging, if needed.
String APP_TAG = "test_tag";

// 1) Perform search request.
ExampleSearchResults results = exampleSearchRequest();

// 2a) Use search result data to render search results to app user.
// Can be done concurrently with step 2b.
// Not shown in this example.

// 2b) Use search result data to send an autoSearch beacon.
// This can be done concurrently with step 2a.
AutoSearchBeacon beacon = new AutoSearchBeacon(new AutoSearchEvent(
        UUID.fromString(results.searchId),
        AutoSearchEvent.Origin.SEARCH),
        null, // no metadata in this example
        null); // no experiments in this example

instance.sendAutoSearchEvent(beacon, new GbCallback() {
    @Override
    public void onFailure(GbException e, int statusCode) {
        String msg = "Failed to send beacon: " + e.getMessage();
        Log.e(APP_TAG, msg, e);
        runOnUiThread(() -> Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        String msg = "Sent beacon successfully!";
        Log.e(APP_TAG, msg);
        runOnUiThread(() -> Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show());
    }
});
```