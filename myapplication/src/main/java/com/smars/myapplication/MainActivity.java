package com.smars.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sramar.permission.GPermisson;
import com.sramar.permission.Permission;
import com.sramar.permission.PermissionGlobalConfigCallback;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        GPermisson.init(new PermissionGlobalConfigCallback() {
            @Override
            public void shouldShowRational(String permission, Object ration) {
                Log.e("momo","shouldShowRational"+permission+": "+ration);
                Toast.makeText(context,(String)ration,Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onPermissonReject(String permission, Object reject) {
                Log.e("momo","onPermissonReject"+permission+": "+reject);
                Toast.makeText(context,(String)reject,Toast.LENGTH_SHORT).show();
            }
        });


        testPermission();
    }
    private void initView() {}

    @Permission(permissions={Manifest.permission.CAMERA},srationales={"该功能需要访问您的相机"},srejects={"未获得相机访问权限，请前往权限控制打开该权限"})
    private void testPermission(){
        Log.e("momo","测试权限");
        Toast.makeText(context,"测试权限",Toast.LENGTH_SHORT).show();
    }
}
