package com.sramar.permission;

public abstract class PermissionGlobalConfigCallback {
//    abstract public void shouldShowRational(String permission, int ration);
//    abstract public void onPermissonReject(String permission, int reject);

    abstract public void shouldShowRational(String permission, Object sration);
    abstract public void onPermissonReject(String permission, Object sreject);
}
