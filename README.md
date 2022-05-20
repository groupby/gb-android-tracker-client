# GroupBy Tracker Client for Android

This is the Android SDK used to send beacons to GroupBy.

## Install dependency from JitPack

Instructions for installing dependencies from JitPack differ based on what kind of Gradle project layout you have. These instructions are based on a new Android app created in April 2022 in the latest version of Android Studio at the time, with "basic activity" selected during the wizard.

1. Add `maven {url 'https://www.jitpack.io'}` the JitPack repository to the `settings.gradle` file under `repositories`, under `dependencyResolutionManagement`.

```
...
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {url 'https://www.jitpack.io'}
    }
}
...
```

2. Add the version of the dependency you want to use to the module `build.gradle` file (not the project `build.gradle` file). Versions usable for production start at `1.0.2`.

```
...
dependencies {

    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    implementation 'com.github.groupby:gb-android-tracker-client:1.0.2'
}
...
```

## Usage

### Importing classes

To import and use the tracker:

```java
import com.groupby.tracker.GbTracker;
```

All other classes used in example code below are imported from `com.groupby.tracker` package.

### Creating an instance and controlling logged in status

The user's login data can be set during the creation of the tracker instance or set when the user logs in after the tracker is already created.

This allows activities between multiple merchandiser applications and web to be attributed to the same user.

To create an instance of the tracker for a shopper who is logged in:

```java
GbTracker instance = GbTracker.getInstance("my-customer-id",
        "my-area",
        new Login(true, "shopper's-username"));
```

To create an instance of the tracker for a shopper who is not logged in:

```java
GbTracker instance = GbTracker.getInstance("my-customer-id",
        "my-area",
        new Login(false, null));
```

To change the shopper's status from "not logged in" to "logged in" at any point during the app's lifecycle after the instance has been created:

```java
instance.setLogin(new Login(true, "shopper's-username"));
```

To change the shopper's status from "logged in" to "not logged in" at any point during the app's lifecycle after the instance has been created:

```java
instance.setLogin(new Login(false, null));
```

### Sending events

To send an event, call the `sendXEvent` method where "X" is the type of event you want to send.

Example for viewProduct event:

```java
// Specifying all required properties for viewProduct beacon. Using "null" for optional
// properties, as required by the SDK.
ViewProductBeacon beacon = new ViewProductBeacon(new ViewProductEvent(new Product("abc123", null, "A product",
        null, null, new Price(false, null, "100.00", "USD")), null), null, null);

String APP_TAG = "ANDROID_BEACON_TEST";

// Logging and toasting on UI thread only for example purposes. Remove before deploying
// code to production if desired.
instance.sendViewProductEvent(beacon, new GbCallback() {
    @Override
    public void onFailure(GbException e, int statusCode) {
        String errMsg = "Error (response status = " + statusCode + ").";
        if (e.getError() != null) {
            // If an error was provided, it was a 400 response and it will have at least one validation error.
            List<String> validationErrors = e.getError().getJsonSchemaValidationErrors();
            errMsg = errMsg + " Validation errors: [" + validationErrors + "].";
        }
        String msg = "Failed to send beacon: " + errMsg;
        Log.e(APP_TAG, msg, e);
        runOnUiThread(() -> Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show());
    }

    @Override
    public void onSuccess() {
        String msg = "Sent beacon successfully!";
        Log.e(APP_TAG, msg);
        runOnUiThread(() -> Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show());
    }
});
```

### Reading validation error messages

If a property in the request body is invalid because invalid data was used while creating instances of the model classes, then the error returned in the `onFailure` callback will contain a list of validation error messages.

Example:

Request:

```java
// Invalid because if `isLoggedIn` is set to true, then the shopper username cannot be an empty string.
instance.setLogin(new Login(true, ""));
```

Errors in logs (see `onFailure` handler example above):

```
E/ANDROID_BEACON_TEST: Failed to send beacon: Error (response status = 400). Validation errors: [[shopper.login: Must validate one and only one schema (oneOf), shopper.login.username: String length must be greater than or equal to 1]].
    com.groupby.tracker.GbException: 
        at com.groupby.tracker.ApiClient.handleResponse(ApiClient.java:839)
        at com.groupby.tracker.ApiClient$1.onResponse(ApiClient.java:791)
        at okhttp3.internal.connection.RealCall$AsyncCall.run(RealCall.kt:519)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        at java.lang.Thread.run(Thread.java:923)
```

### Including metadata and experiments in events

To include metadata in an event, create a list of metadata items using the model classes and include them in the beacon:

```java
List<Metadata> metadata = new ArrayList<>();
metadata.add(new Metadata("example-key", "example-value"));
beacon.setMetadata(metadata);
```

To include experiments (for A/B testing) in an event, create a list of experiments using the model classes and include them in the beacon. Note that despite the model name "Experiments", each instance represents one experiment and multiple experiments can be added to the event, one for each A/B test being conducted:

```java
List<Experiments> experiments = new ArrayList<>();
experiments.add(new Experiments("example-id", "example-variant"));
experiments.add(new Experiments("example-id2", "example-variant2"));
beacon.setExperiments(experiments);
```

## Internal GroupBy testing

By default beacons will be send to the production environment. This can be overridden by specifying a URL to send the beacons in the tracker constructor. This is useful for sending beacons to a test environment or to GroupBy's development environment.

```java
// Optional, overrides the URL the beacon is sent to. Useful for testing.
GbTracker tracker = GbTracker.getInstance("customer_id", "area", <some_url>);
```

## Supported event types

The following event types are supported in the client:

* AddToCartBeacon
* ViewProductBeacon
* RemoveFromCartBeacon
* OrderBeacon
* RecImpressionBeacon
* AutoSearchBeacon (Used for search events when already integrated with GroupBy search engine)

## Examples for each event type

### autoSearch

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


## Shopper tracking

VisitorId is a UUID used to anonymously track the user. This id is not tied to any external systems and can only be used to track activity within the same app install. VisitorId has an expiry time of 1 year since the last time the shopper visited. After that, a new ID will be generated.

This shared preferences is stored in a file named com.groupby.tracker, separated from other preferences used by the app.

## More Usage Details

See the docs for more detailed information about implementing beacons:

https://docs.groupbycloud.com/gb-docs/gb-implementation/beacons/overview
