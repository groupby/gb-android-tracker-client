package com.groupby.tracker;

import android.content.Context;
import android.util.Log;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.groupby.tracker.model.AddToCartEvent;
import com.groupby.tracker.model.AutoSearchEvent;
import com.groupby.tracker.model.Cart;
import com.groupby.tracker.model.CartItem;
import com.groupby.tracker.model.Experiments;
import com.groupby.tracker.model.Login;
import com.groupby.tracker.model.ManualSearchEvent;
import com.groupby.tracker.model.Metadata;
import com.groupby.tracker.model.OrderEvent;
import com.groupby.tracker.model.PageInfo;
import com.groupby.tracker.model.Price;
import com.groupby.tracker.model.Product;
import com.groupby.tracker.model.RecImpressionEvent;
import com.groupby.tracker.model.Record;
import com.groupby.tracker.model.RemoveFromCartEvent;
import com.groupby.tracker.model.Search;
import com.groupby.tracker.model.SelectedNavigation;
import com.groupby.tracker.model.ViewProductEvent;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.groupby.tracker.test", appContext.getPackageName());
    }

    @Test
    public void testAutoSearchBeacon() {
        //Prepare
        AutoSearchEvent event = new AutoSearchEvent(UUID.randomUUID(), AutoSearchEvent.Origin.SEARCH);
        AutoSearchBeacon beacon = new AutoSearchBeacon(event, metadata(), experiments());

        //Act and verify
        TestGBCallback callback = new TestGBCallback("Send autoSearch beacon");
        initTracker().sendAutoSearchEvent(beacon, callback);
        callback.waitFor(3000);
    }

    @Test
    public void testManualSearchBeacon() {
        //Prepare
        Record record1 = new Record("id1", "title1");
        Record record2 = new Record("id2", "title2");

        PageInfo pageInfo = new PageInfo(1, 2);
        SelectedNavigation navi1 = new SelectedNavigation("refined 1", "val1");
        SelectedNavigation navi2 = new SelectedNavigation("refined 2", "val2");

        Search search = new Search(
                "query",
                2,
                pageInfo,
                Arrays.asList(record1, record2),
                Arrays.asList(navi1, navi2)
        );

        ManualSearchEvent event = new ManualSearchEvent(search);
        ManualSearchBeacon beacon = new ManualSearchBeacon(event, metadata(), experiments());

        //Act and verify
        TestGBCallback callback = new TestGBCallback("Send manualSearch beacon");
        initTracker().sendManualSearchEvent(beacon, callback);
        callback.waitFor(3000);
    }

    @Test
    public void testSendViewProductBeacon() {
        //Prepare
        Price price = price(35.99);
        ViewProductEvent event = new ViewProductEvent(product("Kettle", price));
        ViewProductBeacon beacon = new ViewProductBeacon(event, metadata(), experiments());

        //Act and verify
        TestGBCallback callback = new TestGBCallback("Send viewProduct beacon");
        initTracker().sendViewProductEvent(beacon, callback);
        callback.waitFor(3000);
    }

    @Test
    public void testAddToCartBeacon() {
        //Prepare
        Price price = price(3.50);
        Product product = product("Nessy", price);
        CartItem cartItem = cartItem(product, 1);
        Cart cart = cart(cartItem);

        AddToCartEvent event = new AddToCartEvent(cart);
        AddToCartBeacon beacon = new AddToCartBeacon(event, metadata(), experiments());

        //Act and verify
        TestGBCallback callback = new TestGBCallback("Send addToCart beacon");
        initTracker().sendAddToCartEvent(beacon, callback);
        callback.waitFor(3000);
    }

    @Test
    public void testRemoveFromCartBeacon() {
        //Prepare
        Price price = price(9.99);
        Product product = product("Winter tire for summer", price);
        CartItem cartItem = cartItem(product, 4);
        Cart cart = cart(cartItem);

        RemoveFromCartEvent event = new RemoveFromCartEvent(cart);
        RemoveFromCartBeacon beacon = new RemoveFromCartBeacon(event, metadata(), experiments());

        //Act and verify
        TestGBCallback callback = new TestGBCallback("Send removeFromCart beacon");
        initTracker().sendRemoveFromCartEvent(beacon, callback);
        callback.waitFor(3000);
    }

    @Test
    public void testOrderBeacon() {
        //Prepare
        Price price = price(99.99);
        Product product = product("The sword of thousand truths", price);
        CartItem cartItem = cartItem(product, 1);
        Cart cart = cart(cartItem);

        OrderEvent event = new OrderEvent(cart);
        OrderBeacon beacon = new OrderBeacon(event, metadata(), experiments());

        //Act and verify
        TestGBCallback callback = new TestGBCallback("Send order beacon");
        initTracker().sendOrderEvent(beacon, callback);
        callback.waitFor(3000);
    }

    @Test
    public void testRecImpressionBeacon() {
        //Prepare
        Price price = price(4.99);
        Product product1 = product("Shampoo", price);
        Product product2 = product("Dog food", price);
        RecImpressionEvent event = new RecImpressionEvent(Arrays.asList(product1, product2));

        RecImpressionBeacon beacon = new RecImpressionBeacon(event, metadata(), experiments());

        //Act and verify
        TestGBCallback callback = new TestGBCallback("Send recImpression beacon");
        initTracker().sendRecImpressionEvent(beacon, callback);
        callback.waitFor(3000);
    }

    private List<Metadata> metadata() {
        return Stream.of(
                new Metadata("weather", "Calgary is no more"),
                new Metadata("shopping_list", "Sausages")
        ).collect(Collectors.toList());
    }

    private List<Experiments> experiments() {
        return Stream.of(
                new Experiments("cat_feeding", "disabled"),
                new Experiments("cat_patting", "enabled"),
                new Experiments("funeral_preparation", "planned")
        ).collect(Collectors.toList());
    }

    private Product product(String title, Price price) {
        Product product = new Product();
        product.setId(title + "_111111");
        product.setSku(title + "_111111_1");
        product.setCategory(title + " and stuff");
        product.setCollection(title + "and fancy stuff");
        product.setPrice(price);
        product.setTitle(title);
        return product;
    }

    private CartItem cartItem(Product product, int quantity) {
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        return item;
    }

    private Cart cart(CartItem... cartItems) {
        Cart cart = new Cart();
        cart.setItems(Arrays.asList(cartItems));
        cart.setType("abc123");
        return cart;
    }

    private Price price(double priceVal) {
        if (priceVal <= 0) {
            //nice try, now pay
            priceVal = 99.99;
        }

        BigDecimal actual = new BigDecimal(priceVal);
        BigDecimal onSale = actual.subtract(BigDecimal.valueOf(0.01));

        Price price = new Price();
        price.setRegular(actual.setScale(2, RoundingMode.CEILING).toString());
        price.setCurrency("USD");

        if (onSale.doubleValue() <= 0) {
            price.setOnSale(false);
            price.setActual(actual.setScale(2, RoundingMode.CEILING).toString());
        } else {
            price.setOnSale(true);
            price.setActual(onSale.setScale(2, RoundingMode.CEILING).toString());
        }
        return price;
    }

    private GbTracker initTracker() {
        GbTracker tracker = GbTracker.getInstance(
                getArgRequired("CUSTOMER_ID"),
                getArgRequired("CUSTOMER_AREA"),
                new Login(false, null),
                getArg("URL_OVERRIDE")
        );

        Log.i("TEST", String.format("Initialized tracker, customer [%s], area [%s], url override: [%s]",
                tracker.getCustomerId(),
                tracker.getArea(),
                tracker.getUrlPrefixOverride()));

        return tracker;
    }

    private String getArgRequired(String argName) {
        String argVal = getArg(argName);
        if (argVal == null) throw new IllegalStateException(String.format("No [%s] argument is defined", argVal));
        return argVal;
    }

    private String getArg(String argName) {
        Object argVal = InstrumentationRegistry.getArguments().get(argName);
        if (argVal == null) return null;
        if (argVal instanceof String) {
            return (String) argVal;
        }
        throw new IllegalArgumentException(String.format("Argument [%s] is not a string", argName));
    }

    private static class TestGBCallback implements GbCallback {
        private final CountDownLatch latch;
        private final String caseName;

        public TestGBCallback(String caseName) {
            this.latch = new CountDownLatch(1);
            this.caseName = caseName;
        }

        @Override
        public void onFailure(GbException e, int statusCode) {
            String msg = String.format("Test case [%s] failed with error: %s", caseName, e.getMessage());
            if (statusCode == 400 && e.getError() != null) {
                msg += "; validation errors: " + e.getError().getJsonSchemaValidationErrors();
            }
            fail(msg);
        }

        @Override
        public void onSuccess() {
            latch.countDown();
            Log.i("TEST", String.format("Expectation [%s] passed", caseName));
        }

        public void waitFor(long millis) {
            try {
                boolean success = latch.await(millis, TimeUnit.MILLISECONDS);
                if (!success) {
                    fail(String.format("Expectation [%s] failed with timeout [%s ms]", caseName, millis));
                }
            } catch (InterruptedException e) {
                fail(String.format("Expectation [%s] failed with error %s", caseName, e.getMessage()));
            }
        }
    }

}
