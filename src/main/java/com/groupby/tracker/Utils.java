package com.groupby.tracker;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.webkit.WebSettings;

import java.lang.reflect.Method;

class Utils {
    protected static String userAgent = null;
    protected static String appPackageName = null;
    protected static String appVersion = null;

    public static String getUserAgent(Context context) {
        if (null == userAgent) { // do this once and store it
            Class clazz;
            Method method;
            String appDetails = "";

            try {
                clazz = Class.forName("android.app.ActivityThread");
            } catch (Exception e) {
                clazz = null;
            }

            if (clazz != null) {
                try {
                    method = clazz.getDeclaredMethod("currentPackageName");
                    appPackageName = (String) method.invoke(clazz);
                } catch (Exception e) {
                    appPackageName = "";
                }

                try {
                    method = clazz.getDeclaredMethod("currentApplication");
                    Application app = (Application) method.invoke(clazz);
                    PackageInfo pinfo = app.getPackageManager().getPackageInfo(
                            appPackageName,
                            0
                    );
                    appVersion = pinfo.versionName;
                } catch (Exception e) {
                    appVersion = "";
                }
            }

            appDetails = (appPackageName == null || appPackageName.isEmpty() ? "" : appPackageName)
                    + (appVersion == null || appVersion.isEmpty() ? "" : "/" + appVersion);

            userAgent = WebSettings.getDefaultUserAgent(context)
                    + " " + BuildConfig.LIBRARY_PACKAGE_NAME
                    + (appDetails.isEmpty() ? "" : " " + appDetails);
        }

        return userAgent;
    }
}
