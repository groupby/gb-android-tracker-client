# Metadata

Metadata is miscellaneous key value pair data not part of each event's schema that you can include in each beacon you send.

This data cannot improve the quality of GroupBy's search results or recommendations (because those services can only be powered by data that follows a schema they are designed for) but it provides extra dimensions that can be used in GroupBy's analytics.

## Example

To include metadata alongside an event in the beacon, create a list of metadata items using the model classes and include them in the beacon:

```java
List<Metadata> metadata = new ArrayList<>();
metadata.add(new Metadata("example-key1", "example-value1"));
metadata.add(new Metadata("example-key2", "example-value2"));
beacon.setMetadata(metadata);
```

## Properties

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| key | The key of the metadata pair. | `String` | Yes | 1 | 50 | n/a |
| value | The value of the metadata pair. | `String` | Yes | 1 | 1000 | n/a |

In every event type where metadata is included, the list of `Metadata` objects must be between 1 and 20 items.

## More help

Consult with your Technical Implementation Consultant at GroupBy for more guidance using this feature.
