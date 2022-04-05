
package com.groupby.tracker;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Native app client component
 * <p>
 * Contains data about the client sending the beacon when the client is a native app.
 * 
 */
class NativeAppClient implements Parcelable
{

    /**
     * The native app platform.We need this to differentiate between Android and iOS beacons for analytics purposes.
     * (Required)
     * 
     */
    @SerializedName("platform")
    @Expose
    private NativeAppClient.Platform platform;
    /**
     * The device user's language preference, expressed as a language code. Ex. "en-US".
     * (Required)
     * 
     */
    @SerializedName("lang")
    @Expose
    private String lang;
    /**
     * The model of the device. Ex. "Pixel 5" or "iOS".
     * (Required)
     * 
     */
    @SerializedName("model")
    @Expose
    private String model;
    /**
     * An identifier for the native app. For iOS, see https://developer.apple.com/library/archive/documentation/General/Conceptual/DevPedia-CocoaCore/AppID.html. For Android, see https://developer.android.com/studio/build/configure-app-module. This isn't required because it doesn't stop GroupBy from being able to use native app beacons to improve Search and Recommendations, but it does provide a dimension in Analytics if provided.
     * (Required)
     *
     */
    @SerializedName("appId")
    @Expose
    private String appId;
    public final static Creator<NativeAppClient> CREATOR = new Creator<NativeAppClient>() {


        @SuppressWarnings({
            "unchecked"
        })
        public NativeAppClient createFromParcel(android.os.Parcel in) {
            return new NativeAppClient(in);
        }

        public NativeAppClient[] newArray(int size) {
            return (new NativeAppClient[size]);
        }

    }
    ;

    protected NativeAppClient(android.os.Parcel in) {
        this.platform = ((NativeAppClient.Platform) in.readValue((Platform.class.getClassLoader())));
        this.lang = ((String) in.readValue((String.class.getClassLoader())));
        this.model = ((String) in.readValue((String.class.getClassLoader())));
        this.appId = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public NativeAppClient() {
    }

    /**
     * 
     * @param model
     * @param lang
     * @param platform
     */
    public NativeAppClient(NativeAppClient.Platform platform, String lang, String model, String appId) {
        super();
        this.platform = platform;
        this.lang = lang;
        this.model = model;
        this.appId = appId;
    }

    /**
     * The native app platform.We need this to differentiate between Android and iOS beacons for analytics purposes.
     * (Required)
     * 
     */
    public NativeAppClient.Platform getPlatform() {
        return platform;
    }

    /**
     * The native app platform.We need this to differentiate between Android and iOS beacons for analytics purposes.
     * (Required)
     * 
     */
    public void setPlatform(NativeAppClient.Platform platform) {
        this.platform = platform;
    }

    /**
     * The device user's language preference, expressed as a language code. Ex. "en-US".
     * (Required)
     * 
     */
    public String getLang() {
        return lang;
    }

    /**
     * The device user's language preference, expressed as a language code. Ex. "en-US".
     * (Required)
     * 
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * The model of the device. Ex. "Pixel 5" or "iOS".
     * (Required)
     * 
     */
    public String getModel() {
        return model;
    }

    /**
     * The model of the device. Ex. "Pixel 5" or "iOS".
     * (Required)
     * 
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * An identifier for the native app. For iOS, see https://developer.apple.com/library/archive/documentation/General/Conceptual/DevPedia-CocoaCore/AppID.html. For Android, see https://developer.android.com/studio/build/configure-app-module. This isn't required because it doesn't stop GroupBy from being able to use native app beacons to improve Search and Recommendations, but it does provide a dimension in Analytics if provided.
     * (Required)
     *
     */
    public String getAppId() {
        return appId;
    }

    /**
     * An identifier for the native app. For iOS, see https://developer.apple.com/library/archive/documentation/General/Conceptual/DevPedia-CocoaCore/AppID.html. For Android, see https://developer.android.com/studio/build/configure-app-module. This isn't required because it doesn't stop GroupBy from being able to use native app beacons to improve Search and Recommendations, but it does provide a dimension in Analytics if provided.
     * (Required)
     *
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NativeAppClient.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("platform");
        sb.append('=');
        sb.append(((this.platform == null)?"<null>":this.platform));
        sb.append(',');
        sb.append("lang");
        sb.append('=');
        sb.append(((this.lang == null)?"<null>":this.lang));
        sb.append(',');
        sb.append("model");
        sb.append('=');
        sb.append(((this.model == null)?"<null>":this.model));
        sb.append(',');
        sb.append("appId");
        sb.append('=');
        sb.append(((this.appId == null)?"<null>":this.model));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.model == null)? 0 :this.model.hashCode()));
        result = ((result* 31)+((this.lang == null)? 0 :this.lang.hashCode()));
        result = ((result* 31)+((this.appId == null)? 0 :this.appId.hashCode()));
        result = ((result* 31)+((this.platform == null)? 0 :this.platform.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NativeAppClient) == false) {
            return false;
        }
        NativeAppClient rhs = ((NativeAppClient) other);
        return ((((this.model == rhs.model)||((this.model!= null)&&this.model.equals(rhs.model)))&&((this.lang == rhs.lang)||((this.lang!= null)&&this.lang.equals(rhs.lang))))&&((this.appId == rhs.appId)||((this.appId!= null)&&this.appId.equals(rhs.appId)))&&((this.platform == rhs.platform)||((this.platform!= null)&&this.platform.equals(rhs.platform))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(platform);
        dest.writeValue(lang);
        dest.writeValue(model);
        dest.writeValue(appId);
    }

    public int describeContents() {
        return  0;
    }


    /**
     * The native app platform.We need this to differentiate between Android and iOS beacons for analytics purposes.
     * 
     */
    public enum Platform {

        @SerializedName("android")
        ANDROID("android"),
        @SerializedName("ios")
        IOS("ios");
        private final String value;
        private final static Map<String, NativeAppClient.Platform> CONSTANTS = new HashMap<String, NativeAppClient.Platform>();

        static {
            for (NativeAppClient.Platform c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Platform(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static NativeAppClient.Platform fromValue(String value) {
            NativeAppClient.Platform constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
