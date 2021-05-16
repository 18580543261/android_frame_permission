package com.sramar.permission;

import android.app.Service;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
public class PermissionAspect {
    @Around("execution(@com.sramar.permission.Permission * *(..))")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint) {
        try {
            // get annotation of method
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Permission annotation = method.getAnnotation(Permission.class);
            // get annotation value
            final String[] permissions = annotation.permissions();
            final int[] rationales = annotation.rationales();
            final int[] rejects = annotation.rejects();
            final String[] srationales = annotation.srationales();
            final String[] srejects = annotation.srejects();

            final List<String> permissionList = Arrays.asList(permissions);
            Object object = joinPoint.getThis();
            Context context = null;
            if (object instanceof FragmentActivity) {
                context = (FragmentActivity) object;
            } else if (object instanceof Fragment) {
                context = ((Fragment) object).getContext();
            } else if (object instanceof Service) {
                context = (Service) object;
            }
            // start request permission
            GPermisson.with(context)
                    .permisson(permissions)
                    .callback(new PermissionCallback() {
                        @Override
                        public void onPermissionGranted() {
                            try {
                                joinPoint.proceed();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }

                        @Override
                        public void shouldShowRational(String permisson) {
                            int index = permissionList.indexOf(permisson);
                            int rationale = -1;
                            String srationale = "";
                            if (rationales.length > index) {
                                rationale = rationales[index];
                            }
                            if (srationales.length > index) {
                                srationale = srationales[index];
                            }
                            if(GPermisson.getGlobalConfigCallback() != null)
                                GPermisson.getGlobalConfigCallback().shouldShowRational(permisson, srationale);
                        }

                        @Override
                        public void onPermissonReject(String permisson) {
                            int index = permissionList.indexOf(permisson);
                            int reject = -1;
                            String sreject = "";
                            if (rejects.length > index) {
                                reject = rejects[index];
                            }
                            if (srejects.length > index) {
                                sreject = srejects[index];
                            }
                            if(GPermisson.getGlobalConfigCallback() != null)
                                GPermisson.getGlobalConfigCallback().onPermissonReject(permisson, sreject);
                        }
                    }).request();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
