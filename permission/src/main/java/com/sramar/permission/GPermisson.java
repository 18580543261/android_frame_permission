package com.sramar.permission;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

public class GPermisson {
    private static PermissionGlobalConfigCallback globalConfigCallback;
    private PermissionCallback callback;
    private String[] permissions;
    private static Context context;

    public GPermisson() {
    }

    public static void init(Context c,PermissionGlobalConfigCallback callback) {
        context = c;
        globalConfigCallback = callback;
    }

    static PermissionGlobalConfigCallback getGlobalConfigCallback() {
        return globalConfigCallback;
    }

    public static GPermisson with() {
        return new GPermisson();
    }

    public GPermisson permisson(String[] permissons) {
        this.permissions = permissons;
        return this;
    }

    public GPermisson callback(PermissionCallback callback) {
        this.callback = callback;
        return this;
    }

    public void request() {
        if (permissions == null || permissions.length <= 0) {
            return;
        }
        PermissionActivity.request(context, permissions, callback);
    }

    /**
     * Jump to Settings page of your application
     * @param context
     */
    public static void startSettingsActivity(Context context) {
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
