# Creating the tracker instance

The user's login data can be set during the creation of the tracker instance or set when the user logs in after the tracker is already created.

This allows activities between multiple merchandiser applications and web to be attributed to the same user.

To create an instance of the tracker for a shopper who is logged in:

```java
GbTracker instance = GbTracker.getInstance("my-customer-id",
        "my-area",
        new Login(true, "shopper's-username"));
```

To create an instance of the tracker for a shopper who is not logged in:

```java
GbTracker instance = GbTracker.getInstance("my-customer-id",
        "my-area",
        new Login(false, null));
```

To change the shopper's status from "not logged in" to "logged in" at any point during the app's lifecycle after the instance has been created:

```java
instance.setLogin(new Login(true, "shopper's-username"));
```

To change the shopper's status from "logged in" to "not logged in" at any point during the app's lifecycle after the instance has been created:

```java
instance.setLogin(new Login(false, null));
```
