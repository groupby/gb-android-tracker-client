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

### Validation

You can test your beacon implementation for validation errors using the `OnFailure` callback. Example, where validation errors returned in a 400 Bad Request response are logged:

```java
...
@Override
public void onFailure(GbException e, int statusCode) {
    String msg = "Failed to send beacon: " + e.getMessage();
    if (statusCode == 400  && e.getError() != null) {
        List<String> validationErrors = e.getError().getJsonSchemaValidationErrors();
        msg = msg + "; validation errors: " + validationErrors;
    }
    Log.e("TEST", msg, e);
}
...
```

You can see these logs in Android Studio while debugging your app:

![image](https://user-images.githubusercontent.com/7719209/188751932-023b0671-5947-4563-8332-ab2eccb2e8fe.png)

For more details, see [validation](docs/validation.md).

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

For lists of required and optional properties and examples for each event type, see:

* [autoSearch](docs/autoSearch.md)
* viewProduct - TBD
* addToCart - TBD
* removeFromCart - TBD
* order - TBD
* recImpression - TBD

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

## Shopper tracking

Shoppers are tracked anonymously. GroupBy will know when a shopper returns but will not know who the shopper is.

VisitorId is a UUID used to anonymously track the user. This ID is not tied to any external systems and can only be used to track activity within the same app install. VisitorId has an expiry time of 1 year since the last time the shopper visited. After that, a new ID will be generated.

This shared preferences is stored in a file named com.groupby.tracker, separated from other preferences used by the app.

## Internal GroupBy testing

By default, beacons will be send to the production environment. This can be overridden by specifying a URL to send the beacons in the tracker constructor. This is useful for sending beacons to a test environment or to GroupBy's development environment.

```java
// Optional, overrides the URL the beacon is sent to. Useful for testing.
GbTracker tracker = GbTracker.getInstance("customer_id", "area", "<some_url>");
```
