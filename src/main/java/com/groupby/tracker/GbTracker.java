package com.groupby.tracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.google.gson.reflect.TypeToken;
import com.groupby.tracker.model.Login;
import com.groupby.tracker.model.Metadata;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class GbTracker {
    private static final GbTracker instance = new GbTracker();

    private String customerId;
    private String area = "Production";
    private String urlPrefixOverride = "";
    private Context context;

    private ApiClient localVarApiClient;

    private Customer customer;
    private ShopperTracking shopperTracking;
    private NativeAppClient nativeAppClient;

    private String siteFilterMetadataValue;

    GbTracker() {

    }

    protected GbTracker init(Context context) {
        instance.context = context;
        return instance;
    }

    protected static GbTracker getInstance() {
        return instance;
    }

    public static GbTracker getInstance(String customerId, String area){
        return getInstance(customerId, area, "");
    }

    public static GbTracker getInstance(String customerId, String area, String urlPrefixOverride){
        return getInstance(customerId, area, new Login(false, null), urlPrefixOverride);
    }

    public static GbTracker getInstance(String customerId, String area, Login login){
        return getInstance(customerId, area, login, "");
    }

    public static GbTracker getInstance(String customerId, String area, Login login, String urlPrefixOverride) {
        Utils.getUserAgent();

        instance.setCustomerId(customerId);
        instance.setArea(area);
        instance.setCustomer(new Customer(customerId, area));
        instance.setNativeAppClient(new NativeAppClient(NativeAppClient.Platform.ANDROID, Locale.getDefault().getLanguage(), instance.getDeviceName(), Utils.appPackageName));

        String uuid = "";
        SharedPreferences prefs = instance.context.getSharedPreferences("com.groupby.tracker", Context.MODE_PRIVATE);
        long expires = prefs.getLong("uuid_expiration", 0);
        if(System.currentTimeMillis() < expires){
            //still valid
            uuid = prefs.getString("uuid_value", "");
        }
        if (uuid.equalsIgnoreCase(""))
        {
            uuid = UUID.randomUUID().toString();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("uuid_value", uuid);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, 1);
            Date nextYear = cal.getTime();
            editor.putLong("uuid_expiration", nextYear.getTime());

            editor.apply();
        }
        instance.setShopperTracking(new ShopperTracking(uuid, login));

        instance.setUrlPrefixOverride(urlPrefixOverride);

        instance.localVarApiClient = Configuration.getDefaultApiClient();
        if (urlPrefixOverride.equalsIgnoreCase(""))
        {
            instance.localVarApiClient.setBasePath(String.format(instance.localVarApiClient.getBasePath(),customerId));
        }
        else
        {
            instance.localVarApiClient.setBasePath(urlPrefixOverride);
            instance.localVarApiClient.setDebugging(true);
        }

        return instance;
    }

    private void renewUUIDExpiration() {
        SharedPreferences prefs = instance.context.getSharedPreferences("com.groupby.tracker", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        Date nextYear = cal.getTime();
        editor.putLong("uuid_expiration", nextYear.getTime());

        editor.apply();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String value) {
        this.customerId = value;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String value) {
        this.area = value;
    }

    public Login getLogin() {
        if (shopperTracking == null)
        {
            return null;
        }
        else
        {
            return shopperTracking.getLogin();
        }
    }

    public void setLogin(Login value) {
        if (shopperTracking == null)
        {
            shopperTracking = new ShopperTracking();
        }

        shopperTracking.setLogin(value);
    }

    protected String getUrlPrefixOverride() {
        return urlPrefixOverride;
    }

    protected void setUrlPrefixOverride(String urlPrefixOverride) {
        this.urlPrefixOverride = urlPrefixOverride;
    }

    private Customer getCustomer() {
        return customer;
    }

    private void setCustomer(Customer value) {
        this.customer = value;
    }

    public String getVisitorId() {
        if (shopperTracking == null)
        {
            return null;
        }
        else
        {
            return shopperTracking.getVisitorId();
        }
    }

    private void setVisitorId(String value) {
        if (shopperTracking == null)
        {
            shopperTracking = new ShopperTracking();
        }

        shopperTracking.setVisitorId(value);
    }

    private ShopperTracking getShopperTracking() {
        return shopperTracking;
    }

    private void setShopperTracking(ShopperTracking value) {
        shopperTracking = value;
    }

    private NativeAppClient getNativeAppClient() {
        return nativeAppClient;
    }

    private void setNativeAppClient(NativeAppClient value) {
        nativeAppClient = value;
    }

    private String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    public String getSite() {
        return siteFilterMetadataValue;
    }

    public void setSite(String value) {
        this.siteFilterMetadataValue = value;
    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    private List<Metadata> setSiteFilterMetadataValue(List<Metadata> metadata)
    {
        if (metadata == null)
        {
            metadata = new ArrayList<>();
        }

        if (this.siteFilterMetadataValue != null && !this.siteFilterMetadataValue.equalsIgnoreCase(""))
        {
            boolean valueSet = false;
            for (int i = 0; i < metadata.size(); i++)
            {
                Metadata siteFilterMetadata = metadata.get(i);
                if (siteFilterMetadata.getKey().equalsIgnoreCase("siteFilter"))
                {
                    siteFilterMetadata.setValue(this.siteFilterMetadataValue);
                    valueSet = true;
                }
            }

            if (!valueSet)
            {
                Metadata siteFilterMetadata = new Metadata();
                siteFilterMetadata.setKey("siteFilter");
                siteFilterMetadata.setValue(this.siteFilterMetadataValue);
                metadata.add(siteFilterMetadata);
            }
        }
        else
        {
            for (int i = 0; i < metadata.size(); i++)
            {
                Metadata siteFilterMetadata = metadata.get(i);
                if (siteFilterMetadata.getKey().equalsIgnoreCase("siteFilter"))
                {
                    metadata.remove(i);
                    break;
                }
            }
        }

        return metadata;
    }

    /**
     * Build call for sendAddToCartEvent
     * @param addToCartBeacon AddToCartBeacon
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    protected okhttp3.Call sendAddToCartEventCall(AddToCartBeacon addToCartBeacon, final GbCallback _callback) { // throws GbException {
        addToCartBeacon.setTime(new Date());
        addToCartBeacon.setCustomer(this.getCustomer());
        addToCartBeacon.setShopper(this.getShopperTracking());
        addToCartBeacon.setClient(this.getNativeAppClient());
        addToCartBeacon.setMetadata(setSiteFilterMetadataValue(addToCartBeacon.getMetadata()));

        Object localVarPostBody = addToCartBeacon;

        // create path and map variables
        String localVarPath = "/addToCart";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, String> localVarCookieParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                "text/plain", "application/json", "text/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
                "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call sendAddToCartEventValidateBeforeCall(AddToCartBeacon addToCartBeacon, final GbCallback _callback) { // throws GbException {
        okhttp3.Call localVarCall = sendAddToCartEventCall(addToCartBeacon, _callback);
        return localVarCall;
    }

    /**
     * Returns Void (asynchronously)
     *
     * @param addToCartBeacon AddToCartBeacon
     * @param _callback The callback to be executed when the API call finishes
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    public void sendAddToCartEvent(AddToCartBeacon addToCartBeacon, final GbCallback _callback) { // throws GbException {
        okhttp3.Call localVarCall = sendAddToCartEventValidateBeforeCall(addToCartBeacon, _callback);
        Type localVarReturnType = new TypeToken<Void>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        renewUUIDExpiration();
    }

    /**
     * Build call for sendAutoSearchEvent
     * @param autoSearchBeacon AutoSearchBeacon
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    protected okhttp3.Call sendAutoSearchEventCall(AutoSearchBeacon autoSearchBeacon, final GbCallback _callback) { //throws GbException {
        autoSearchBeacon.setTime(new Date());
        autoSearchBeacon.setCustomer(this.getCustomer());
        autoSearchBeacon.setShopper(this.getShopperTracking());
        autoSearchBeacon.setClient(this.getNativeAppClient());
        autoSearchBeacon.setMetadata(setSiteFilterMetadataValue(autoSearchBeacon.getMetadata()));

        Object localVarPostBody = autoSearchBeacon;

        // create path and map variables
        String localVarPath = "/autoSearch";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, String> localVarCookieParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                "text/plain", "application/json", "text/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
                "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call sendAutoSearchEventValidateBeforeCall(AutoSearchBeacon autoSearchBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendAutoSearchEventCall(autoSearchBeacon, _callback);
        return localVarCall;
    }

    /**
     * Returns Void (asynchronously)
     *
     * @param autoSearchBeacon AutoSearchBeacon
     * @param _callback The callback to be executed when the API call finishes
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    public void sendAutoSearchEvent(AutoSearchBeacon autoSearchBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendAutoSearchEventValidateBeforeCall(autoSearchBeacon, _callback);
        Type localVarReturnType = new TypeToken<Void>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        renewUUIDExpiration();
    }

    /**
     * Build call for sendViewProductEvent
     * @param viewProductBeacon ViewProductBeacon
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    protected okhttp3.Call sendViewProductEventCall(ViewProductBeacon viewProductBeacon, final GbCallback _callback) { //throws GbException {
        viewProductBeacon.setTime(new Date());
        viewProductBeacon.setCustomer(this.getCustomer());
        viewProductBeacon.setShopper(this.getShopperTracking());
        viewProductBeacon.setClient(this.getNativeAppClient());
        viewProductBeacon.setMetadata(setSiteFilterMetadataValue(viewProductBeacon.getMetadata()));

        Object localVarPostBody = viewProductBeacon;

        // create path and map variables
        String localVarPath = "/viewProduct";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, String> localVarCookieParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                "text/plain", "application/json", "text/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
                "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call sendViewProductEventValidateBeforeCall(ViewProductBeacon viewProductBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendViewProductEventCall(viewProductBeacon, _callback);
        return localVarCall;
    }

    /**
     * Returns Void (asynchronously)
     *
     * @param viewProductBeacon ViewProductBeacon
     * @param _callback The callback to be executed when the API call finishes
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    public void sendViewProductEvent(ViewProductBeacon viewProductBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendViewProductEventValidateBeforeCall(viewProductBeacon, _callback);
        Type localVarReturnType = new TypeToken<Void>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        renewUUIDExpiration();
    }

    /**
     * Build call for sendRemoveFromCartEvent
     * @param removeFromCartBeacon RemoveFromCartBeacon
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    protected okhttp3.Call sendRemoveFromCartEventCall(RemoveFromCartBeacon removeFromCartBeacon, final GbCallback _callback) { //throws GbException {
        removeFromCartBeacon.setTime(new Date());
        removeFromCartBeacon.setCustomer(this.getCustomer());
        removeFromCartBeacon.setShopper(this.getShopperTracking());
        removeFromCartBeacon.setClient(this.getNativeAppClient());
        removeFromCartBeacon.setMetadata(setSiteFilterMetadataValue(removeFromCartBeacon.getMetadata()));

        Object localVarPostBody = removeFromCartBeacon;

        // create path and map variables
        String localVarPath = "/removeFromCart";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, String> localVarCookieParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                "text/plain", "application/json", "text/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
                "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call sendRemoveFromCartEventValidateBeforeCall(RemoveFromCartBeacon removeFromCartBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendRemoveFromCartEventCall(removeFromCartBeacon, _callback);
        return localVarCall;
    }

    /**
     * Returns Void (asynchronously)
     *
     * @param removeFromCartBeacon RemoveFromCartBeacon
     * @param _callback The callback to be executed when the API call finishes
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    public void sendRemoveFromCartEvent(RemoveFromCartBeacon removeFromCartBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendRemoveFromCartEventValidateBeforeCall(removeFromCartBeacon, _callback);
        Type localVarReturnType = new TypeToken<Void>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        renewUUIDExpiration();
    }

    /**
     * Build call for sendOrderEvent
     * @param orderBeacon OrderBeacon
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    protected okhttp3.Call sendOrderEventCall(OrderBeacon orderBeacon, final GbCallback _callback) { //throws GbException {
        orderBeacon.setTime(new Date());
        orderBeacon.setCustomer(this.getCustomer());
        orderBeacon.setShopper(this.getShopperTracking());
        orderBeacon.setClient(this.getNativeAppClient());
        orderBeacon.setMetadata(setSiteFilterMetadataValue(orderBeacon.getMetadata()));

        Object localVarPostBody = orderBeacon;

        // create path and map variables
        String localVarPath = "/order";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, String> localVarCookieParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                "text/plain", "application/json", "text/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
                "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call sendOrderEventValidateBeforeCall(OrderBeacon orderBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendOrderEventCall(orderBeacon, _callback);
        return localVarCall;
    }

    /**
     * Returns Void (asynchronously)
     *
     * @param orderBeacon OrderBeacon
     * @param _callback The callback to be executed when the API call finishes
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    public void sendOrderEvent(OrderBeacon orderBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendOrderEventValidateBeforeCall(orderBeacon, _callback);
        Type localVarReturnType = new TypeToken<Void>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        renewUUIDExpiration();
    }

    /**
     * Build call for sendRecImpressionEvent
     * @param recImpressionBeacon RecImpressionBeacon
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    protected okhttp3.Call sendRecImpressionEventCall(RecImpressionBeacon recImpressionBeacon, final GbCallback _callback) { //throws GbException {
        recImpressionBeacon.setTime(new Date());
        recImpressionBeacon.setCustomer(this.getCustomer());
        recImpressionBeacon.setShopper(this.getShopperTracking());
        recImpressionBeacon.setClient(this.getNativeAppClient());
        recImpressionBeacon.setMetadata(setSiteFilterMetadataValue(recImpressionBeacon.getMetadata()));

        Object localVarPostBody = recImpressionBeacon;

        // create path and map variables
        String localVarPath = "/recImpression";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, String> localVarCookieParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                "text/plain", "application/json", "text/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
                "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call sendRecImpressionEventValidateBeforeCall(RecImpressionBeacon recImpressionBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendRecImpressionEventCall(recImpressionBeacon, _callback);
        return localVarCall;
    }

    /**
     * Returns Void (asynchronously)
     *
     * @param recImpressionBeacon RecImpressionBeacon
     * @param _callback The callback to be executed when the API call finishes
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    public void sendRecImpressionEvent(RecImpressionBeacon recImpressionBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendRecImpressionEventValidateBeforeCall(recImpressionBeacon, _callback);
        Type localVarReturnType = new TypeToken<Void>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        renewUUIDExpiration();
    }

    /**
     * Build call for sendManualSearchEvent
     * @param manualSearchBeacon ManualSearchBeacon
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    protected okhttp3.Call sendManualSearchEventCall(ManualSearchBeacon manualSearchBeacon, final GbCallback _callback) { //throws GbException {
        manualSearchBeacon.setTime(new Date());
        manualSearchBeacon.setCustomer(this.getCustomer());
        manualSearchBeacon.setShopper(this.getShopperTracking());
        manualSearchBeacon.setClient(this.getNativeAppClient());
        manualSearchBeacon.setMetadata(setSiteFilterMetadataValue(manualSearchBeacon.getMetadata()));

        Object localVarPostBody = manualSearchBeacon;

        // create path and map variables
        String localVarPath = "/manualSearch";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, String> localVarCookieParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                "text/plain", "application/json", "text/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
                "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call sendManualSearchEventValidateBeforeCall(ManualSearchBeacon manualSearchBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendManualSearchEventCall(manualSearchBeacon, _callback);
        return localVarCall;
    }

    /**
     * Returns Void (asynchronously)
     *
     * @param manualSearchBeacon ManualSearchBeacon
     * @param _callback The callback to be executed when the API call finishes
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
    <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
    <tr><td> 403 </td><td> Invalid Request </td><td>  -  </td></tr>
    <tr><td> 500 </td><td> Server Error </td><td>  -  </td></tr>
    </table>
     */
    public void sendManualSearchEvent(ManualSearchBeacon manualSearchBeacon, final GbCallback _callback) { //throws GbException {
        okhttp3.Call localVarCall = sendManualSearchEventValidateBeforeCall(manualSearchBeacon, _callback);
        Type localVarReturnType = new TypeToken<Void>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        renewUUIDExpiration();
    }
}
