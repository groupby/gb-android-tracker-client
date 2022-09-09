# viewProduct

For sending details of which product (or SKU within a product) the shopper is viewing details of.

## Example

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

// Prepare product for event
Product product = new Product();
product.setCategory("abc123"); // optional, min length 1, max length 100
product.setCollection("abc123"); // optional, min length 1, max length 50, defaults to "default"
product.setId("abc123"); // required, min length 1, max length 36
product.setPrice(price); // required
product.setSku("abc123"); // optional, min length 1, max length 73
product.setTitle("abc123"); // required, min length 1, max length 100

// Prepare event for beacon
ViewProductEvent event = new ViewProductEvent();
event.setGoogleAttributionToken("abc123"); // optional, min length 1, max length 100
event.setProduct(product); // required

// Prepare beacon for request
ViewProductBeacon beacon = new ViewProductBeacon();
beacon.setEvent(event); // required
beacon.setMetadata(null); // optional
beacon.setExperiments(null); // optional

// Use tracker instance to send beacon
tracker.sendViewProductEvent(beacon, new GbCallback() {
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

In the real world, you should re-use your tracker instance across the lifetime of your app, not create a new instance each time you want to send a beacon. These code examples create new tracker instances each time for demonstration purposes.

## Properties

Price:

| Property | Description | Java Type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| actual | The price the customer would pay (if viewing) or paid (for order events) for the product. | String | Yes | n/a | 100 | ^[0-9]{1,9}\\.?[0-9]{1,2}$ |
| currency | The ISO 4217 code of the currency for the product. | String | Yes | 3 | 3 | [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217) format |
| onSale | Whether the product was on sale when the shopper viewed details of it. | Boolean | Yes | n/a | n/a | n/a |
| regular | The regular price of the product (when it is not on sale). Disallowed when property onSale is set to `false`. | Boolean | When property onSale is set to `true`. | n/a | 100 | ^[0-9]{1,9}\\.?[0-9]{1,2}$ |

Product:

| Property | Description | Java Type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| category | The category the product belongs to in your catalog's category hierarchy. | String | No | 1 | 100 | n/a |
| collection | The collection the product belongs to in GroupBy's systems after it has been uploaded to GroupBy. | String | No | 1 | 100 | n/a |
| id | The product's ID in your catalog stored in GroupBy's system. | String | Yes | 1 | 36 | n/a |
| price | Contains data about the price of the product, including whether it was on sale to the shopper when the event occurred. | com.groupby.tracker.model.Price | Yes | n/a | n/a | n/a |
| sku | The product's SKU in your catalog stored in GroupBy's system. | String | No | 1 | 73 | n/a |
| title | The product's title. This is used in GroupBy UIs that render information about the product. | String | Yes | 1 | 100 | n/a |


ViewProductEvent:

| Property | Description | Java Type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| googleAttributionToken | The Google attribution token as described in Google Cloud Platform's documentation for Cloud Retail Solutions (https://cloud.google.com/retail/docs/attribution-tokens). Instructions for implementing this are evolving over time. If you use GroupBy's Google-powered platform, reach out to your Customer Success rep to find out whether you need to implement this property and if so, how you should do it. | String | No | 1 | 100 | n/a |
| product | The product the shopper viewed details of. | com.groupby.tracker.model.Product | Yes | n/a | n/a | n/a |

## Additional schemas

See [Metadata](metadata.md) for the schema of the metadata component.

See [Experiments](experiments.md) for the schema of the experiments component.

