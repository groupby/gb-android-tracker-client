# GroupBy Tracker Client for Android

This is the Android SDK used to send beacons to GroupBy.

## Installing

You can install the library into your Android app via [Jitpack](https://jitpack.io/).

1. Add the Jitpack Maven repository to your Gradle config by adding `maven {url 'https://www.jitpack.io'}` under `repositories` under `dependencyResolutionManagement`.
2. Add the desired version of the library under `dependencies`. For example, `implementation 'com.github.groupby:gb-android-tracker-client:1.0.3'`.
3. Perform a Gradle sync.

See [installing](docs/installing.md) for more details.

## Creating the tracker instance

You must program your app so that this happens when the app starts and a reference should be kept to the same tracker client object to re-use throughout the entire lifecycle of your app on the shopper's Android device.

Example:

```java
// login status for a shopper who is not logged in
Login login = new Login(false, null);
GbTracker instance = GbTracker.getInstance("my-customer-id", "my-area", login);
```

See [Creating the tracker instance](docs/creating_the_tracker_instance.md) for more details.

## Sending events

You must program your app so that the `sendXEvent` methods (where "X" is the name of the event to send) are called at the appropriate times throughout the lifecycle of your app on the shopper's Android device, reflecting shopping behavior. The single instance of the tracker (described in the previous step) should be re-used to do this.

An example of a method you can call to send an event is the `sendViewProductEvent` method.

See [Sending events](docs/sending_events.md) for more details.

## Setting login status

Login status describes whether the shopper is logged in or not when the event occurs. With this information set in the tracker GroupBy can anonymously track shoppers across their devices, not just anonymously track them in the Android app.

You can set the login status as you create the tracker instance and by mutating an existing tracker instance throughout the lifecycle of the app.

See [Setting login status](docs/setting_login_status.md) for more details.

## Validation

You can test your beacon implementation for validation errors using the `onFailure` callback. Example, where validation errors returned in a 400 Bad Request response are logged:

```java
@Override
public void onFailure(GbException e, int statusCode) {
    String msg = "Failed to send beacon: " + e.getMessage();
    if (statusCode == 400  && e.getError() != null) {
        List<String> validationErrors = e.getError().getJsonSchemaValidationErrors();
        msg = msg + "; validation errors: " + validationErrors;
    }
    Log.e("TEST", msg, e);
}
```

You can see these logs in Android Studio while debugging your app:

![image](https://user-images.githubusercontent.com/7719209/188751932-023b0671-5947-4563-8332-ab2eccb2e8fe.png)

See [Validation](docs/validation.md) for more details.

## Event types

The following event types are supported in the client. The "main four" event types are what GroupBy considers to be a minimum required beacon implementation in your Android app:

| Event type | In "main four"? | Description | Details |
| ---------- | --------------- | ----------- | ------- |
| autoSearch  | Yes | After performing a search using a GroupBy search API, this is used for sending details of the search to GroupBy's beacon API. The details are sent from the web browser using this event instead of being retrieved internally by GroupBy so that client tracking works correctly and aligns with the rest of the event types which must be sent from the client. | [autoSearch](docs/autoSearch.md)
| viewProduct  | Yes | For sending details of which product (or SKU within a product) the shopper is viewing details of. | [viewProduct](docs/viewProduct.md)
| addToCart | Yes | For sending details of which products (or SKUs within products) the shopper is adding to their cart. | [autoSearch](docs/addToCart.md)
| removeFromCart | No | For sending details of which products (or SKUs within products) the shopper is removing from their cart. | [removeFromCart](docs/removeFromCart.md)
| order | Yes | For sending details of which products (or SKUs within products) the shopper is ordering. | [order](docs/order.md)
| recImpression | No | For sending details of which products (or SKUs within products) the shopper is viewing on a page where you're rendering recommendations from a GroupBy recommendation API. | [recImpression](docs/recImpression.md)

When at least the main four event types have been implemented, session level insights become available instead of just event level insights. For example, you can get a breakdown via GroupBy's analytics of which search terms are leading your shoppers to the products they're buying.

## Including metadata and experiments in events

### Metadata

Metadata is miscellaneous key value pair data not part of each event's schema that you can include in each beacon you send.

When you include metadata in beacons you send, you extend GroupBy's analytics by enabling new dimensions.

See [Metadata](docs/metadata.md) for more details.

### Experiments

Experiments are key value pairs of data not part of each event's schema that you can in each beacon you send.

When you are running an A/B test, including details of the experiments in your A/B testing allows you to extend GroupBy's analytics by using your experiment as a a new dimension in analytics. For example, you can measure revenue for each bucket in your experiment.

See [Experiments](docs/experiments.md) for more details.

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
