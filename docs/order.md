# order

For sending details of which products (or SKUs within products) the shopper is ordering.

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
price.setActual("12.34");
price.setCurrency("usd");
price.setOnSale(true);
price.setRegular("23.45");

// Prepare product for cart item
Product product = new Product();
product.setCategory("abc123");
product.setCollection("abc123");
product.setId("abc123");
product.setPrice(price);
product.setSku("abc123");
product.setTitle("abc123");

// Prepare cart item for list of cart items
CartItem item = new CartItem();
item.setProduct(product);
item.setQuantity(1);

// Prepare list of cart items for cart
List<CartItem> items = new ArrayList<>();
items.add(item);

// Prepare cart for event
Cart cart = new Cart();
cart.setItems(items);
cart.setType("abc123");

// Prepare event for beacon
OrderEvent event = new OrderEvent();
event.setGoogleAttributionToken("abc123");
event.setCart(cart);

// Prepare beacon for request
OrderBeacon beacon = new OrderBeacon();
beacon.setEvent(event);
beacon.setMetadata(null);
beacon.setExperiments(null);

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

In the real world, you should re-use your tracker instance across the lifetime of your app, not create a new instance each time you want to send a beacon. These code examples create new tracker instances each time for demonstration purposes.

## Properties

Price:

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| actual | The price the customer paid for the product as they ordered it. | `String` | Yes | n/a | 100 | ^[0-9]{1,9}\\.?[0-9]{1,2}$ |
| currency | The ISO 4217 code of the currency for the product. | `String` | Yes | 3 | 3 | [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217) format |
| onSale | Whether the product was on sale when the shopper ordered it. | `Boolean` | Yes | n/a | n/a | n/a |
| regular | The regular price of the product (when it is not on sale). Disallowed when property onSale is set to `false`. | `String` | When property onSale is set to `true`. | n/a | 100 | ^[0-9]{1,9}\\.?[0-9]{1,2}$ |

Product:

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| category | The category the product belongs to in your catalog's category hierarchy. | `String` | No | 1 | 100 | n/a |
| collection | The collection the product belongs to in GroupBy's systems after it has been uploaded to GroupBy. | `String` | No | 1 | 100 | n/a |
| id | The product's ID in your catalog stored in GroupBy's system. | `String` | Yes | 1 | 36 | n/a |
| price | Contains data about the price of the product, including whether it was on sale to the shopper when the event occurred. | `Price` | Yes | n/a | n/a | n/a |
| sku | The product's SKU in your catalog stored in GroupBy's system. | `String` | No | 1 | 73 | n/a |
| title | The product's title. This is used in GroupBy UIs that render information about the product. | `String` | Yes | 1 | 100 | n/a |

CartItem:

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| product | The product related to the cart item. | `Product` | Yes | n/a | n/a | n/a |
| quantity | The quantity of the product being ordered. | `Integer` | Yes | 1 | 2147483647 | n/a |

Cart:

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| items | The cart items being ordered. | `List<CartItem>` | Yes | 1 | 1000 | n/a |
| type | A value to label the cart with to differentiate it from other types of carts you might have. Ex. "gift registry". If provided, this will not affect search personalization or recommendations but will provide a new dimension to use in analytics dashboards. | `String` | No | 1 | 100 | n/a |

OrderEvent:

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| googleAttributionToken | The Google attribution token as described in Google Cloud Platform's [documentation for Cloud Retail Solutions](https://cloud.google.com/retail/docs/attribution-tokens). Instructions for implementing this are evolving over time. If you use GroupBy's Google-powered platform, reach out to your Customer Success rep to find out whether you need to implement this property and if so, how you should do it. | `String` | No | 1 | 100 | n/a |
| cart | The cart related to the event. | `Cart` | Yes | n/a | n/a | n/a |

OrderBeacon:

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| event | The event data for the beacon. | `OrderEvent` | Yes | n/a | n/a | n/a |
| experiments | The A/B testing experiments related to the event. | `List<Experiments>` | No | 1 | 20 | n/a |
| metadata | The metadata for the event. | `List<Metadata>` | No | 1 | 20 | n/a |

## Additional schemas

See [Experiments](experiments.md) for the schema of the experiments component.

See [Metadata](metadata.md) for the schema of the metadata component.
