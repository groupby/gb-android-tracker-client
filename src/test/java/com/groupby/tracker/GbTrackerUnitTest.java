package com.groupby.tracker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;

import com.groupby.tracker.model.AddToCartEvent;
import com.groupby.tracker.model.Cart;
import com.groupby.tracker.model.CartItem;
import com.groupby.tracker.model.Experiments;
import com.groupby.tracker.model.Login;
import com.groupby.tracker.model.Metadata;
import com.groupby.tracker.model.Price;
import com.groupby.tracker.model.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class GbTrackerUnitTest {

    @Mock
    private Context mockContext;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

    @Test
    public void sendAddToCartEvent_valid()
    {
        try (MockedStatic<WebSettings> webSettings = Mockito.mockStatic(WebSettings.class)) {
            webSettings.when(() -> WebSettings.getDefaultUserAgent(null)).thenReturn("");
            setFinalStatic(Build.class.getField("MANUFACTURER"), "Google");
            setFinalStatic(Build.class.getField("MODEL"), "sdk_gphone64_x86_64");

            String urlPrefixOverride = "https://bedbathbeyond.dev.groupbycloud.com/api/beacons/v3/native-app/from-client";
            GbTracker tracker = GbTracker.getInstance("bedbathbeyond", "area", new Login(true, "shopper@example.com"), urlPrefixOverride);

            Product product = new Product();
            product.setCategory("Test Category");
            product.setCollection("Test Collection");
            product.setId("TestId");
            product.setSku("TestSku");
            Price price = new Price(true, "103.65", "100", "CAD");
            product.setPrice(price);
            product.setTitle("Test Title");

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(10);

            Cart cart = new Cart();
            cart.setItems(new ArrayList<CartItem>());
            cart.getItems().add(cartItem);
            cart.setType("Test Type");

            AddToCartEvent atcEvent = new AddToCartEvent();
            atcEvent.setCart(cart);
            atcEvent.setGoogleAttributionToken("TestGAT");

            AddToCartBeacon atcBeacon = new AddToCartBeacon();
            atcBeacon.setEvent(atcEvent);
            atcBeacon.setMetadata(new ArrayList<Metadata>());
            atcBeacon.getMetadata().add(new Metadata("TestKey", "TestValue"));
            atcBeacon.setExperiments(new ArrayList<Experiments>());
            atcBeacon.getExperiments().add(new Experiments("TestId", "TestVariant"));

            tracker.sendAddToCartEvent(atcBeacon, new GbCallback() {
                @Override
                public void onSuccess() {
                    assertTrue(true);
                }

                @Override
                public void onFailure(final GbException e, int statusCode) {
                    assertTrue(false);
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}