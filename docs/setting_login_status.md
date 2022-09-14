# Setting login status

Login status describes whether the shopper is logged in or not when the event occurs.

GroupBy anonymously tracks shoppers. It doesn't need the shopper to be logged in to track them. The Android library sets a unique ID for the device (called visitor ID) which stays with the app install for up to 1 year after the last activity. But, if the shopper is logged in, and the library is configured correctly, GroupBy also receives a second unique ID. This ID is for the shopper, not the device. This allows GroupBy to track the shopper across their devices if the GroupBy customer has configured multiple tracker SDKs (ex. web, Android, iOS) correctly.

GroupBy acknowledges that the shopper's username is PII and takes steps to anonymize it before processing and storing it. You can use the plain text username (ex. "shopper@example.com") or you can hash it first. If you do hash it, ensure you also hash the shopper's username everywhere else you use it in GroupBy's APIs to ensure features like personalization and analytics tracking work correctly. Whether you hash it or not, GroupBy will hash it as soon as possible in our servers.

You can set login status as you create the tracker instance. See [creating the tracker instance](docs/creating_the_tracker_instance.md) for details on doing it that way. But you can also set a new login status as the app runs by mutating the existing tracker instance you created earlier in the app's lifecycle.

To change the shopper's status from "not logged in" to "logged in" at any point during the app's lifecycle after the instance has been created:

```java
tracker.setLogin(new Login(true, "shopper's-username"));
```

To change the shopper's status from "logged in" to "not logged in" at any point during the app's lifecycle after the instance has been created:

```java
tracker.setLogin(new Login(false, null));
```

## Properties

| Property | Description | Java type | Required? | Min | Max | String format |
| -------- | ----------- | --------- | --------- | --- | --- | ------------- |
| loggedIn | Whether the shopper is logged in. | `Boolean` | Yes | n/a | n/a | n/a |
| username | The shopper's username. Disallowed if property "loggedIn" is set to false. | `String` | If property "loggedIn" is set to true. | 1 | 50 | n/a |
