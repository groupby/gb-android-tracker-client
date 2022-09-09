# Metadata

Metadata is miscellaneous key value pair data not part of each event's schema that you can include in each beacon you send.

This data cannot improve the quality of GroupBy's search results or recommendations (because those services can only be powered by data that follows a schema they are designed for) but it provides extra dimensions that can be used in GroupBy's analytics.

To include metadata alongside an event in the beacon, create a list of metadata items using the model classes and include them in the beacon:

```java
List<Metadata> metadata = new ArrayList<>();
metadata.add(new Metadata("example-key1", "example-value1"));
metadata.add(new Metadata("example-key2", "example-value2"));
beacon.setMetadata(metadata);
```

## Schema

```java
List<Metadata> l = new ArrayList<>(); // min num items 1, max num items 20
Metadata m = new Metadata(); 
m.setKey("abc"); // required, min length 1, max length 50
m.setValue("def"); // required, min length 1, max length 1000
```

Consult with your Technical Implementation Consultant at GroupBy for more guidance using this feature.
