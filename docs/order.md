# order

For sending details of which products (or SKUs within products) the shopper is ordering.

Example:

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

// Prepare price for product
Price price = new Price();
price.setActual("12.34"); // required, max length 100, must fit pattern ^[0-9]{1,9}\\.?[0-9]{1,2}$
price.setCurrency("usd"); // required, length 3, must be an ISO 4217 code
price.setOnSale(true); // required, must be true or false
price.setRegular("23.45"); // required if onSale is true, disallowed if onSale is false, max length 100, must fit pattern ^[0-9]{1,9}\\.?[0-9]{1,2}$

// Prepare product for cart item
Product product = new Product();
product.setCategory("abc123"); // optional, min length 1, max length 100
product.setCollection("abc123"); // optional, min length 1, max length 50, defaults to "default"
product.setId("abc123"); // required, min length 1, max length 36
product.setPrice(price); // required
product.setSku("abc123"); // optional, min length 1, max length 73
product.setTitle("abc123"); // required, min length 1, max length 100

// Prepare cart item for list of cart items
CartItem item = new CartItem();
item.setProduct(product); // required
item.setQuantity(1); // required, min 1, max 2147483647

// Prepare list of cart items for cart
List<CartItem> items = new ArrayList<>();
items.add(item);

// Prepare cart for event
Cart cart = new Cart();
cart.setItems(items); // required, min items 1, max items 1000
cart.setType("abc123"); // optional, min length 1, max length 100

// Prepare event for beacon
OrderEvent event = new OrderEvent();
event.setGoogleAttributionToken("abc123"); // optional, min length 1, max length 100
event.setCart(cart); // required

// Prepare beacon for request
OrderBeacon beacon = new OrderBeacon();
beacon.setEvent(event); // required
beacon.setMetadata(null); // optional
beacon.setExperiments(null); // optional

// Use tracker instance to send beacon
tracker.sendOrderEvent(beacon, new GbCallback() {
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

See [Metadata](metadata.md) for the schema of the metadata component.

See [Experiments](experiments.md) for the schema of the experiments component.

In the real world, you should re-use your tracker instance across the lifetime of your app, not create a new instance each time you want to send a beacon. These code examples create new tracker instances each time for demonstration purposes.