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

You'll know you've completed these steps properly if Android Studio has autocompletion help for the `GbTracker` class:

![image](https://user-images.githubusercontent.com/7719209/188748073-c11673ff-3187-4218-9403-d765ad8ccc93.png)

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

### Testing for validation errors

You can use the callback available for every beacon sending method to test whether the HTTP request sending the beacon had a 400 Bad Request response sent back with validation errors. In Android Studio, you can use the emulator and Logcat tab in the debugger to see these messages when they are logged with `Log.e`, `Log.i`, etc.

For example, you can run the following code which sends a valid autoSearch beacon:

```java
private void sendAutoSearchEvent() {
        // Create instance of tracker
        String customerId = "<customer-id>";
        String area = "<area>";
        // Represents a shopper who is not logged in
        Login login = new Login();
        login.setLoggedIn(false);
        login.setUsername(null);
        GbTracker tracker = GbTracker.getInstance(customerId, area, login);

        // Code below assumes a tracker has been created called "tracker"

        // Prepare event object
        AutoSearchEvent event = new AutoSearchEvent();
        event.setSearchId(UUID.fromString("167e44d4-2140-4098-91b0-1e1f0558fd8c"));
        event.setOrigin(AutoSearchEvent.Origin.SEARCH);

        // Prepare beacon object, including event object in it
        AutoSearchBeacon beacon = new AutoSearchBeacon();
        beacon.setEvent(event);
        beacon.setMetadata(null);
        beacon.setExperiments(null);

        // Use beacon object to send beacon with tracker
        tracker.sendAutoSearchEvent(beacon, new GbCallback() {
            @Override
            public void onFailure(GbException e, int statusCode) {
                String msg = "Failed to send beacon: " + e.getMessage();
                if (statusCode == 400  && e.getError() != null) {
                    List<String> validationErrors = e.getError().getJsonSchemaValidationErrors();
                    msg = msg + "; validation errors:" + validationErrors;
                }
                Log.e("TEST", msg, e);
            }

            @Override
            public void onSuccess() {
                String msg = "Sent beacon successfully.";
                Log.i("TEST", msg);
            }
        });
    }
```

This code can be wired up to a GUI element to send a test beacon like this:

```java
// Starting from code in starter Android Studio project
binding.fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        // Calling our new method to send the test beacon
        sendAutoSearchEvent();
        Snackbar.make(view, "Invoked code to send beacon.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
    }
});
```

You can click the GUI element in the emulator send the test beacon:

![image](https://user-images.githubusercontent.com/7719209/188751310-a25a8d5d-db57-42ae-adc4-0c68d2166035.png)

When you do this, based on the code above, a snackbar will be displayed:

![image](https://user-images.githubusercontent.com/7719209/188751336-f8612a36-d5cb-499f-81f6-fb9fed4d289d.png)

You can filter to the string "TEST" in the Logcat tab, because this value was used as the tag in the code above when logging:

![image](https://user-images.githubusercontent.com/7719209/188751550-40c5cfba-4a4b-4c6f-bf7b-29ababaf4405.png)

You will see the following logged in the Logcat tab in Android Studio:

![image](https://user-images.githubusercontent.com/7719209/188750610-9fbd0853-ba75-4a81-b1b3-1e138ddd7d5b.png)

You can also send an invalid beacon. For example, one where the login data is omitted. Properties in this SDK must be set to `null` explicitly. They cannot be omitted:

```java
// Represents a shopper who is not logged in
Login login = new Login();
// login.setLoggedIn(false);
// login.setUsername(null);
GbTracker tracker = GbTracker.getInstance(customerId, area, login);
```

When you send an invalid beacon, the error logging code above will result in you seeing this in the Logcat tab in Android Studio:

![image](https://user-images.githubusercontent.com/7719209/188751932-023b0671-5947-4563-8332-ab2eccb2e8fe.png)

Error code copied here in text form too:

```
2022-09-06 18:31:45.335 4180-4260/com.example.myapplication E/TEST: Failed to send beacon: Bad Request; validation errors:[shopper.login: Must validate one and only one schema (oneOf), shopper.login: loggedIn is required]
    com.groupby.tracker.GbException: Bad Request
        at com.groupby.tracker.ApiClient.handleResponse(ApiClient.java:839)
        at com.groupby.tracker.ApiClient$1.onResponse(ApiClient.java:791)
        at okhttp3.internal.connection.RealCall$AsyncCall.run(RealCall.kt:519)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        at java.lang.Thread.run(Thread.java:923)
```

In the real world, you should re-use your tracker instance across the lifetime of your app, not creating a new instance each time you want to send a beacon. These code examples create new tracker instances each time for demonstration purposes.

## Event types

The following event types are supported in the client. The "main four" event types are what GroupBy considers to be a minimum required beacon implementation in your Android app:

| Event type | Among "main four"? | Description |
| ------------- | ------------- | ------- |
| autoSearch  | Yes | For sending the search ID of a search you perform against a GroupBy search API to GroupBy's beacon system. |
| viewProduct  | Yes | For sending details of which product (or SKU within a product) the shopper is viewing details of. |
| addToCart | Yes | For sending details of which products (or SKUs within products) the shopper is adding to their cart. |
| removeFromCart | No | For sending details of which products (or SKUs within products) the shopper is removing from their cart. |
| order | Yes | For sending details of which products (or SKUs within products) the shopper is ordering. |
| recImpression | No | For sending details of which products (or SKUs within products) the shopper is viewing on a page where you're rendering recommendations from a GroupBy recommendation API. |

When at least the main four event types have been implemented, session level insights become available instead of just event level insights. For example, you can get a breakdown via GroupBy's analytics of which search terms are leading your shoppers to the products they're buying.

## Including metadata and experiments in events

### Metadata

To include metadata alongside an event in the beacon, create a list of metadata items using the model classes and include them in the beacon:

```java
List<Metadata> metadata = new ArrayList<>();
metadata.add(new Metadata("example-key", "example-value"));
beacon.setMetadata(metadata);
```

You will only need to add metadata for advanced use cases. It is not required. Consult with your Customer Success specialist for more into.

### Experiments

To include experiments (for A/B testing) in an event, create a list of experiments using the model classes and include them in the beacon. Note that despite the model name "Experiments", each instance represents one experiment and multiple experiments can be added to the event, one for each A/B test being conducted:

```java
List<Experiments> experiments = new ArrayList<>();
experiments.add(new Experiments("example-id", "example-variant"));
experiments.add(new Experiments("example-id2", "example-variant2"));
beacon.setExperiments(experiments);
```

You will only need to add experiments if you are running an A/B test where you want to measure the difference between beacons in one A/B test bucket vs. another in GroupBy's analytics. If you are not running an A/B test, the experiments property is not relevant to you and you don't need to implement it in your beacons.

## Examples for each event type

### autoSearch

Sends details of a search event 

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

### viewProduct

TBD

### addToCart

TBD

### removeFromCart

TBD

### order

TBD

### recImpression

TBD

## Shopper tracking

VisitorId is a UUID used to anonymously track the user. This ID is not tied to any external systems and can only be used to track activity within the same app install. VisitorId has an expiry time of 1 year since the last time the shopper visited. After that, a new ID will be generated.

This shared preferences is stored in a file named com.groupby.tracker, separated from other preferences used by the app.

## Internal GroupBy testing

By default, beacons will be send to the production environment. This can be overridden by specifying a URL to send the beacons in the tracker constructor. This is useful for sending beacons to a test environment or to GroupBy's development environment.

```java
// Optional, overrides the URL the beacon is sent to. Useful for testing.
GbTracker tracker = GbTracker.getInstance("customer_id", "area", "<some_url>");
```
