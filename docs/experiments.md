# Experiments

Experiments are key value pairs of data not part of each event's schema that you can include each beacon you send.

When you are running an A/B test, including details of the experiments in your A/B testing allows you to extend GroupBy's analytics by using your experiment as a a new dimension in analytics. For example, you can measure revenue for each bucket in your experiment.

## Example

To include experiments (for A/B testing) in an event, create a list of experiments using the model classes and include them in the beacon. Note that despite the model name "Experiments", each instance represents one experiment and multiple experiments can be added to the event, one for each A/B test being conducted:

```java
List<Experiments> experiments = new ArrayList<>();
experiments.add(new Experiments("your-experiment-id", "variant1"));
// or variant2, depending on which bucket you assigned the shopper to
beacon.setExperiments(experiments);
```

If you're running more than one experiment at a time, you'd include a key value pair for each experiment relevant to the beacon you're sending:

```java
List<Experiments> experiments = new ArrayList<>();
experiments.add(new Experiments("your-first-experiment-id", "variant1"));
experiments.add(new Experiments("your-second-experiment-id", "variant1"));
beacon.setExperiments(experiments);
```

## Properties

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| experimentId | The ID of the experiment. | `String` | Yes | 1 | 50 | n/a |
| experimentVariant | The variant of the experiment, aka bucket. | `String` | Yes | 1 | 50 | n/a |

There should be a 1 to many relationship between ID and variant, though it is technically valid to run an A/B testing experiment where there is only one variant for an experiment.

In every event type where experiments are included, the list of `Experiments` objects must be between 1 and 20 items.

## More help

Consult with your Technical Implementation Consultant at GroupBy for more guidance using this feature.