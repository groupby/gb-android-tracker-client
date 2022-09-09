# Sending events

You must program your app so that the `sendXEvent` methods (where "X" is the name of the event to send) are called at the appropriate times throughout the lifecycle of your app on the shopper's Android device, reflecting shopping behavior. The single instance of the tracker (described in the previous step) should be re-used to do this.

Example:

A method you can call to send an event is the `sendViewProductEvent` method. The shopper may be viewing a list of products in search results and they may tap on one of the products to see details of it. When this happens, your app might begin a new activity to show them the details. You should hook into the new activity's lifecycle methods to call the `sendViewProductEvent` of your tracker instance as that activity starts up. 

```java
// Based on starter code from a new Android Studio project
@Override
protected void onCreate(Bundle savedInstanceState) {
    // Beginning of code from starter code
    super.onCreate(savedInstanceState);

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    setSupportActionBar(binding.toolbar);

    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    // Ending of code from starter code

    // Added code for sending a beacon, assumes "tracker" is a reference to the
    // tracker singleton in your app
    tracker.sendViewProductEvent(/* ... */);
}
```

This example was for when the important event occurred when the activity started. That's why it uses the `onCreate` lifecycle hook method. Your situation and app design might be different. You may need to use different hooks or respond to a UI element being tapped, etc.

This example is only meant to describe when to send an event, so it doesn't fill in the viewProduct event details. For complete viewProduct event details, see [viewProduct](viewProduct.md).
