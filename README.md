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

## Usage 

To import and use the tracker:

```Groovy

//Step 1. Add the JitPack repository to your build file

//Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
    
//Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.tomshli:gb-tracker-client-for-android:0.1.31'
	}

```

To import and use the tracker:

```Java
import com.groupby.tracker.GbTracker;

// create the SDK instance
GbTracker tracker = GbTracker.getInstance("customer_id", "area");

// set the login data
tracker.setLogin(new Login(true, "shopper@example.com"));

// View Product Example
        
        
//Create Product        
Product product = new Product();
product.setCategory("Test Category");
product.setCollection("Test Collection");
product.setId("TestId");
product.setSku("TestSku");
Price price = new Price(true, "103.65", "100", "CAD");
product.setPrice(price);
product.setTitle("Test Title");

ViewProductEvent vpEvent = new ViewProductEvent();
vpEvent.setProduct(product);

ViewProductBeacon vpBeacon = new ViewProductBeacon();
vpBeacon.setEvent(vpEvent);
vpBeacon.setMetadata(new ArrayList<>());
vpBeacon.getMetadata().add(new Metadata("TestKey", "TestValue"));
vpBeacon.setExperiments(new ArrayList<>());
vpBeacon.getExperiments().add(new Experiments("TestId", "TestVariant"));

// send beacon
tracker.sendAddToCartEvent(vpBeacon, new GbCallback() {
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

By default beacons will be send to the production environment. This can be overridden by specifying a URL to send the beacons in the tracker constructor.
This is useful for sending beacons to a test environment or to Groupby's development environment.

```Java
GbTracker tracker = GbTracker.getInstance("customer_id", "area", <some_url>); // Optional, overrides the URL the beacon is sent to. Useful for testing.
```

## Supported Beacon Types
The following beacon types are supported in the client:
* AddToCartBeacon
* ViewProductBeacon
* RemoveFromCartBeacon
* OrderBeacon
* RecImpressionBeacon
* ManualSearchBeacon (Only to be used when integrating with GroupBy systems)
* AutoSearchBeacon (Used for search events when already integrated with GroupBy systems)

## Set Login Information

The user's login data can be set during the creation of the tracker instance or set when the user logs in after the tracker is already created.
This allows activities between multiple merchandiser applications and web to be attributed to the same user.

```Java
GbTracker tracker = GbTracker.getInstance("customer_id", "area", new Login(true, "shopper@example.com"));

// or

GbTracker tracker = GbTracker.getInstance("customer_id", "area")
...
tracker.setLogin(new Login(true, "shopper@example.com"));
```

setLogin() can also be used when the user logs out

```Java
tracker.setLogin(new Login(false, null));
```

## Shopper tracking

VisitorId is a UUID used to anonymously track the user. This id is not tied to any external systems and can only be used to track activity within the same app install.
VisitorId has an expiry time of 1 year since the last time the shopper visited. After that a new Id will be generated. 
This shared preferences is stored in a file named com.groupby.tracker, separated from other preferences used by the app.

## More Usage Details

See the docs for more detailed information about implementing beacons:

https://docs.groupbycloud.com/gb-docs/gb-implementation/beacons/overview
