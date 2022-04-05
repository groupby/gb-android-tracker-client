# GroupBy Tracker Client for Android

This is the Android SDK used to send beacons to GroupBy.

## Usage from JitPack

To get the Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.tomshli:gb-tracker-client-for-android:0.1.31'
	}

## Usage with JitPack

To import and use the tracker:

```Java
import com.groupby.tracker.GbTracker;

// create the SDK instance
GbTracker tracker = GbTracker.getInstance("customer_id", "area");
# GroupBy Tracker Client for Android

This is the Android SDK used to send beacons to GroupBy.

## Usage from JitPack

To get the Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.tomshli:gb-tracker-client-for-android:0.1.31'
	}

## Usage with JitPack

To import and use the tracker:

```Java
import com.groupby.tracker.GbTracker;

// create the SDK instance
GbTracker tracker = GbTracker.getInstance("customer_id", "area");

// set the login data
tracker.setLogin(new Login(true, "shopper@example.com"));

// create beacon
AddToCartBeacon atcBeacon = new AddToCartBeacon(...);

// send beacon
tracker.sendAddToCartEvent(atcBeacon, new GbCallback() {
	@Override
	public void onSuccess() {
	    // runOnUiThread Optional: The send event methods run on separate threads so runOnUiThread can be used on the callback if the UI needs to be updated.
	    runOnUiThread(() -> {
		...
	    });
	}

	@Override
	public void onFailure(final GbException e, int statusCode) {
	    // runOnUiThread Optional: The send event methods run on separate threads so runOnUiThread can be used on the callback if the UI needs to be updated.
	    runOnUiThread(() -> {
		// If there are data validation errors, a list of string with the error details will be returned.
		// If there is a network or any other error, the statusCode will contain the HTTP status code returned.
		GbError error = e.getError();
		if (error != null && error.getJsonSchemaValidationErrors() != null && error.getJsonSchemaValidationErrors().size() > 0)
		{
		    ....
		}
		Log.e("error", e.toString());
	    });
	}
});
```

## Options

The constructor for the tracker client has an optional parameter for providing options:

```Java
GbTracker tracker = GbTracker.getInstance("customer_id", "area", <some_url>); // Optional, overrides the URL the beacon is sent to. Useful for testing.
```

## Set Login Information

The login data comes from the constructor call (allowing the SDK user to say “the shopper is logged in and this is their username” as they create the SDK instance) and also a method exist on the SDK instance that the user can call at any point during the app’s lifecycle to set new login data. This is to enable the use case of someone being not logged in at first, as they launch the app, but then logging it at some point while they’re using the app.

```Java
GbTracker tracker = GbTracker.getInstance("customer_id", "area", new Login(true, "shopper@example.com"));

// or

GbTracker tracker = GbTracker.getInstance("customer_id", "area")
...
tracker.setLogin(new Login(true, "shopper@example.com"));
```

## Shopper tracking

The shared preferences `uuid_value` is set with an expiry time of 1 year that is extended each time the shopper visits again. This is used to anonymously track the shopper. This shared preferences is stored in a file named com.groupby.tracker, separated from other preferences used by the app.

## More Usage Details

See the docs for more detailed information about implementing beacons:

https://docs.groupbycloud.com/gb-docs/gb-implementation/beacons/overview

// set the login data
tracker.setLogin(new Login(true, "shopper@example.com"));

// create beacon
AddToCartBeacon atcBeacon = new AddToCartBeacon(...);

// send beacon
tracker.sendAddToCartEvent(atcBeacon, new GbCallback() {
	@Override
	public void onSuccess() {
	    // runOnUiThread Optional: The send event methods run on separate threads so runOnUiThread can be used on the callback if the UI needs to be updated.
	    runOnUiThread(() -> {
		...
	    });
	}

	@Override
	public void onFailure(final GbException e, int statusCode) {
	    // runOnUiThread Optional: The send event methods run on separate threads so runOnUiThread can be used on the callback if the UI needs to be updated.
	    runOnUiThread(() -> {
		// If there are data validation errors, a list of string with the error details will be returned.
		// If there is a network or any other error, the statusCode will contain the HTTP status code returned.
		GbError error = e.getError();
		if (error != null && error.getJsonSchemaValidationErrors() != null && error.getJsonSchemaValidationErrors().size() > 0)
		{
		    ....
		}
		Log.e("error", e.toString());
	    });
	}
});
```

## Options

The constructor for the tracker client has an optional parameter for providing options:

```Java
GbTracker tracker = GbTracker.getInstance("customer_id", "area", <some_url>); // Optional, overrides the URL the beacon is sent to. Useful for testing.
```

## Set Login Information

The login data comes from the constructor call (allowing the SDK user to say “the shopper is logged in and this is their username” as they create the SDK instance) and also a method exist on the SDK instance that the user can call at any point during the app’s lifecycle to set new login data. This is to enable the use case of someone being not logged in at first, as they launch the app, but then logging it at some point while they’re using the app.

```Java
GbTracker tracker = GbTracker.getInstance("customer_id", "area", new Login(true, "shopper@example.com"));

// or

GbTracker tracker = GbTracker.getInstance("customer_id", "area")
...
tracker.setLogin(new Login(true, "shopper@example.com"));
```

## Shopper tracking

The shared preferences `uuid_value` is set with an expiry time of 1 year that is extended each time the shopper visits again. This is used to anonymously track the shopper. This shared preferences is stored in a file named com.groupby.tracker, separated from other preferences used by the app.

## More Usage Details

See the docs for more detailed information about implementing beacons:

https://docs.groupbycloud.com/gb-docs/gb-implementation/beacons/overview
