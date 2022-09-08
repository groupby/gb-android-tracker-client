# Validation

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

In the real world, you should re-use your tracker instance across the lifetime of your app, not create a new instance each time you want to send a beacon. These code examples create new tracker instances each time for demonstration purposes.
